package com.tiddev.sample.web;

import com.tiddev.sample.service.ContactService;
import com.tiddev.sample.service.model.Contacts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService service;

    @PostMapping
    public ResponseEntity<Contacts> createContact(@RequestBody @Validated Contacts contact){
        return ResponseEntity.created(URI.create("/contacts/userID")).body(service.saveContact(contact));
    }
    @GetMapping
    public ResponseEntity<Page<Contacts>> contactsPage(@RequestParam(value = "page",defaultValue = "0") int page ,
                                                       @RequestParam(value = "size",defaultValue = "10") int size){
        return ResponseEntity.ok().body(service.getAllContacts(page , size));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Contacts> getContact(@PathVariable(value = "id") String id){
        return ResponseEntity.ok().body(service.getContact(id));
    }
    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam(value = "id") String id ,
                                              @RequestParam(value = "file")MultipartFile file){
        return ResponseEntity.ok().body(service.uploadPhoto( id , file));
    }
    @GetMapping(path = "image/{filename}" , produces = {IMAGE_PNG_VALUE , IMAGE_JPEG_VALUE})
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException{
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}
