package com.springproject.blogger.service;

import com.springproject.blogger.model.Blog;
import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.repository.BlogUserRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogUserService {

    @Autowired
    private BlogUserRepository blogUserRepo;

    public void addNewBlogUser(BlogUser blogUser) {
        try {
            blogUserRepo.save(blogUser);
        } catch (StaleObjectStateException e) {
            // Handle conflict, e.g., refresh the entity or retry
            System.out.println("Conflict detected: " + e.getMessage());
        }
    }

    public BlogUser getBlogUserByID(int id) {
        return blogUserRepo.findById(id).orElse(null);
    }
}
