package com.springproject.blogger.service;

import com.springproject.blogger.model.BlogUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface BlogUserService {
    UserDetails loadUserByUsername(String username);
    void addNewBlogUser(BlogUser blogUser);
    BlogUser getBlogUserByID(int id);
    Optional<BlogUser> getMyProfileDetails();
    List<BlogUser> getBlogUserList();
    void deleteBlogUser(int blogUserID);
}
