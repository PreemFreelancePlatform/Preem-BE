package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.CustomerPosts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface CustomerPostRepo extends PagingAndSortingRepository<CustomerPosts, Long> {
    Page<CustomerPosts> findAllByCategoryInAndTagsInAndBudgetBetween(List<String> category, List<String> tags, Double min, Double max, Pageable pageable);
    Page<CustomerPosts> findAllByCategoryInAndBudgetBetween(List<String>  category, Double min, Double max, Pageable pageable);



//    @Query(value = "SELECT * FROM customerpost WHERE field = :field AND roleid = :roleid",
//            nativeQuery = true)
//    JustTheCount checkUserRolesCombo(
//            long userid,
//            long roleid);

}
