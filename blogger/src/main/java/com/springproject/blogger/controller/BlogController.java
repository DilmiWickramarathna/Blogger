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
    private BlogService blogServic;

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return new ResponseEntity<>(blogServic.getAllBlogs(), HttpStatus.OK);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlogByISBN(@PathVariable int id){

        Blog blog = blogServic.getBlogByID(id);

        if(blog != null)
            return new ResponseEntity<>(blog,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/blog")
    public void addBlog(@RequestBody Blog blog)
    {
        blogServic.addNewBlog(blog);
    }

    @PutMapping("/blog")
    public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
    {
        Blog p = blogServic.updateBlogDetails(blog);
        if(p != null)
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
        Blog p = blogServic.getBlogByID(id);
        if(p != null)
        {
            blogServic.deleteBlog(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/blogs/search")
    public ResponseEntity<List<Blog>> getBlogsBySearch(@RequestParam String keyword)
    {
        List<Blog> prodList = blogServic.getBlogListBySearch(keyword);
        return new ResponseEntity<>(prodList,HttpStatus.OK);
    }
}
