package com.springproject.blogger.controller;

import com.springproject.blogger.model.BlogUser;
import com.springproject.blogger.model.UserLogin;
import com.springproject.blogger.service.impl.BlogUserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin //Fix CORS errors
@RequestMapping("/blogportal")
public class BlogUserController {
    @Autowired
    private final BlogUserServiceImpl blogUserService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public BlogUserController(BlogUserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.blogUserService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup") //New user registration
    public ResponseEntity<String> addBlogUser(@RequestBody @Valid BlogUser blogUser)
    {
        blogUserService.addNewBlogUser(blogUser);
        return new ResponseEntity<>("New User Added!", HttpStatus.OK);
    }

    @PostMapping("/login") //User login
    public String login(@RequestBody UserLogin userLogin) {
        String token = blogUserService.verifyUserLogin(userLogin);
        System.out.println(token);
        return token;
    }

    @GetMapping("/myprofile") //Get current logged user profile
    public ResponseEntity<Optional<BlogUser>> getMyProfile(){
        Optional<BlogUser> myProfile = blogUserService.getMyProfileDetails();
        if(!myProfile.isEmpty()){
            return new ResponseEntity<>(myProfile, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/blogusers") //Get all the existing user list
    public ResponseEntity<List<BlogUser>> getAllUsers() {
        List<BlogUser> blogUserList = blogUserService.getBlogUserList();
        if(blogUserList != null){
            return new ResponseEntity<>(blogUserList,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(blogUserList,HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteuser/{blogUserID}") //Delete an existing user
    public ResponseEntity<String> deleteBlogUser(@PathVariable int blogUserID) {
        blogUserService.deleteBlogUser(blogUserID);
        return new ResponseEntity<>("User Deleted Successfully!", HttpStatus.OK);
    }

    @GetMapping("bloguser/{blogUserID}") //Get user by id
    public ResponseEntity<BlogUser> getBlogUserDetailsByID(@PathVariable int blogUserID){
        BlogUser userDetails = blogUserService.getBlogUserByID(blogUserID);
        if(userDetails != null) {
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
