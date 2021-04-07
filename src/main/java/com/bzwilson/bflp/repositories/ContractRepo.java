package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.Contract;
import org.springframework.data.repository.CrudRepository;

public interface ContractRepo extends CrudRepository<Contract, Long> {
}
