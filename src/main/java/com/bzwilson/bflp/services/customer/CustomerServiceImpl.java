package com.bzwilson.bflp.services.customer;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerrepo;


    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        customerrepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }


    @Override
    public Customer findCustomerById(long id)
            throws
            ResourceNotFoundException {
        return customerrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found!"));
    }


    @Transactional
    @Override
    public void delete(long id) {
        customerrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found!"));
        customerrepo.deleteById(id);
    }


    // NOTE THAT YOU NEED TO ENCRYPT PASSWORD ON MODELS AND HERE AS WELL
    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();

        if (customer.getCustomerid() != 0) {
            Customer oldCustomer = customerrepo.findById(customer.getCustomerid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + customer.getCustomerid() + " not found!"));

            // delete the roles for the old user we are replacing
            //not using roles rn
//            for (UserRole ur: User.getUserroles() {
//                 oldCustomer.getCustomerposts()) {
//                deleteUserRole(ur.getUser()
//                                .getUserid(),
//                        ur.getRole()
//                                .getRoleid());
//            }
//            newUser.setUserid(user.getUserid());
//        }

            customer.set(customer.getUsername()
                    .toLowerCase());
            customer.setPasswordNoEncrypt(customer.getPassword());
            customer.setPrimaryemail(customer.getPrimaryemail()
                    .toLowerCase());

            newUser.getRoles()
                    .clear();
            if (user.getUserid() == 0) {
                for (UserRoles ur : user.getRoles()) {
                    Role newRole = roleService.findRoleById(ur.getRole()
                            .getRoleid());

                    newUser.addRole(newRole);
                }
            } else {
                // add the new roles for the user we are replacing
                for (UserRoles ur : user.getRoles()) {
                    addUserRole(newUser.getUserid(),
                            ur.getRole()
                                    .getRoleid());
                }
            }

            newUser.getUseremails()
                    .clear();
            for (Useremail ue : user.getUseremails()) {
                newUser.getUseremails()
                        .add(new Useremail(newUser,
                                ue.getUseremail()));
            }

            newUser.getUserprops()
                    .clear();
            for (UserProperty uu : user.getUserprops()) {
                newUser.getUserprops()
                        .add(new UserProperty(uu.getName(), uu.getBedrooms(), uu.getNeighbourhood(), uu.getRoomtype(), uu.getMinimumnights(), uu.getNumberofreviews(), uu.getPrice(), newUser));
            }

            return userrepos.save(newUser);
        }

        @Override
        public Customer update (Customer customer,long id){
            return null;
        }


    }
