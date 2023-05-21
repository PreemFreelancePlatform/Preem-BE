# Preem. the better freelance platform

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

1. install JDK 16
2. install intellij
3. git clone (https://github.com/PreemFreelancePlatform/Preem-BE)
4. mvn clean install
5. mvn spring-boot:run` || press run

# FreelancerController API Endpoints
The following table provides an overview of the API endpoints available in the `FreelancerController` class:

| Endpoint                                                   | Description                                                                                                                           |
| ---------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `GET /freelancer/freelancer/{email}`                        | Retrieves a `Freelancer` object by the specified email.                                                                                |
| `GET /freelancer/freelancers`                               | (ADMIN or CUSTOMER) Retrieves a list of all freelancers.                                                                               |
| `GET /freelancer/freelancer/{id}`                           | (ADMIN or CUSTOMER or FREELANCER) Retrieves a `Freelancer` object by the specified ID.                                                 |
| `POST /freelancer/{freelancerid}/post/{postid}`             | (ADMIN or FREELANCER) Adds a freelancer to a post by the specified IDs.                                                                |
| `PATCH /freelancer/freelancer/{id}`                         | (ADMIN or FREELANCER) Updates a `Freelancer` object by the specified ID.                                                               |
| `DELETE /freelancer/freelancer/{freelancerid}`              | (ADMIN) Deletes a `Freelancer` object by the specified ID.                                                                             |
| `PATCH /freelancer/upload/{freelancerid}`                   | (ADMIN or FREELANCER) Uploads an image for the specified freelancer ID.                                                                |

Please note that the descriptions provided are based on the limited information available in the code snippets. You may need to further refine and customize the descriptions based on your specific application and business logic.

