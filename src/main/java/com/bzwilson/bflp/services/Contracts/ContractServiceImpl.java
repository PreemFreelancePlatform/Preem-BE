package com.bzwilson.bflp.services.Contracts;


import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.exceptions.RestrictionException;
import com.bzwilson.bflp.models.Contract;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.repositories.ContractRepo;
import com.bzwilson.bflp.repositories.CustomerRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "contractService")
public class ContractServiceImpl implements ContractService {


    @Autowired
    private ContractRepo contractRepo;


    @Autowired
    private FreelancerRepo freelancerRepo;

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public List<Contract> findAll() {
        List<Contract> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        contractRepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Contract findContractById(long id)
                    throws
        ResourceNotFoundException {
            return contractRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("contract " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        contractRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("contract " + id + " not found!"));
        contractRepo.deleteById(id);
    }

    @Override
    public Contract createnew(Contract contract) {

        Contract newcontract = new Contract();

        newcontract.setCustomer(contract.getCustomer());

        newcontract.setFreelancer(contract.getFreelancer());

        newcontract.setPrice(contract.getPrice());

        newcontract.setPaytime(contract.getPaytime());  

        newcontract.setTask(contract.getTask());

        newcontract.setDeliverdate(contract.getDeliverdate());

        newcontract.setDescription(contract.getDescription());

        newcontract.setFreelancerOK(contract.getFreelancerOK());

        newcontract.setClientOK(contract.getClientOK());

        return contractRepo.save(newcontract);

    }

    @Override
    public Contract update(Contract contract, long id) {

        Contract newcontract = findContractById(id);

        // WILL I NEED THIS LATER??
//        if (helper.isAuthorizedToMakeChange(currentCustomer.getEmail())) {

            if (newcontract.getCustomer() != null) {
                newcontract.setCustomer(contract.getCustomer());
            }

            if (newcontract.getFreelancer() != null) {
            newcontract.setFreelancer(contract.getFreelancer());
            }

            if (newcontract.getPrice() != null) {
            newcontract.setPrice(contract.getPrice());
            }

            if (newcontract.getTask() != null) {
                newcontract.setTask(contract.getTask());
            }

            if (newcontract.getDeliverdate() != null) {
                newcontract.setDeliverdate(contract.getDeliverdate());
            }

            if (newcontract.getFreelancerOK()) {
                newcontract.setCustomer(contract.getCustomer());
            }

            if (newcontract.getClientOK()) {
                newcontract.setCustomer(contract.getCustomer());
            }

            if (newcontract.getCustomer() != null) {
                newcontract.setCustomer(contract.getCustomer());
            }

            return contractRepo.save(newcontract);

//        } else {
//
//            throw new ResourceNotFoundException(customer.getFirstname() + customer.getLastname() + " is not authorized to make change");

        }



    @Override
    public List<Contract> findAllByCustomer_Email(String email)
        throws
        ResourceNotFoundException {
        return contractRepo.findAllByCustomer_Email(email);
    }

    @Override
    public List<Contract> findAllByFreelancer_Email(String email)
            throws
            ResourceNotFoundException {
        return contractRepo.findAllByFreelancer_Email(email);
    }



}

