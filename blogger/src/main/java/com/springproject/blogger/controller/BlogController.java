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
@RequestMapping("/blogger")
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
    public void addBlog(@RequestBody Blog blog)
    {
        blogService.addNewBlog(blog);
    }

    @PutMapping("/blog")
    public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
    {
        Blog b = blogService.updateBlogDetails(blog);
        if(b != null)
        {
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping ("/blog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable int id)
    {
        Blog b = blogService.getBlogByID(id);
        if(b != null)
        {
            blogService.deleteBlog(id);
            return new ResponseEntity<>("Blog Deleted", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/blogs/search")
    public ResponseEntity<List<Blog>> getBlogsBySearch(@RequestParam String keyword)
    {
        List<Blog> blogList = blogService.getBlogListBySearch(keyword);
        return new ResponseEntity<>(blogList,HttpStatus.OK);
    }
}
