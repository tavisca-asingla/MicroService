package com.tavisca.micro.dbservice.repository;

import com.tavisca.micro.dbservice.model.RequestTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestTransactionRepository extends JpaRepository<RequestTransaction,Long> {
}
