package com.springproject.blogger.service.impl;

import com.springproject.blogger.exception.NoElementFoundException;
import com.springproject.blogger.exception.NoPermissionException;
import com.springproject.blogger.model.Blog;
import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.repository.BlogRepository;
import com.springproject.blogger.service.BlogService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepo;

    @Autowired
    private final BlogUserServiceImpl blogUserService;

    public BlogServiceImpl(BlogUserServiceImpl blogUserService) {
        this.blogUserService = blogUserService;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    @Override
    public Blog getBlogByID(int id) {
        return blogRepo.findById(id).orElseThrow(()-> new NoElementFoundException("Blog with ID" + id + " not found!"));
    }

    @Override
    public void addNewBlog(Blog blog) {
        Optional<BlogUser> myprofile = blogUserService.getMyProfileDetails();
        if(Objects.equals(myprofile.get().getRole(), "AUTHOR")){
            blog.setBlogUserID(myprofile.get().getBlogUserID());
            blogRepo.save(blog);
        }else{
            throw new NoPermissionException("You don't have permission to add new blog!");
        }
    }

    @Override
    public void updateBlogDetails(Blog blog) {
        int blogID = blog.getID();
        Optional<Blog> optBlog = blogRepo.findById(blogID);
        if(optBlog.isPresent()){
            Optional<BlogUser> myprofile = blogUserService.getMyProfileDetails();
            if(myprofile.isPresent()){
                Blog existingBlog = optBlog.get();
                if(Objects.equals(myprofile.get().getBlogUserID(),existingBlog.getBlogUserID())) {
                    existingBlog.setBlogName(blog.getBlogName());
                    existingBlog.setCategory(blog.getCategory());
                    existingBlog.setDescription(blog.getDescription());
                    blogRepo.save(existingBlog);
                }else{
                    throw new NoPermissionException("You don't have permission to update this blog!");
                }
            }
        }else{
            //blog doesn't exist
            throw new NoElementFoundException("Blog with ID" + blogID + " not found!");
        }
    }

    @Override
    @Transactional
    public void deleteBlog(int id) {
        Optional<Blog> optBlog = blogRepo.findById(id);
        if(optBlog.isPresent()) {
            Optional<BlogUser> myprofile = blogUserService.getMyProfileDetails();
            if(myprofile.isPresent()) {
                Blog existingBlog = optBlog.get();
                if((Objects.equals(myprofile.get().getBlogUserID(),existingBlog.getBlogUserID()))
                || (Objects.equals(myprofile.get().getRole(),"ADMIN") )){
                    blogRepo.deleteById(id);
                }else {
                    throw new NoPermissionException("You don't have permission to delete this blog!");
                }
            }
        }else{
            //blog doesn't exist
            throw new NoElementFoundException("Blog with ID" + id + " not found!");
        }
    }

    @Override
    public List<Blog> getBlogListBySearch(String keyword) {
        return blogRepo.searchBlogs(keyword);
    }
}
