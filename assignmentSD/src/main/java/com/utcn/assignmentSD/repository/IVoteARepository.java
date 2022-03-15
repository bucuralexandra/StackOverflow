package com.utcn.assignmentSD.repository;

import com.utcn.assignmentSD.model.VoteAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoteARepository extends CrudRepository<VoteAnswer,Integer> {
}
