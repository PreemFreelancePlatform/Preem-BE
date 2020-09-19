package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.handlers.HelperFunctions;
import com.lambdaschool.foundation.models.UserProperty;
import com.lambdaschool.foundation.repository.UserPropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userpropertyService")
public class UserPropertyServiceImpl implements UserpropertyService {


    @Autowired
    private UserPropertyRepo propertyRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helper;


    @Override
    public List<UserProperty> findAllProperties() {

        List<UserProperty> list = new ArrayList<>();
        propertyRepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public UserProperty findPropertyById(Long id) {
        return propertyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("property with id " + id + " Not Found!"));
    }

    @Override
    public void delete(Long id) {
        if (propertyRepo.findById(id)
                .isPresent()) {
            if (helper.isAuthorizedToMakeChange(propertyRepo.findById(id)
                    .get()
                    .getUserprop()
                    .getUsername())) {
                propertyRepo.deleteById(id);
            }
        } else {
            throw new ResourceNotFoundException("prop with id " + id + " Not Found!");
        }
    }
//        userrepos.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
//        userrepos.deleteById(id);

    @Transactional
    @Override
    public UserProperty save(UserProperty property) {
        UserProperty newProp = new UserProperty();

        if (property.getPropertyid() != 0) {
            propertyRepo.findById((long) property.getPropertyid())
                    .orElseThrow(() -> new ResourceNotFoundException("PropertyID " + property.getPropertyid() + " not found!"));
        }

        newProp.setName(property.getName());
        newProp.setBedrooms(property.getBedrooms());
        newProp.setNeighbourhood(property.getNeighbourhood());
        newProp.setRoomtype(property.getRoomtype());
        newProp.setMinimumnights(property.getMinimumnights());
        newProp.setNumberofreviews(property.getNumberofreviews());
        newProp.setPrice(property.getPrice());
        newProp.setUserprop(property.getUserprop());

        return propertyRepo.save(newProp);
    }

    @Override
    public UserProperty update(UserProperty property, Long id) {
        UserProperty currentproperty = findPropertyById(id);

        if (helper.isAuthorizedToMakeChange(propertyRepo.findById(id)
                .get()
                .getUserprop()
                .getUsername())) {

            if (property.getName() != null) {
                currentproperty.setName(property.getName().toLowerCase());
            }
            if (property.getBedrooms() != 0) {
                currentproperty.setBedrooms(property.getBedrooms());
            }
            if (property.getNeighbourhood() != null) {
                currentproperty.setNeighbourhood(property.getNeighbourhood().toLowerCase());
            }
            if (property.getRoomtype() != null) {
                currentproperty.setRoomtype(property.getRoomtype());
            }
            if (property.getMinimumnights() != 0) {
                currentproperty.setMinimumnights(property.getMinimumnights());
            }
            if (property.getNumberofreviews() != 0) {
                currentproperty.setNumberofreviews(property.getNumberofreviews());
            }
            if (property.getPrice() != 0) {
                currentproperty.setPrice(property.getPrice());
            }

            return propertyRepo.save(currentproperty);

        } else {
            throw new ResourceNotFoundException("idk what happened but cant update stuff");
        }
    }
}
