package com.tiddev.sample.service.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "CONTACTS")
@Entity
public class Contacts {
    @Id
    @UuidGenerator
    @Column(name = "CONTACT_ID",updatable = false , unique = true)
    private String id;
    @Column(name = "CONTACT_NAME")
    private String name;
    @Email(message = "email is not valid")
    @NotEmpty(message = "email can not be empty")
    private String email;
    private String title ;
    private String phone;
    @Column(name = "CONTACT_NUMBER")
    private String number;
    private String status;
    private String photoUrl;

}
