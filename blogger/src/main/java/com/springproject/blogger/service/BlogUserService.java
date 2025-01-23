package com.springproject.blogger.service;

import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.repository.BlogUserRepository;
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
    private final BlogUserRepository blogUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BlogUserService(BlogUserRepository userRepository, PasswordEncoder passwordEncoder) {
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

    public boolean addNewBlogUser(BlogUser blogUser) {
        Optional<BlogUser> user = blogUserRepo.findByUsername(blogUser.getUsername());
        if (!user.isPresent()) {
            blogUser.setPassword(blogUser.getPassword());
            blogUserRepo.save(blogUser);
            return true;
        }
        return false;
    }

    public BlogUser getBlogUserByID(int id) {
        return blogUserRepo.findById(id).orElse(null);
    }
}
