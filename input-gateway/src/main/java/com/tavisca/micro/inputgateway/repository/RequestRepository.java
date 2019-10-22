package com.tavisca.micro.inputgateway.repository;

import com.tavisca.micro.inputgateway.model.RequestTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestTransaction,Long> {
}
