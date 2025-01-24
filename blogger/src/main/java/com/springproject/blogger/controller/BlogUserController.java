package com.springproject.blogger.controller;

import com.springproject.blogger.model.Blog;
import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin //Fix CORS errors
@RequestMapping("/user")
public class BlogUserController {
    @Autowired
    private BlogUserService blogUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BlogUserController(BlogUserService userService, PasswordEncoder passwordEncoder) {
        this.blogUserService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> addBlogUser(@RequestBody BlogUser blogUser)
    {
        boolean successSignup = false;
        blogUser.setPassword(passwordEncoder.encode(blogUser.getPassword()));
        successSignup = blogUserService.addNewBlogUser(blogUser);

        if(successSignup)
        {
            return new ResponseEntity<>("New User Added!", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public String login() {
        return "Login successful!";
    }

    @GetMapping("/myprofile")
    public ResponseEntity<Optional<BlogUser>> getMyProfile(){
        Optional<BlogUser> myProfile = blogUserService.getMyProfileDetails();
        if(!myProfile.isEmpty()){
            return new ResponseEntity<>(myProfile, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/blogusers")
    public ResponseEntity<List<BlogUser>> getAllUsers() {
        List<BlogUser> blogUserList = blogUserService.getBlogUserList();
        return new ResponseEntity<>(blogUserList,HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser/{blogUserID}")
    public ResponseEntity<String> deleteBlogUser(@PathVariable int blogUserID) {
        System.out.println("AAAAAAAAAAAAAA");
        BlogUser userToDelete = blogUserService.getBlogUserByID(blogUserID);
        if(userToDelete != null)
        {
            System.out.println("bbbbbbbbbbbb");
            blogUserService.deleteBlog(blogUserID);
            return new ResponseEntity<>("User Deleted", HttpStatus.OK);
        }
        else
        {
            System.out.println("cccccccccccc");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{blogUserID}")
    public ResponseEntity<BlogUser> getBlogUserDetailsByID(@PathVariable int blogUserID){
        System.out.println("1111111111");
        BlogUser userDetails = blogUserService.getBlogUserByID(blogUserID);
        if(userDetails != null)
        {
            System.out.println("222222222");
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }
        else
        {
            System.out.println("333333333");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
