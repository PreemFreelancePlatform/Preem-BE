# Preem. the better freelance platform

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

1. install JDK 16
2. install intellij
3. git clone (https://github.com/PreemFreelancePlatform/Preem-BE)
4. mvn clean install
5. mvn spring-boot:run` || press run

# Freelancer Endpoints
The following table provides an overview of the API endpoints available in the `FreelancerController` class:

| Endpoint                                               | Description                                                            | Role Access |
| ------------------------------------------------------ | ---------------------------------------------------------------------- | ----------- |
| `GET /freelancer/freelancer/{email}`                   | Retrieves a `Freelancer` object by the specified email.                | All         |
| `GET /freelancer/freelancers`                          | Retrieves a list of all freelancers.                                   | ADMIN, CUSTOMER |
| `GET /freelancer/freelancer/{id}`                      | Retrieves a `Freelancer` object by the specified ID.                   | ADMIN, CUSTOMER, FREELANCER |
| `POST /freelancer/{freelancerid}/post/{postid}`        | Adds a freelancer to a post by the specified IDs.                      | ADMIN, FREELANCER |
| `PATCH /freelancer/freelancer/{id}`                    | Updates a `Freelancer` object by the specified ID.                     | ADMIN, FREELANCER |
| `DELETE /freelancer/freelancer/{freelancerid}`         | Deletes a `Freelancer` object by the specified ID.                     | ADMIN |
| `PATCH /freelancer/upload/{freelancerid}`              | Uploads an image for the specified freelancer ID.                      | ADMIN, FREELANCER |


Please note that the descriptions provided are based on the limited information available in the code snippets. You may need to further refine and customize the descriptions based on your specific application and business logic.

