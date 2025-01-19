package com.springproject.blogger.service;

import com.springproject.blogger.model.Blog;
import com.springproject.blogger.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepo;

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public Blog getBlogByID(int id) {
        return blogRepo.findById(id).orElse(null);
    }

    public void addNewBlog(Blog blog) {
        try {
            blogRepo.save(blog);
        } catch (StaleObjectStateException e) {
            // Handle conflict, e.g., refresh the entity or retry
            System.out.println("Conflict detected: " + e.getMessage());
        }
    }

    public Blog updateBlogDetails(Blog blog) {
        return blogRepo.save(blog);
    }

    @Transactional
    public void deleteBlog(int id) {
        blogRepo.deleteById(id);
    }

    public List<Blog> getBlogListBySearch(String keyword) {
        return blogRepo.searchBlogs(keyword);
    }
}
