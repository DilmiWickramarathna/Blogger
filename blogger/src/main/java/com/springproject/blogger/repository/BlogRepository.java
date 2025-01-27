package com.springproject.blogger.repository;

import com.springproject.blogger.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    @Query("SELECT b FROM Blog b WHERE " +
            "LOWER(b.blogName) LIKE LOWER (CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.category) LIKE LOWER (CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.description) LIKE LOWER (CONCAT('%', :keyword, '%'))")
    List<Blog> searchBlogs(String keyword);
}
