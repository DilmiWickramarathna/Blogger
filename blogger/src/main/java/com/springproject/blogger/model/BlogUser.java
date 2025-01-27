package com.springproject.blogger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;

@Entity
//LamBok for getters, setters and constructors
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUser {
    @Id //To define primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //To auto generate the id val
    @Column(nullable = false)
    @JsonProperty("blogUserID")
    private int blogUserID;

    @NotNull(message = "User Name is a mandatory field!")
    @NotEmpty(message = "User Name is a mandatory field!")
    @Column(nullable = false)
    @JsonProperty("username")
    private String username;

    @NotNull(message = "User Role is a mandatory field!")
    @NotEmpty(message = "User Role is a mandatory field!")
    @Column(nullable = false)
    @JsonProperty("role")
    private String role;

    @NotNull(message = "Email is a mandatory field!")
    @NotEmpty(message = "Email is a mandatory field!")
    @Column(nullable = false)
    @Email(message = "Invalid email format")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "Password is a mandatory field!")
    @NotEmpty(message = "Password is a mandatory field!")
    @Column(nullable = false)
    @JsonProperty("password")
    private String password;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
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
