package com.tavisca.micro.datavalidator.repository;

import com.tavisca.micro.datavalidator.model.RequestTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestTransactionRepository extends JpaRepository<RequestTransaction,Long> {

}
