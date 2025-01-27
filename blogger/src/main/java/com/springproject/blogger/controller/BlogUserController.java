package com.springproject.blogger.controller;

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
@RequestMapping("/blogportal")
public class BlogUserController {
    @Autowired
    private final BlogUserService blogUserService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public BlogUserController(BlogUserService userService, PasswordEncoder passwordEncoder) {
        this.blogUserService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> addBlogUser(@RequestBody BlogUser blogUser)
    {
        boolean successSignup = false;
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
        if(blogUserList != null){
            return new ResponseEntity<>(blogUserList,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(blogUserList,HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteuser/{blogUserID}")
    public ResponseEntity<String> deleteBlogUser(@PathVariable int blogUserID) {
        int deletionResult = blogUserService.deleteBlog(blogUserID);
        if(deletionResult == 1) {
            return new ResponseEntity<>("User Deleted Successfully!", HttpStatus.OK);
        }else if(deletionResult == 2){
            return new ResponseEntity<>("This user doesn't exists!", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("You don't have authority to delete this user!",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{blogUserID}")
    public ResponseEntity<BlogUser> getBlogUserDetailsByID(@PathVariable int blogUserID){
        BlogUser userDetails = blogUserService.getBlogUserByID(blogUserID);
        if(userDetails != null)
        {
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
