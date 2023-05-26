# Preem. the better freelance platform

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

1. install JDK 16
3. git clone (https://github.com/PreemFreelancePlatform/Preem-BE)
4. mvn clean install
5. mvn spring-boot:run` || press run
6. test endpoints with postman (preferred)

# Dependencies 
-Spring Boot Data JPA

-Spring Boot Security

-Spring Security OAuth2

-Spring Boot  Mail

-Spring Boot  WebSocket
-Spring Boot Web
-Spring Boot DevTools
-H2 Database Engine
-PostgreSQL JDBC
-JavaFaker

## For Auth token
Almost All of the endpoints require authentication so you will need to obtain a token. This can be done by making a request to the authorization endpoint with the appropriate credentials.

# Users
| Role              |      email            |   password    |
| ----------------- | ---------------------- |  ----------    |
| Admin           |     Admin             |  Admin   |
| Freelancer     | JimBob@gmail.com       |  jimbob  |
| Customer        | JoshWilson@gmail.com  |  josh |

# Params
| Parameter         | Value                  |
| ----------------- | ---------------------- |
| Access Token URL  | http://localhost:2019/login |
| Client ID         | OAUTHCLIENTID          |
| Client Secret     | OAUTHCLIENTSECRET      |
| Username          | email                  |
| Password          | password               |

Please replace Username and Password to the user who is asking to auth.

# Utility endpoints
| Endpoint | HTTP Method | Description | Parameters | Response |
|---|---|---|---|---|
| `/logout | GET | Revoke token | N/A | HTTP Status OK |
| `/createnew{customer/freelancer} |POST| creates new user | {email, username, firstname, password} | HTTP Status CREATED |
| `/recover/{email}` | POST | Provides recovery functionality for a forgotten password | {email, answer1, answer2} | Bot sends temp info if correct |
| `/getquestions/{email}` | GET | Fetches security questions for password recovery | {email} | Customer or Freelancer security questions |

# Freelancer Endpoints 
All API endpoints start with: `http://localhost:2019/`
The following table provides an overview of the API endpoints available in the `FreelancerController` class:
| Endpoint | HTTP Method | Description | Parameters | Response |
|---|---|---|---|---|
| `/freelancer/freelancers` | GET | Fetches all freelancers (Admin or Customer only) | N/A | List of Freelancers in JSON |
| `/freelancer/freelancer/{email}` | GET | Fetches freelancer by email | {email} | Freelancer information in JSON |
| `/freelancer/freelancer/{id}` | GET | Fetches freelancer by ID | {id} | Freelancer information in JSON |
| `/freelancer/{freelancerid}/post/{postid}` | POST | Adds a freelancer to a post | {freelancerid, postid} | HTTP Status OK |
| `/freelancer/freelancer/{id}` | PATCH | Updates a freelancer's information | {id, {properties} } | HTTP Status OK |
| `/freelancer/freelancer/{freelancerid}` | DELETE | Deletes a freelancer by ID (Admin only) | {id} | HTTP Status OK |
| `/freelancer/upload/{freelancerid}` | PATCH | Uploads an image for a freelancer | {id, MultipartFile} | HTTP Status OK |

# Customer Endpoints 
All API endpoints start with: http://localhost:2019/
The following table provides an overview of the API endpoints available in the CustomerController class:
| Endpoint | HTTP Method | Description | Parameters | Response |
|---|---|---|---|---|
| `/customer/customers` | GET | Fetches all customers (Admin only) | N/A | List of Customers in JSON |
| `/customer/customer/{customerid}` | GET | Fetches customer by ID | { customerid } | Customer information in JSON |
| `/customer/customer/{customerid}` | PATCH | Updates a customer's information | { customerid, {properties} | HTTP Status OK |
| `/customer/customer/{customerid}` | DELETE | Deletes a customer by ID (Admin only) | { customerid } | HTTP Status OK |
| `/customer/upload/{customerid}` | PATCH | Uploads an image for a customer | { customerid", MultipartFile } | HTTP Status OK |

 # Contract Endpoints
All API endpoints start with: http://localhost:2019/
The following table provides an overview of the API endpoints available in the ContractController class:
| Endpoint | HTTP Method | Description | Parameters | Response |
|---|---|---|---|---|
| `/contract/contracts` | GET | Fetches all contracts (Admin or Customer only) | N/A | List of Contracts in JSON |
| `/contract/{id}` | GET | Fetches contract by ID | { "id" } | Contract information in JSON |
| `/contract/new/{fid}/{cid}` | POST | Creates a new contract | { "fid", "cid", "contract" } | HTTP Status OK |
| `/contract/{id}` | PATCH | Updates a contract's information | { "id", "contract" } | HTTP Status OK |
| `/contract/{contractid}` | DELETE | Deletes a contract by ID (Admin only) | { "contractid" } | HTTP Status OK |










