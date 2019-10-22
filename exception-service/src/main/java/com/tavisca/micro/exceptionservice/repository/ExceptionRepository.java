package com.tavisca.micro.exceptionservice.repository;

import com.tavisca.micro.exceptionservice.model.ExceptionModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExceptionRepository extends MongoRepository<ExceptionModel, Long> {


}