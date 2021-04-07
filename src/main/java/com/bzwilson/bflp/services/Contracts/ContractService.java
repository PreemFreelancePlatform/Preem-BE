package com.bzwilson.bflp.services.Contracts;

import com.bzwilson.bflp.models.Contract;

import java.util.List;

public interface ContractService {

    List<Contract> findAll();

    Contract findContractById(long id);

    void delete(long id);

    Contract createnew(Contract contract, long fid, long cid);

    Contract update(
            Contract contract,
            long id);

}
