# Artist Portfolio — Spring Boot + MongoDB

## Project Overview
Personal cabinet for an artist to manage artworks, commission requests and clients.
University lab project demonstrating MongoDB CRUD, aggregation and multi-collection queries.

## Architecture
Layered architecture: Controller → Service → Repository → Model/DTO
- Never skip layers (Controller must not call Repository directly)
- Never put business logic in Controller
- Controllers accept DTO, pass DTO to service
- Services accept DTO, handle mapping via mapper and business logic, return Model
- Mappers convert between Model and DTO (MapStruct)
- Controllers convert Model to ResponseDTO via mapper before returning

## Tech Stack
- Java 21
- Spring Boot 3.5.13
- Spring Data MongoDB
- Lombok (@Data, @RequiredArgsConstructor)
- MapStruct 1.5.5.Final
- MongoDB Atlas (database: artist_portfolio)
- SpringDoc OpenAPI (Swagger)

## Collections
- `users` — id, email, username, avatarUrl, bio, isVerified, createdAt
- `artist_profile` — id, email
- `paintings` — id, artistId, title, description, imageUrl, price, status, isPublic, createdAt
- `homepage_profile` — id, email, name, title, bio, skills, achievements, contacts, isActive
- `favorites` — id, userId, paintingId, createdAt
- `commission_topics` — id, name, description, isActive
- `commission_requests` — id, userId, topicId, title, description, budgetMin, budgetMax, deadline, contactName, contactMeBy, contactOther, artistNote, status, createdAt, updatedAt
- `email_verification_tokens` — id, userId, token, createdAt, expiresAt

## Enums (in model/enums/)
- `PaintingStatus` — FOR_SALE, SOLD, NOT_FOR_SALE
- `CommissionStatus` — NEW, VIEWED, IN_PROGRESS, DECLINED, COMPLETED

## Mapper Rules
- @Mapper(componentModel = "spring") on all mappers
- @Mapping(target = "id", ignore = true) for create methods
- @Mapping(target = "createdAt", ignore = true) for create methods
- void updateModel(UpdateDTO dto, @MappingTarget Model model) for updates
- Call updateModel then save separately (not inside save())

## Naming Conventions
- Controllers: `PaintingController`
- Services: `PaintingService`
- Repositories: `PaintingRepository`
- Models: `Painting`
- DTOs: `PaintingCreateDTO`, `PaintingUpdateDTO`, `PaintingResponseDTO`
- Mappers: `PaintingMapper`
- Packages: controller, service, repository, model, model/enums, dto, mapper

## MongoDB Annotations
- @Document(collection = "paintings") on models
- @Id on id field (String type)
- @CreatedDate on createdAt field
- @EnableMongoAuditing on main application class
- Use MongoRepository<Model, String>

## API Design
- REST endpoints, JSON responses
- ResponseEntity for all controller methods
- @RequestParam for filters (required=false where optional)
- Return empty list instead of 404 when no results
- DELETE returns ResponseEntity<Void> with noContent()

## Required Validations
- Check userId exists before creating CommissionRequest or Favorite
- Check artistId exists before creating Painting
- Check topicId exists before creating CommissionRequest
- Check email uniqueness before creating User
- Check painting+user uniqueness before creating Favorite

## Security
- SecurityConfig disables auth for development
- JWT auth to be implemented later

## Code Style
- Use orElseThrow(() -> new RuntimeException("...")) for not found cases
- No magic strings — use enums
- Validate in Service, not Controller
- Use Optional<> when finding single entity
- @Mapping(target = "id", ignore = true) always for create mappers