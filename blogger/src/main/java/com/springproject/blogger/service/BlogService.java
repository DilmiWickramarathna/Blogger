package com.springproject.blogger.service;

import com.springproject.blogger.model.Blog;
import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepo;

    @Autowired
    private final BlogUserService blogUserService;

    public BlogService(BlogUserService blogUserService) {
        this.blogUserService = blogUserService;
    }

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public Blog getBlogByID(int id) {
        return blogRepo.findById(id).orElse(null);
    }

    public int addNewBlog(Blog blog) {
        try {
            Optional<BlogUser> myprofile = blogUserService.getMyProfileDetails();
            if(Objects.equals(myprofile.get().getRole(), "AUTHOR")){
                blog.setBlogUserID(myprofile.get().getBlogUserID());
                blogRepo.save(blog);
                return 1;
            }else{
                System.out.println("You don't have access to create blogs!");
                return 2;
            }
        } catch (StaleObjectStateException e) {
            System.out.println("Conflict detected: " + e.getMessage());
            return 3;
        }
    }

    public int updateBlogDetails(Blog blog) {
        Optional<Blog> optBlog = blogRepo.findById(blog.getID());
        if(optBlog.isPresent()){
            Optional<BlogUser> myprofile = blogUserService.getMyProfileDetails();
            if(myprofile.isPresent()){
                Blog existingBlog = optBlog.get();
                if(Objects.equals(myprofile.get().getBlogUserID(),existingBlog.getBlogUserID())) {
                    existingBlog.setBlogName(blog.getBlogName());
                    existingBlog.setCategory(blog.getCategory());
                    existingBlog.setDescription(blog.getDescription());
                    blogRepo.save(existingBlog);
                    return 1;
                }else{
                    return 2;
                }
            }
        }else{
            return 3;
        }
        return 4;
    }

    @Transactional
    public int deleteBlog(int id) {
        Optional<Blog> optBlog = blogRepo.findById(id);
        if(optBlog.isPresent()) {
            Optional<BlogUser> myprofile = blogUserService.getMyProfileDetails();
            if(myprofile.isPresent()) {
                Blog existingBlog = optBlog.get();
                if((Objects.equals(myprofile.get().getBlogUserID(),existingBlog.getBlogUserID()))
                || (Objects.equals(myprofile.get().getRole(),"ADMIN") )){
                    blogRepo.deleteById(id);
                    return 1;
                }else {
                    return 2;
                }
            }
        }else{
            //blog doesn't exist
            return 3;
        }
        return 4;
    }

    public List<Blog> getBlogListBySearch(String keyword) {
        return blogRepo.searchBlogs(keyword);
    }
}
