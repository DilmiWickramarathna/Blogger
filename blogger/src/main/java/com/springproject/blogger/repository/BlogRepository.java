package com.springproject.blogger.repository;

import com.springproject.blogger.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    @Query("SELECT b FROM Blog b WHERE " +
            "LOWER(b.BlogName) LIKE LOWER (CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.Category) LIKE LOWER (CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.Description) LIKE LOWER (CONCAT('%', :keyword, '%'))")
    List<Blog> searchBlogs(String keyword);
}
