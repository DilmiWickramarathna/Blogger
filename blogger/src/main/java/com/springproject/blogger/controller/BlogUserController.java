package com.springproject.blogger.controller;

import com.springproject.blogger.model.Blog;
import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> addBlogUser(@RequestBody BlogUser blogUser)
    {
        boolean successSignup = false;
        blogUser.setPassword(passwordEncoder.encode(blogUser.getPassword()));
        successSignup = blogUserService.addNewBlogUser(blogUser);

        if(successSignup)
        {
            return new ResponseEntity<>("New User Added!", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public String login() {
        return "Login successful!";
    }

}
