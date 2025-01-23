package com.springproject.blogger.repository;

import com.springproject.blogger.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogUserRepository  extends JpaRepository<BlogUser,Integer> {
    Optional<BlogUser> findByUsername(String username);
}
