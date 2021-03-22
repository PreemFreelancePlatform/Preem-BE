package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.TagRequest;
import org.springframework.data.repository.CrudRepository;

public interface TagRequestRepo extends CrudRepository<TagRequest, Long> {

    TagRequest findAllByCategoryAP4(String categoryAP4);

}
