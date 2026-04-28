package com.ihor.artist_portfolio_server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

  @Id
  private String id;

  @Indexed(unique = true)
  private String email;
  private String password;
  private String name;
  private String avatarUrl;
  private String bio;

  private Boolean isVerified = false;

  @CreatedDate
  private LocalDateTime createdAt;

}