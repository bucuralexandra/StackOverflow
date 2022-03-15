package com.utcn.assignmentSD.repository;

import com.utcn.assignmentSD.model.VoteQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoteQRepository extends CrudRepository<VoteQuestion, Integer> {
}
