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
    @JsonProperty("ID")
    private int ID;

    @JsonProperty("BlogName")
    private String BlogName;

    @JsonProperty("BlogUserID")
    private String BlogUserID;

    @JsonProperty("Category")
    private String Category;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("CreatedDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
    private LocalDateTime CreatedDateTime;

    @JsonProperty("LastUpdatedTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
    private LocalDateTime LastUpdatedTime;

}

