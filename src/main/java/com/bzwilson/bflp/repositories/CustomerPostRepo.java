package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.CustomerPosts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface CustomerPostRepo extends PagingAndSortingRepository<CustomerPosts, Long> {
//    Page<CustomerPosts> findAllByFieldAndSpecializationInAndBudgetBetween(String field, List<String> specialization, Double min, Double max, Pageable pageable);
//    Page<CustomerPosts> findAllByFieldAndBudgetBetween(String field, Double min, Double max, Pageable pageable);



//    @Query(value = "SELECT * FROM customerpost WHERE field = :field AND roleid = :roleid",
//            nativeQuery = true)
//    JustTheCount checkUserRolesCombo(
//            long userid,
//            long roleid);

}
