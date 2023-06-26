package com.ebcr.repositories;

import com.ebcr.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<AppUser, UUID> {
    public AppUser findUserByUserName(String username);
}
