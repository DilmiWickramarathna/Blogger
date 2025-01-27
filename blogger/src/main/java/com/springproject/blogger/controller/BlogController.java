package com.springproject.blogger.controller;

import com.springproject.blogger.model.Blog;
import com.springproject.blogger.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //Fix CORS errors
@RequestMapping("/blogportal")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlogByISBN(@PathVariable int id){
        Blog blog = blogService.getBlogByID(id);

        if(blog != null)
            return new ResponseEntity<>(blog,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/blog")
    public ResponseEntity<String> addBlog(@RequestBody Blog blog)
    {
        int blogCreation;
        blogCreation = blogService.addNewBlog(blog);

        if(blogCreation == 1){
            return new ResponseEntity<String>("Blog added successfully!",HttpStatus.OK);
        }else if(blogCreation == 2){
            return new ResponseEntity<String>("You don't have permission to create blogs!",HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<String>("Conflict detected when adding the blog!",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/blog")
    public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
    {
        int success = 0;
        success = blogService.updateBlogDetails(blog);
        if(success == 1){
            return new ResponseEntity<>("Updated the blog!", HttpStatus.OK);
        }else if(success == 2) {
            return new ResponseEntity<>("You don't have permission to update the blog!",HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("This blog don't exists!",HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping ("/blog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable int id)
    {
        int success = 0;
        success = blogService.deleteBlog(id);
        if(success == 1)
            return new ResponseEntity<>("Blog Deleted", HttpStatus.OK);
        else if(success == 2)
            return new ResponseEntity<>("You don't have permission to delete the blog!",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("This blog don't exists!",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/blogs/search")
    public ResponseEntity<List<Blog>> getBlogsBySearch(@RequestParam String keyword)
    {
        List<Blog> blogList = blogService.getBlogListBySearch(keyword);
        return new ResponseEntity<List<Blog>>(blogList,HttpStatus.OK);
    }
}
