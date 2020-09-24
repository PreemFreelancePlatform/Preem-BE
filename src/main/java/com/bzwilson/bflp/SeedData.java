package com.bzwilson.bflp;

import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.services.CustomerPost.CustomerPostService;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
        implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    CustomerService customerService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    CustomerPostService customerPostService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args)
            throws
            Exception
    {
//        Role r1 = new Role("admin");
//        Role r2 = new Role("user");
//        Role r3 = new Role("data");

//        r1 = roleService.save(r1);
//        r2 = roleService.save(r2);
//        r3 = roleService.save(r3);

        // admin, data, user
//        ArrayList<UserRoles> admins = new ArrayList<>();
//
//        admins.add(new UserRoles(new User(),
//                                 r1));
//        admins.add(new UserRoles(new User(),
//                                 r2));
//        admins.add(new UserRoles(new User(),
//                                 r3));


//
//
//
        // making a customer in the db
        Customer c1 = new Customer("William",
                           "Wilson",
                           "AgentBillster",
                           "something@yahoo.com",
                            "a password");

        Customer c2 = new Customer("bob",
                "dabob",
                "bb",
                "b@yahoo.com",
                "a v");

        c2.getCustomerposts().add(new CustomerPosts("post1", "description", "using this and that", c2));
        c1.getCustomerposts().add(new CustomerPosts("post2", "another description", "uWOOOOTTTY SCOOOTY and that", c1));

        // making a customer post and assigning it to the customer i made above


        // save it
        customerService.save(c1);
        customerService.save(c2);
//
//        // data, user

//         using JavaFaker create a bunch of regular users
//         https://www.baeldung.com/java-faker
//         https://www.baeldung.com/regular-expressions-java

//        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
//                                                                    new RandomService());
//        Faker nameFaker = new Faker(new Locale("en-US"));
//
//        for (int i = 0; i < 25; i++)
//        {
//            new User();
//            User fakeUser;
//
//            users = new ArrayList<>();
//            users.add(new UserRoles(new User(),
//                                    r2));
//            fakeUser = new User(nameFaker.name()
//                                        .username(),
//                                "password",
//                                nameFaker.internet()
//                                        .emailAddress(),
//                                users);
//            fakeUser.getUseremails()
//                    .add(new Useremail(fakeUser,
//                                       fakeValuesService.bothify("????##@gmail.com")));
//            userService.save(fakeUser);

    }
}