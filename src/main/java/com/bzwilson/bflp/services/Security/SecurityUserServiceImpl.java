package com.bzwilson.bflp.services.Security;


// make sure the user that is in the import be the one from this application, not core security
// import org.springframework.security.core.userdetails.User;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.repositories.CustomerRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This implements User Details Service that allows us to authenticate a user.
 */
@Service(value = "securityUserService")
public class SecurityUserServiceImpl
        implements UserDetailsService {
    /**
     * Ties this implementation to the User Repository so we can find a user in the database.
     */
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private FreelancerRepo freerepo;


    /**
     * Verifies that the user is correct and if so creates the authenticated user
     *
     * @param s The user name we are look for
     * @return a security user detail that is now an authenticated user
     * @throws ResourceNotFoundException if the user name is not found
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s)
            throws
            ResourceNotFoundException {

        Customer user = customerRepo.findByUsername(s);

        if (user == null) {
            Freelancer freelancer = freerepo.findByUsername(s);
            return new org.springframework.security.core.userdetails.User(
                    freelancer.getUsername(),
                    freelancer.getPassword(),
                    freelancer.getAuthority());
        } else {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthority());
        }

    }
}
