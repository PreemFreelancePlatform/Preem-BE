package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.CustomerPosts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerPostRepo extends CrudRepository<CustomerPosts, Long> {

    List<CustomerPosts> findAllByField(String field);


//    @Query(value = "SELECT * FROM customerpost WHERE field = :field AND roleid = :roleid",
//            nativeQuery = true)
//    JustTheCount checkUserRolesCombo(
//            long userid,
//            long roleid);

}
