package com.ihor.artist_portfolio_server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "email_verification_tokens")
public class EmailVerificationToken {
    @Id
    private String id;

    private String userId;
    private String token;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
}
