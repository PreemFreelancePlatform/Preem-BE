package com.bzwilson.bflp.services.CustomerPost;

import com.bzwilson.bflp.HelperFunctions.HelperFunctions;
import com.bzwilson.bflp.exceptions.ResourceFoundException;
import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.exceptions.RestrictionException;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.models.JsonPage;
import com.bzwilson.bflp.repositories.CustomerPostRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

@Transactional
@Service(value = "customerPostService")
public class CustomerPostServiceImpl implements CustomerPostService {

    @Autowired
    private CustomerPostRepo customerpostrepo;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FreelancerRepo freerepo;

    @Autowired
    private FreelancerService freelancerService;


    @Autowired
    private HelperFunctions helperFunctions;

//    @Override
//    public List<CustomerPosts> findAll(Pageable pageable) {
//        List<CustomerPosts> list = new ArrayList<>();
//        customerpostrepo.findAll()
//                .iterator()
//                .forEachRemaining(list::add);
//        return list;
//    }

    @Override
    public Page<CustomerPosts> findAll(int page, int size) {
            Pageable pr = PageRequest.of(page, size);
            return new JsonPage<CustomerPosts>(customerpostrepo.findAll(pr), pr);
    }

    @Override
    public Page<CustomerPosts> findAllByCategoryInAndTagsInAndBudgetBetween(List<String> category, List<String> tags, Double min, Double max, int page, Pageable pageable) {
        return new JsonPage<CustomerPosts>(customerpostrepo.findAllByCategoryInAndTagsInAndBudgetBetween(category, tags, min, max, pageable), pageable);
    }

    @Override
    public Page<CustomerPosts> findAllByCategoryInAndBudgetBetween(List<String>  category, Double min, Double max, int page, Pageable pageable) {
        Pageable pr = PageRequest.of(page, 12);
        return new JsonPage<CustomerPosts>(customerpostrepo.findAllByCategoryInAndBudgetBetween(category, min, max, pageable), pageable);
    }

    @Override
    public CustomerPosts findByCustomerPostId(long id)
            throws
            ResourceNotFoundException {
        return customerpostrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer post " + id + " not found!"));
    }


    @Override
    public void delete(long id) {
        customerpostrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer post " + id + " not found!"));
        customerpostrepo.deleteById(id);
    }

    @Override
    public CustomerPosts removeFreelancerFromPost(long pid, long fid) {
        Freelancer fr = freelancerService.findFreelancerById(fid);
        CustomerPosts newpost = findByCustomerPostId(pid);

        if(newpost.getFreelancers().contains(fr)) {
          newpost.getFreelancers().remove(fr);
        } else {
            throw new ResourceFoundException("Freelancer" + fr.getFreelancerid() + "aint on this post big homie");
        }
        return customerpostrepo.save(newpost);
    }

    @Transactional
    @Override
    public CustomerPosts save(CustomerPosts customerposts) {

        // making new customerpost object
        CustomerPosts newCustomerPosts = new CustomerPosts();

        // if we get an id back then set it to new customer
        if (customerposts.getPostid() != 0) {
//            Customer oldCustomer = customerrepo.findById(customer.getCustomerid())
//                    .orElseThrow(() -> new ResourceNotFoundException("User id " + customer.getCustomerid() + " not found!"));
            newCustomerPosts.setPostid(customerposts.getPostid());
        }

        newCustomerPosts.setTask(customerposts.getTask());

        newCustomerPosts.setDescription(customerposts.getDescription());

        newCustomerPosts.setBudget(customerposts.getBudget());

        newCustomerPosts.setDuedate(customerposts.getDuedate());

        newCustomerPosts.setPostdate(customerposts.getPostdate());

        newCustomerPosts.setCategory(customerposts.getCategory());

        newCustomerPosts.setTags(customerposts.getTags());

        newCustomerPosts.getFreelancers()
                .clear();
        for (Freelancer fl : customerposts.getFreelancers()) {
            newCustomerPosts.getFreelancers()
                    .add(new Freelancer(fl.getEmail(), fl.getFirstname(), fl.getLastname(), fl.getPassword(), fl.getLOCKED_role(), fl.getSetup(), fl.getVerified(), fl.getQuestion1(), fl.getSecurity1(), fl.getQuestion2(), fl.getSecurity2(), fl.getTags(), fl.getCategories(),  fl.getPicByte()));
        }

        newCustomerPosts.setCustomer(customerposts.getCustomer());

        return customerpostrepo.save(newCustomerPosts);
    }

    @Transactional
    @Override
    public CustomerPosts update(
            CustomerPosts customerpost,
            long id) {

        CustomerPosts currentcustomerposts = findByCustomerPostId(id);

        // WILL I NEED THIS LATER?? yes ---------------------------------------------------------------------------
//        if (helper.isAuthorizedToMakeChange(currentUser.getUsername())) {

        if (customerpost.getTask() != null) {
            currentcustomerposts.setTask(customerpost.getTask());
        }

        if (customerpost.getDescription() != null) {
            currentcustomerposts.setDescription(customerpost.getDescription());
        }

        if (customerpost.getBudget() != null) {
            currentcustomerposts.setBudget(customerpost.getBudget());
        }

        if (customerpost.getDuedate() != null) {
            currentcustomerposts.setDuedate(customerpost.getDuedate());
        }

        if (customerpost.getPostdate() != null) {
            currentcustomerposts.setPostdate(customerpost.getPostdate());
        }

        if (customerpost.getCategory() != null) {
            currentcustomerposts.setCategory(customerpost.getCategory());
        }

        if (customerpost.getTags() != null) {
            currentcustomerposts.setTags(customerpost.getTags());
        }

        if (customerpost.getFreelancers()
                .size() > 0) {
            currentcustomerposts.getFreelancers().clear();
            for (Freelancer fl : customerpost.getFreelancers()) {
                currentcustomerposts.getFreelancers()
                        .add(new Freelancer(fl.getEmail(), fl.getFirstname(), fl.getLastname(), fl.getPassword(), fl.getLOCKED_role(), fl.getSetup(), fl.getVerified(), fl.getQuestion1(), fl.getSecurity1(), fl.getQuestion2(), fl.getSecurity2(), fl.getTags(), fl.getCategories(),  fl.getPicByte()));
            }
        }

        return customerpostrepo.save(currentcustomerposts);
    }


    @Override
    public CustomerPosts apply(long fid, long pid) {
        CustomerPosts cp = customerpostrepo.findById(pid).orElseThrow(EntityNotFoundException::new);
        Freelancer fl = freerepo.findById(fid).orElseThrow(EntityNotFoundException::new);

        if(cp.getFreelancers().contains(fl)) {
            throw new RestrictionException("You have already applied to this post");
        } else {
            cp.getFreelancers().add(fl);
        }

        return customerpostrepo.save(cp);
    }

    @Override
    public List<CustomerPosts> findAllByCustomer(Customer customer)
            throws
            ResourceNotFoundException {
        return customerpostrepo.findAllByCustomer(customer);
    }


}
