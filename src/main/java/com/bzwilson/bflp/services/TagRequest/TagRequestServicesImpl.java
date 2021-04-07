package com.bzwilson.bflp.services.TagRequest;
import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.TagRequest;
import com.bzwilson.bflp.repositories.TagRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "tagrequestservices")
public class TagRequestServicesImpl implements TagRequestServices {

    @Autowired
    private TagRequestRepo tagRequestRepo;

    @Override
    public TagRequest findAllByCategory(String category) {
       return tagRequestRepo.findAllByCategory(category);
    }

    @Override
    public List<TagRequest> findAll() {
        List<TagRequest> list = new ArrayList<>();
        tagRequestRepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public TagRequest findTagRequestById(long id)
        throws
        ResourceNotFoundException {
            return tagRequestRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("TagRequest " + id + " not found!"));
    }

    @Override
    public void delete(long id) {
        tagRequestRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TagRequest " + id + " not found!"));
        tagRequestRepo.deleteById(id);
    }

    @Override
    public TagRequest save(TagRequest tagRequest) {
        TagRequest newTagRequest = new TagRequest();

        if (newTagRequest.getRequestid() != 0) {
            newTagRequest.setRequestid(tagRequest.getRequestid());
        }

        newTagRequest.setCategory(tagRequest.getCategory());

        newTagRequest.getTags()
                .clear();
        for(String str : tagRequest.getTags()) {
            newTagRequest.getTags().add(str);
        }

        newTagRequest.getProjects()
                .clear();
        for(String str : tagRequest.getProjects()) {
            newTagRequest.getProjects().add(str);
        }

        newTagRequest.getGithubs()
                .clear();
        for(String str : tagRequest.getGithubs()) {
            newTagRequest.getGithubs().add(str);
        }

        newTagRequest.setFreelancer(tagRequest.getFreelancer());

        return tagRequestRepo.save(newTagRequest);
    }
}

