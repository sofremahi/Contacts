package com.tiddev.sample.service;

import com.tiddev.sample.common.exception.NoResourceException;
import com.tiddev.sample.service.model.Contacts;
import com.tiddev.sample.service.repository.ContactRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.tiddev.sample.service.constant.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepo contactRepo;

    public Page<Contacts> getAllContacts(int page, int size) {
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Contacts getContact(String id) {
        return contactRepo.findById(id).orElseThrow(
                () -> new NoResourceException("no contact found")
        );
    }

    public Contacts saveContact(Contacts contact) {
        return contactRepo.save(contact);
    }

    public void deleteContact(Contacts contact) {
      contactRepo.deleteById(contact.getId());
    }

    public String uploadPhoto(String id, MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Uploaded file is empty");
        }
        Contacts contact = getContact(id);
        String photoUrl = photoFunction.apply(id, file);
        contact.setPhotoUrl(photoUrl);
        contactRepo.save(contact);
        return photoUrl;
    }


    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");


    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String fileName = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(fileName) , REPLACE_EXISTING) ;

            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/contacts/image/" + fileName)
                    .toUriString();
        } catch (Exception e) {
            throw new RuntimeException("unable to upload the photo");
        }
    };
}
