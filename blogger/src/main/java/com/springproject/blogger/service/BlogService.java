package com.springproject.blogger.service;
import com.springproject.blogger.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    Blog getBlogByID(int id);
    void addNewBlog(Blog blog);
    void updateBlogDetails(Blog blog);
    void deleteBlog(int id);
    List<Blog> getBlogListBySearch(String keyword);
}
