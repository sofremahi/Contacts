package com.tiddev.sample.web;

import com.tiddev.sample.service.ContactService;
import com.tiddev.sample.service.model.Contacts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.tiddev.sample.service.constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Tag(name = "contract controller",
        description = " some operations for contracts rest api "
)
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService service;

    @Operation(summary = "creating a contact",
            description = "saving the contact info to the contacts table in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTPS status response code 201 Created"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Contacts> createContact(@RequestBody @Validated Contacts contact) {
        return ResponseEntity.created(URI.create("/contacts/userID")).body(service.saveContact(contact));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN' , 'USER')")
    public ResponseEntity<Page<Contacts>> contactsPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(service.getAllContacts(page, size));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'USER')")
    public ResponseEntity<Contacts> getContact(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(service.getContact(id));
    }

    @PutMapping("/photo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadPhoto(@RequestParam(value = "id") String id,
                                              @RequestParam(value = "file") MultipartFile file) {
        return ResponseEntity.ok().body(service.uploadPhoto(id, file));
    }

    @GetMapping(path = "image/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    @PreAuthorize("hasAnyRole('ADMIN' , 'USER')")
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}
