package com.springproject.blogger.controller;

import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin //Fix CORS errors
@RequestMapping("/blogger")
public class BlogUserController {
    @Autowired
    private BlogUserService service;

    @PostMapping("/user/register")
    public void addBlogUser(@RequestBody BlogUser blogUser)
    {
        service.addNewBlogUser(blogUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<BlogUser> getBlogUserByID(@PathVariable int id){

        BlogUser blogUser = service.getBlogUserByID(id);

        if(blogUser != null)
            return new ResponseEntity<>(blogUser, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
