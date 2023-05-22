# Preem. the better freelance platform

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

1. install JDK 16
2. install intellij
3. git clone (https://github.com/PreemFreelancePlatform/Preem-BE)
4. mvn clean install
5. mvn spring-boot:run` || press run


## For Auth token
All API endpoints start with: `http://localhost:2019`
To access endpoints requiring authentication, you will need to obtain a token. This can be done by making a request to the authorization endpoint with the appropriate credentials.

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

# Freelancer Endpoints
All API endpoints start with: `http://localhost:2019`
The following table provides an overview of the API endpoints available in the `FreelancerController` class:
| Endpoint                                               | Description                                                            | Role Access |
| ------------------------------------------------------ | ---------------------------------------------------------------------- | ----------- |
| `GET /freelancer/freelancer/{email}`                   | Retrieves a `Freelancer` object by the specified email.                | ADMIN, CUSTOMER, FREELANCER |
| `GET /freelancer/freelancers`                          | Retrieves a list of all freelancers.                                   | ADMIN, CUSTOMER |
| `GET /freelancer/freelancer/{id}`                      | Retrieves a `Freelancer` object by the specified ID.                   | ADMIN, CUSTOMER, FREELANCER |
| `POST /freelancer/{freelancerid}/post/{postid}`        | Adds a freelancer to a post by the specified IDs.                      | ADMIN, FREELANCER |
| `PATCH /freelancer/freelancer/{id}`                    | Updates a `Freelancer` object by the specified ID.                     | ADMIN, FREELANCER |
| `DELETE /freelancer/freelancer/{freelancerid}`         | Deletes a `Freelancer` object by the specified ID.                     | ADMIN |
| `PATCH /freelancer/upload/{freelancerid}`              | Uploads an image for the specified freelancer ID.                      | ADMIN, FREELANCER |



# Customer Endpoints 
All API endpoints start with: `http://localhost:2019`
The following table provides an overview of the API endpoints available in the `FreelancerController` class:
| Endpoint                                  | Description                                          | Role Access |
| ----------------------------------------- | ---------------------------------------------------- | ----------- |
| `GET /customer/customers`                 | Retrieves a list of all customers.                   | ADMIN, FREELANCER |
| `GET /customer/customer/{email}`          | Retrieves a `Customer` object by the specified email.| ADMIN, FREELANCER, CUSTOMER |
| `GET /customer/customer/{id}`             | Retrieves a `Customer` object by the specified ID.   | ADMIN, FREELANCER, CUSTOMER |
| `POST /customer/customer`                 | Creates a new `Customer` object.                     | ADMIN |
| `PATCH /customer/customer/{id}`           | Updates a `Customer` object by the specified ID.     | ADMIN, CUSTOMER |
| `DELETE /customer/customer/{customerId}`  | Deletes a `Customer` object by the specified ID.     | ADMIN |

