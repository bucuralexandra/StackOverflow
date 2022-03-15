package com.utcn.assignmentSD.repository;

import com.utcn.assignmentSD.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends CrudRepository<Answer, Integer> {

}
