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
    @JsonProperty("BlogUserID")
    private int BlogUserID;

    @Column(nullable = false)
    @JsonProperty("username")
    private String username;

    @Column(nullable = false)
    @JsonProperty("Role")
    private String Role;

    @Column(nullable = false)
    @JsonProperty("Email")
    private String Email;

    @Column(nullable = false)
    @JsonProperty("Password")
    private String Password;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonProperty("RegistrationDateTime")
    private LocalDateTime RegistrationDateTime;

    public void setBlogUserID(int blogUserID) {
        BlogUserID = blogUserID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        Role = role;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        RegistrationDateTime = registrationDateTime;
    }

    public int getBlogUserID() {
        return BlogUserID;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return Role;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public LocalDateTime getRegistrationDateTime() {
        return RegistrationDateTime;
    }
}
