package com.tiddev.sample.service.repository;

import com.tiddev.sample.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User , Long> {
    Optional<User> findByUsernameOrEmail(String username , String email);
}
