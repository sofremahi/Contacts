package com.tiddev.sample.service.repository;

import com.tiddev.sample.service.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepo extends JpaRepository<Contacts, String> {
    Optional<Contacts> findById(String id);
}
