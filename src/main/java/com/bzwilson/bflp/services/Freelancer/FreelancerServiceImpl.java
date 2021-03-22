package com.bzwilson.bflp.services.Freelancer;

import com.bzwilson.bflp.HelperFunctions.HelperFunctions;
import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.exceptions.RestrictionException;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.models.TagRequest;
import com.bzwilson.bflp.repositories.CustomerPostRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "freelancerService")
public class FreelancerServiceImpl implements FreelancerService {


    @Autowired
    private FreelancerRepo freerepo;

    @Autowired
    private CustomerPostRepo postRepo;

    @Autowired
    private HelperFunctions helperFunctions;
    //    List<Freelancer> findAllByCategoryAndTagsIn(String category, List<String> tags);
    //    List<Freelancer> findAllByCategory(String category);

//    @Override
//    public List<Freelancer> findAllByCategoryOrTagsIn(String category, List<String> tags)
//    {
//        return freerepo.findAllByCategoryOrTagsIn(category, tags);
//    }

//    @Override
//    public Freelancer findByUsername(String username) {
//        Freelancer uu = freerepo.findByUsername(username.toLowerCase());
//        // get uu and uncompress image and add back to uu then return
////        uu.setPicByte(helperFunctions.decompressBytes(uu.getPicByte()));
//        if (uu == null) {
//            throw new ResourceNotFoundException("User name " + username + " not found!");
//        }
//        return uu;
//    }

    @Override
    public Freelancer findByEmail(String email) {
        Freelancer ff = freerepo.findByEmail(email);
        if (ff == null) {
            throw new ResourceNotFoundException("freelancer email " + email + " not found!");
        }
        return ff;
    }

    @Override
    public List<Freelancer> findAll() {
        List<Freelancer> list = new ArrayList<>();
        freerepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Freelancer FindFreelancerById(long id)
            throws
            ResourceNotFoundException {
        return freerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer " + id + " not found!"));
    }


    @Override
    public void delete(long id) {
       Freelancer freelancerDelete =  freerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer " + id + " not found!"));
        freerepo.deleteById(freelancerDelete.getFreelancerid());
    }

    @Transactional
    @Override
    public Freelancer save(Freelancer freelancer) {

        Freelancer newfreelancer = new Freelancer();

        if (freelancer.getFreelancerid() != 0) {
            freerepo.findById(freelancer.getFreelancerid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + freelancer.getFreelancerid() + " not found!"));
            newfreelancer.setFreelancerid(freelancer.getFreelancerid());
        }

        newfreelancer.setEmail(freelancer.getEmail());

        newfreelancer.setFirstname(freelancer.getFirstname());

        newfreelancer.setLastname(freelancer.getLastname());

        newfreelancer.setPasswordnoEncrypt(freelancer.getPassword());

        newfreelancer.setLOCKED_role(freelancer.getLOCKED_role());

        newfreelancer.setSetup(freelancer.getSetup());

        newfreelancer.setVerified(freelancer.getVerified());

        newfreelancer.setQuestion1(freelancer.getQuestion1());

        newfreelancer.setSecurity1(freelancer.getSecurity1());

        newfreelancer.setQuestion2(freelancer.getQuestion2());


        newfreelancer.setSecurity2(freelancer.getSecurity2());

        newfreelancer.setTags(freelancer.getTags());

        newfreelancer.setCategories(freelancer.getCategories());

        newfreelancer.setPicByte(freelancer.getPicByte());


        newfreelancer.getTagRequests()
                .clear();
        for (TagRequest tr : freelancer.getTagRequests()) {
            newfreelancer.getTagRequests()
                    .add(new TagRequest(tr.getCategoryAP4(), tr.getTagAP4(), tr.getProjlink(), tr.getFreelancer()));
        }


        newfreelancer.getCustomerposts()
                .clear();
        for (CustomerPosts cp : freelancer.getCustomerposts()) {
            newfreelancer.getCustomerposts()
                    .add(new CustomerPosts( cp.getCustomer(), cp.getTask(), cp.getDescription(), cp.getCategory(), cp.getTags(), cp.getBudget(), cp.getDuedate(), cp.getPostdate(), cp.getFreelancers()));
        }


        return freerepo.save(newfreelancer);
    }

    @Transactional
    @Override
    public Freelancer update(
            Freelancer freelancer,
            long id) {

        Freelancer currentfreelancer = FindFreelancerById(id);

        // WILL I NEED THIS LATER??
//        if (helper.isAuthorizedToMakeChange(currentUser.getUsername())) {

        if (freelancer.getEmail() != null) {
            currentfreelancer.setEmail(freelancer.getEmail());
        }

        if (freelancer.getFirstname() != null) {
            currentfreelancer.setFirstname(freelancer.getFirstname());
        }

        if (freelancer.getLastname() != null) {
            currentfreelancer.setLastname(freelancer.getLastname());
        }

        if (freelancer.getPassword() != null) {
            currentfreelancer.setPasswordnoEncrypt(freelancer.getPassword());
        }


        if (freelancer.getLOCKED_role() != null) {
            throw new RestrictionException("you cannot change your role");
        }

        if (freelancer.getSetup() != null) {
            currentfreelancer.setSetup(freelancer.getSetup());
        }


        if (freelancer.getVerified() != null) {
            currentfreelancer.setVerified(freelancer.getVerified());
        }

        if (freelancer.getQuestion1() != null) {
            currentfreelancer.setQuestion1(freelancer.getQuestion1());
        }

        if (freelancer.getSecurity1() != null) {
            currentfreelancer.setSecurity1(freelancer.getSecurity1());

        }

        if (freelancer.getQuestion2() != null) {
            currentfreelancer.setQuestion2(freelancer.getQuestion2());
        }

        if (freelancer.getSecurity2() != null) {
            currentfreelancer.setSecurity2(freelancer.getSecurity2());
        }

        if (freelancer.getTags() != null) {
            currentfreelancer.setTags(freelancer.getTags());
        }

        if (freelancer.getCategories() != null) {
            currentfreelancer.setCategories(freelancer.getCategories());
        }

        if (freelancer.getPicByte() != null) {
            currentfreelancer.setPicByte(freelancer.getPicByte());
        }

        if (freelancer.getCustomerposts().size() > 0) {
            for (CustomerPosts cp : freelancer.getCustomerposts()) {
                currentfreelancer.getCustomerposts()
                        .add(new CustomerPosts( cp.getCustomer(), cp.getTask(), cp.getDescription(), cp.getCategory(), cp.getTags(), cp.getBudget(), cp.getDuedate(), cp.getPostdate(), cp.getFreelancers()));
            }

        }

        return freerepo.save(currentfreelancer);
    }




}
//


