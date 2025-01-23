package com.springproject.blogger.controller;

import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin //Fix CORS errors
@RequestMapping("/user")
public class BlogUserController {
    @Autowired
    private BlogUserService blogUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BlogUserController(BlogUserService userService, PasswordEncoder passwordEncoder) {
        this.blogUserService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public void addBlogUser(@RequestBody BlogUser blogUser)
    {
        blogUserService.addNewBlogUser(blogUser);
    }

}
