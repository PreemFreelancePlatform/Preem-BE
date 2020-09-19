package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.UserProperty;

import java.util.List;

public interface UserpropertyService {

    List<UserProperty> findAllProperties();

    UserProperty findPropertyById(Long id);

    void delete(Long id);

    UserProperty save(UserProperty property);

    UserProperty update(UserProperty property, Long id);
}
