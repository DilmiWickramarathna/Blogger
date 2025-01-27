package com.springproject.blogger.controller;

import com.springproject.blogger.model.Blog;
import com.springproject.blogger.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/blogportal")
public class BlogController {
    @Autowired
    private BlogServiceImpl blogService;

    @GetMapping("/blogs") //Get all the existing blogs
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        if(!blogs.isEmpty())
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/blog/{id}") //Get blog by its id
    public ResponseEntity<Blog> getBlogByISBN(@PathVariable int id){
        Blog blog = blogService.getBlogByID(id);
        return new ResponseEntity<Blog>(blog,HttpStatus.OK);
    }

    @PostMapping("/blog") //Add new blog
    public ResponseEntity<String> addBlog(@RequestBody Blog blog)
    {
        blogService.addNewBlog(blog);
        return new ResponseEntity<String>("Blog added successfully!",HttpStatus.OK);
    }

    @PutMapping("/blog") //Update an existing blog
    public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
    {
        blogService.updateBlogDetails(blog);
        return new ResponseEntity<>("Updated the blog successfully!", HttpStatus.OK);
    }

    @DeleteMapping ("/blog/{id}") //Delete an existing blog
    public ResponseEntity<String> deleteBlog(@PathVariable int id)
    {
        blogService.deleteBlog(id);
        return new ResponseEntity<>("Blog deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/blogs/search") //Search blogs by keywords
    public ResponseEntity<List<Blog>> getBlogsBySearch(@RequestParam String keyword)
    {
        List<Blog> blogList = blogService.getBlogListBySearch(keyword);
        return new ResponseEntity<List<Blog>>(blogList,HttpStatus.OK);
    }
}
