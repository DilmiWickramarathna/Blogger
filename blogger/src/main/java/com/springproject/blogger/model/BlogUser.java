package com.springproject.blogger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
//LamBok for getters, setters and constructors
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUser {
    @Id //To define primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //To auto generate the id val
    @JsonProperty("blogUserID")
    private int blogUserID;

    @Column(nullable = false)
    @JsonProperty("username")
    private String username;

    @Column(nullable = false)
    @JsonProperty("role")
    private String role;

    @Column(nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(nullable = false)
    @JsonProperty("password")
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonProperty("registrationDateTime")
    private LocalDateTime registrationDateTime;

    public void setBlogUserID(int blogUserID) {
        this.blogUserID = blogUserID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public int getBlogUserID() {
        return blogUserID;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }
}
