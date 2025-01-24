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

import java.sql.Date;
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
    private String BlogName;

    @JsonProperty("blogUserID")
    private String BlogUserID;

    @JsonProperty("category")
    private String Category;

    @JsonProperty("description")
    private String Description;

    @JsonProperty("createdDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
    private LocalDateTime CreatedDateTime;

    @JsonProperty("lastUpdatedTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
    private LocalDateTime LastUpdatedTime;

}

