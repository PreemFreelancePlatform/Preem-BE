# Preem. the better freelance platform

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

1. install JDK 16
3. git clone (https://github.com/PreemFreelancePlatform/Preem-BE)
4. mvn clean install
5. mvn spring-boot:run` || press run
6. test endpoints with postman (preferred)

## Dependencies:

- Spring Boot Data JPA
- Spring Boot Security
- Spring Security OAuth2
- Spring Boot Mail
- Spring Boot WebSocket
- Spring Boot Web
- Spring Boot DevTools
- H2 Database Engine
- PostgreSQL JDBC
- JavaFaker

## Overview
This document provides comprehensive documentation for all API endpoints in the BFLP (Better Freelance Platform) application. The API follows RESTful principles and uses JSON for request and response formats.

## Base URL
All endpoints are relative to: `http://localhost:2019`

## Authentication
Most endpoints require authentication using OAuth2. To obtain a token, use the following endpoint:

| Endpoint | Method | Description | Parameters | Response |
|----------|---------|-------------|------------|-----------|
| `/login` | POST | Obtain authentication token | `username`, `password`, `client_id`, `client_secret` | JWT Token |

## Common Response Codes
- `200 OK`: Request successful
- `201 Created`: Resource created successfully
- `400 Bad Request`: Invalid request parameters
- `401 Unauthorized`: Authentication required
- `403 Forbidden`: Insufficient permissions
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server error

## Contract Endpoints

| Endpoint | Method | Description | Parameters | Response |
|----------|---------|-------------|------------|-----------|
| `/contract/contracts` | GET | Get all contracts | None | List of Contracts |
| `/contract/{id}` | GET | Get contract by ID | `id` | Contract |
| `/contract/new/{fid}/{cid}` | POST | Create a new contract | `fid`, `cid`, Contract body | OK |
| `/contract/{id}` | PATCH | Update a contract | `id`, Contract body | OK |
| `/contract/{contractid}` | DELETE | Delete a contract | `contractid` | OK |

### Contract Request/Response Examples

#### Create Contract
```http
POST /contract/new/123/456
Content-Type: application/json

{
    "title": "Web Development Project",
    "description": "Create a responsive website",
    "status": "ACTIVE",
    "startDate": "2023-05-01",
    "endDate": "2023-06-01",
    "budget": 1500.00
}
```

#### Get Contract
```http
GET /contract/789
```

Response:
```json
{
    "contractid": 789,
    "title": "Web Development Project",
    "description": "Create a responsive website",
    "status": "ACTIVE",
    "customer": {
        "customerid": 456,
        "firstname": "John",
        "lastname": "Doe"
    },
    "freelancer": {
        "freelancerid": 123,
        "firstname": "Jane",
        "lastname": "Smith"
    }
}
```

## Customer Endpoints

| Endpoint | Method | Description | Parameters | Response |
|----------|---------|-------------|------------|-----------|
| `/customer/customers` | GET | Get all customers | None | List of Customers |
| `/customer/customer/{customerid}` | GET | Get customer by ID | `customerid` | Customer |
| `/customer/customer/{customerid}` | PATCH | Update customer | `customerid`, Customer body | OK |
| `/customer/customer/{customerid}` | DELETE | Delete customer | `customerid` | OK |
| `/customer/upload/{customerid}` | PATCH | Upload customer image | `customerid`, MultipartFile | OK |

### Customer Request/Response Examples

#### Update Customer
```http
PATCH /customer/customer/456
Content-Type: application/json

{
    "firstname": "John",
    "lastname": "Doe",
    "email": "john.doe@example.com"
}
```

## Freelancer Endpoints

| Endpoint | Method | Description | Parameters | Response |
|----------|---------|-------------|------------|-----------|
| `/freelancer/freelancers` | GET | Get all freelancers | None | List of Freelancers |
| `/freelancer/freelancer/{email}` | GET | Get freelancer by email | `email` | Freelancer |
| `/freelancer/freelancer/{id}` | GET | Get freelancer by ID | `id` | Freelancer |
| `/freelancer/{freelancerid}/post/{postid}` | POST | Add freelancer to post | `freelancerid`, `postid` | OK |
| `/freelancer/freelancer/{id}` | PATCH | Update freelancer | `id`, Freelancer body | OK |
| `/freelancer/freelancer/{freelancerid}` | DELETE | Delete freelancer | `freelancerid` | OK |
| `/freelancer/upload/{freelancerid}` | PATCH | Upload freelancer image | `freelancerid`, MultipartFile | OK |

### Freelancer Request/Response Examples

#### Update Freelancer
```http
PATCH /freelancer/freelancer/123
Content-Type: application/json

{
    "firstname": "Jane",
    "lastname": "Smith",
    "email": "jane.smith@example.com",
    "skills": ["Java", "Spring", "React"]
}
```

## Post Endpoints

| Endpoint | Method | Description | Parameters | Response |
|----------|---------|-------------|------------|-----------|
| `/customer/post/posts` | GET | Get all posts | `page` | Page of CustomerPosts |
| `/customer/post/filter` | GET | Filter posts | `category`, `tags`, `min`, `max`, `page`, `size` | Filtered Posts |
| `/customer/post/{postid}` | GET | Get post by ID | `postid` | CustomerPost |
| `/customer/post/customer/{customerid}` | POST | Create new post | `customerid`, Post body | Created Post |
| `/customer/post/{postid}` | PATCH | Update post | `postid`, Post body | OK |
| `/customer/post/{postid}` | DELETE | Delete post | `postid` | OK |
| `/customer/post/{pid}/{fid}` | DELETE | Remove freelancer from post | `pid`, `fid` | OK |

### Post Request/Response Examples

#### Create Post
```http
POST /customer/post/customer/456
Content-Type: application/json

{
    "title": "Need a Website Developer",
    "description": "Looking for an experienced web developer",
    "category": "Web Development",
    "budget": 2000.00,
    "tags": ["React", "Node.js", "MongoDB"]
}
```

## Tag Request Endpoints

| Endpoint | Method | Description | Parameters | Response |
|----------|---------|-------------|------------|-----------|
| `/tagRequest/requests` | GET | Get all tag requests | None | List of TagRequests |
| `/tagRequest/request/{id}` | GET | Get tag request by ID | `id` | TagRequest |
| `/tagRequest/freelancer/{freelancerid}` | POST | Create new tag request | `freelancerid`, TagRequest body | Created TagRequest |

### Tag Request Examples

#### Create Tag Request
```http
POST /tagRequest/freelancer/123
Content-Type: application/json

{
    "tagName": "NewTechnology",
    "description": "Request to add new technology tag"
}
```

## Utility Endpoints

| Endpoint | Method | Description | Parameters | Response |
|----------|---------|-------------|------------|-----------|
| `/getmyposts` | GET | Get posts for authenticated customer | None | List of CustomerPosts |
| `/cgetmycontracts` | GET | Get contracts for authenticated customer | None | List of Contracts |
| `/fgetmycontracts` | GET | Get contracts for authenticated freelancer | None | List of Contracts |
| `/getmyinfo` | GET | Get current user information | None | Customer/Freelancer info |
| `/recover/{email}` | POST | Password recovery | `email`, `answer1`, `answer2` | "CORRECT"/"WRONG" |
| `/getquestions/{email}` | GET | Get security questions | `email` | Security questions |

## User Registration Endpoints

| Endpoint | Method | Description | Parameters | Response |
|----------|---------|-------------|------------|-----------|
| `/createnewfreelancer` | POST | Create new freelancer account | MinUser body | Created Freelancer |
| `/createnewcustomer` | POST | Create new customer account | Customer body, `imageFile` | Created Customer |

### Registration Examples

#### Create Freelancer
```http
POST /createnewfreelancer
Content-Type: application/json

{
    "firstname": "Jane",
    "lastname": "Smith",
    "email": "jane.smith@example.com",
    "password": "securepassword"
}
```

#### Create Customer
```http
POST /createnewcustomer
Content-Type: multipart/form-data

{
    "firstname": "John",
    "lastname": "Doe",
    "email": "john.doe@example.com",
    "password": "securepassword",
    "security1": "What is your mother's maiden name?",
    "security2": "What was your first pet's name?",
    "imageFile": [binary file data]
}
```

## WebSocket Endpoints

| Endpoint | Method | Description | Parameters | Response |
|----------|---------|-------------|------------|-----------|
| `/topic/messages` | WebSocket | Subscribe to messages | None | Messages |
| `/app/hello` | WebSocket | Send message | Message string | Processed message |

### WebSocket Example
```javascript
// Connect to WebSocket
const socket = new WebSocket('ws://localhost:2019/ws');

// Subscribe to messages
socket.send(JSON.stringify({
    destination: '/app/subscribe',
    body: JSON.stringify({})
}));

// Send message
socket.send(JSON.stringify({
    destination: '/app/hello',
    body: JSON.stringify({
        message: 'Hello, World!'
    })
}));
```

## Error Responses

### 400 Bad Request
```json
{
    "error": "Bad Request",
    "message": "Invalid input parameters",
    "details": "Email format is invalid"
}
```

### 401 Unauthorized
```json
{
    "error": "Unauthorized",
    "message": "Authentication required"
}
```

### 403 Forbidden
```json
{
    "error": "Forbidden",
    "message": "Insufficient permissions"
}
```

### 404 Not Found
```json
{
    "error": "Not Found",
    "message": "Resource not found",
    "details": "Customer with ID 123 not found"
}
```










