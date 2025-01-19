package com.springproject.blogger.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonProperty("BlogUserName")
    private String BlogUserName;

    @JsonProperty("Role")
    private String Role;

    @JsonProperty("Email")
    private String Email;

    @JsonProperty("Password")
    private String Password;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonProperty("RegistrationDateTime")
    private LocalDateTime RegistrationDateTime;
}
