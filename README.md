# 🎨 Artist Portfolio — Server

Spring Boot backend for the Artist Portfolio platform. Handles authentication, paintings, commission requests, favorites, and the artist's public profile.

> Built as a university project (ХНУРЕ, group ПЗПІ-23-5) and a real portfolio application.

**Frontend repo:** [artist-portfolio-client](https://github.com/NureVysotskyiIhor/artist-portfolio-client)

---

## Tech Stack

- **Java 21** + **Spring Boot 3.5**
- **MongoDB Atlas** (cloud replica set, Spring Data MongoDB)
- **Spring Security** — stateless JWT auth (HS384)
- **MapStruct** + **Lombok**
- **Spring Mail** — email verification via Gmail SMTP
- **SpringDoc OpenAPI** — Swagger UI at `/swagger-ui/index.html`

### Database
- **MongoDB Atlas** — 8 collections
- **2dsphere index** — geo search for commission requests
- **Aggregation pipelines** — `$lookup` (favorites → paintings), `$group` (popularity stats)

---

## Database Schema

| Collection | Description |
|-----------|-------------|
| `users` | Registered users |
| `artist_profile` | Artist profile (determines ROLE_ARTIST in JWT) |
| `paintings` | Painting catalog |
| `homepage_profile` | Artist public homepage content |
| `favorites` | User saved paintings |
| `commission_topics` | Commission topic dictionary |
| `commission_requests` | Commission requests with GeoJSON location |
| `email_verification_tokens` | One-time email verification tokens |

---

## API Overview

```
POST   /api/auth/register          Register + send verification email
GET    /api/auth/verify?token=     Verify email
POST   /api/auth/login             Login → JWT token

GET    /api/paintings              List all paintings
GET    /api/paintings/filter       Filter by status and/or price range
GET    /api/paintings/:id          Get painting by ID
POST   /api/paintings              Create painting         [ARTIST]
PUT    /api/paintings/:id          Update painting         [ARTIST]
DELETE /api/paintings/:id          Delete painting         [ARTIST]

GET    /api/commission-requests             All requests
GET    /api/commission-requests/nearby      Geo search by radius
GET    /api/commission-requests/user/:id    By user
POST   /api/commission-requests             Create request
PUT    /api/commission-requests/:id         Update (client)
PUT    /api/commission-requests/:id/artist  Update status + note [ARTIST]
DELETE /api/commission-requests/:id         Delete request

GET    /api/favorites/with-painting  Favorites with painting data ($lookup)
GET    /api/favorites/stats          Popularity stats ($group)
POST   /api/favorites                Add to favorites
DELETE /api/favorites                Remove from favorites

GET    /api/nominatim/search?q=      Address autocomplete proxy (Nominatim)
GET    /api/homepage-profile         Public artist profile
```

---

## Getting Started

### Prerequisites
- Java 21
- MongoDB Atlas account (or local MongoDB)

### Setup

```bash
git clone https://github.com/NureVysotskyiIhor/artist-portfolio-server
cd artist-portfolio-server
```

Configure `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb+srv://<user>:<password>@<cluster>.mongodb.net/artist_portfolio

app.jwt.secret=<your-secret-min-32-chars>
app.jwt.expiration=86400000

spring.mail.username=<your-gmail>
spring.mail.password=<app-password>

app.base-url=http://localhost:8080
```

```bash
./mvnw spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui/index.html

---

## Project Structure

```
src/main/java/com/ihor/artist_portfolio_server/
├── config/          # SecurityConfig, RestTemplateConfig, SwaggerConfig
├── controller/      # REST controllers
├── dto/             # Request/Response DTOs
├── exception/       # Custom exceptions
├── mapper/          # MapStruct mappers
├── model/           # MongoDB documents
│   └── enums/       # PaintingStatus, CommissionStatus
├── repository/      # Spring Data MongoDB repositories
├── security/        # JwtFilter, JwtService
└── service/         # Business logic
```

---

## Author

**Ihor Vysotskyi** — ХНУРЕ, group ПЗПІ-23-5

[GitHub](https://github.com/NureVysotskyiIhor)
