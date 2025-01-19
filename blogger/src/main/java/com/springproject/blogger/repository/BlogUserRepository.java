package com.springproject.blogger.repository;

import com.springproject.blogger.model.Blog;
import com.springproject.blogger.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogUserRepository  extends JpaRepository<BlogUser,Integer> {
}
