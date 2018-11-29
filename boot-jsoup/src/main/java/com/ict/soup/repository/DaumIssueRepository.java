package com.ict.soup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ict.soup.collection.DaumIssue;

public interface DaumIssueRepository extends MongoRepository<DaumIssue,String> {

}