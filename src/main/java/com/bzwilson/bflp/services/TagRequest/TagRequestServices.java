package com.bzwilson.bflp.services.TagRequest;
import com.bzwilson.bflp.models.TagRequest;

import java.util.List;

public interface TagRequestServices {

    TagRequest findAllByCategory(String category);

    List<TagRequest> findAll();

    TagRequest findTagRequestById(long id);

    void delete(long id);

    TagRequest save(TagRequest tagRequest);

}
