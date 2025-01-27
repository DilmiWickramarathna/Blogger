package com.springproject.blogger.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
//LamBok for getters, setters and constructors
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id //To define primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //To auto generate the id val
    @JsonProperty("id")
    private int ID;

    @JsonProperty("blogName")
    private String blogName;

    @JsonProperty("blogUserID")
    private int blogUserID;

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;

    @JsonProperty("createdDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy:HH:mm:ss", timezone = "UTC")
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @JsonProperty("lastUpdatedTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public void setBlogUserID(int blogUserID) {
        this.blogUserID = blogUserID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public int getID() {
        return ID;
    }

    public String getBlogName() {
        return blogName;
    }

    public int getBlogUserID() {
        return blogUserID;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }
}

