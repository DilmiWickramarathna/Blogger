package com.springproject.blogger.service.impl;

import com.springproject.blogger.exception.NoElementFoundException;
import com.springproject.blogger.exception.NoPermissionException;
import com.springproject.blogger.exception.RegistrationException;
import com.springproject.blogger.model.Blog;
import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.repository.BlogUserRepository;
import com.springproject.blogger.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlogUserServiceImpl implements UserDetailsService, BlogUserService {

    @Autowired
    private final BlogUserRepository blogUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BlogUserServiceImpl(BlogUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.blogUserRepo = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BlogUser> userNew = blogUserRepo.findByUsername(username);
        return blogUserRepo.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole()) // Assign a default role
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public void addNewBlogUser(BlogUser blogUser) {
        Optional<BlogUser> userWithSameName = blogUserRepo.findByUsername(blogUser.getUsername());
        if (!userWithSameName.isPresent()) {
            Optional<BlogUser> userWithSameEmail = blogUserRepo.findByEmail(blogUser.getEmail());
            if(!userWithSameEmail.isPresent()){
                blogUser.setPassword(passwordEncoder.encode(blogUser.getPassword()));
                blogUserRepo.save(blogUser);
            }else{
                throw new RegistrationException("User with this email already exists!");
            }
        }else{
            throw new RegistrationException("User with this username already exists!");
        }
    }

    @Override
    public BlogUser getBlogUserByID(int id) {
        return blogUserRepo.findById(id).orElseThrow(() -> new NoElementFoundException("User not found!"));
    }

    @Override
    public Optional<BlogUser> getMyProfileDetails() {
        String myName;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        myName =  ((UserDetails) principal).getUsername();
        return Optional.ofNullable(blogUserRepo.findByUsername(myName).orElseThrow(() -> new NoElementFoundException("User not found!")));
    }

    @Override
    public List<BlogUser> getBlogUserList() {
        Optional<BlogUser> myprofile = getMyProfileDetails();
        List<BlogUser> userList = null;
        if(Objects.equals(((Optional<BlogUser>)myprofile).get().getRole(), "ADMIN")){
                userList = blogUserRepo.findAll();
                return userList;
        }else{
            throw new NoPermissionException("You don't have permission to see the user list!");
        }
    }
    @Override
    public void deleteBlog(int blogUserID) {
        BlogUser userToDelete = getBlogUserByID(blogUserID);
        Optional<BlogUser> myProfile = getMyProfileDetails();
        if((!myProfile.isEmpty()) && ((Objects.equals(myProfile.get().getRole(), "ADMIN")) || (myProfile.get().getBlogUserID() == blogUserID))){
            if(userToDelete != null)
            {
                blogUserRepo.deleteById(blogUserID);
            }
            else
            {
                throw new NoElementFoundException("User not found!");
            }
        }else{
            throw new NoPermissionException("You don't have permission to delete this user!");
        }
    }
}
