package com.springproject.blogger.service;

import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.repository.BlogUserRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogUserService implements UserDetailsService {

    @Autowired
    private BlogUserRepository blogUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BlogUserService(BlogUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.blogUserRepo = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BlogUser> user = blogUserRepo.findByUsername(username);

        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(passwordEncoder.encode(userObj.getPassword())) // Add {noop} prefix for plain-text password
                    .roles(userObj.getRole()) // Assign a default role
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public void addNewBlogUser(BlogUser blogUser) {
        try {
            blogUser.setPassword(passwordEncoder.encode(blogUser.getPassword()));
            blogUserRepo.save(blogUser);
        } catch (StaleObjectStateException e) {
            System.out.println("Conflict detected: " + e.getMessage());
        }
    }

    public BlogUser getBlogUserByID(int id) {
        return blogUserRepo.findById(id).orElse(null);
    }
}
