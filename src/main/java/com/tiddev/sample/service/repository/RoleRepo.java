package com.tiddev.sample.service.repository;

import com.tiddev.sample.service.model.Role;
import org.hibernate.boot.jaxb.mapping.JaxbPostRemove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role , Long> {
}
