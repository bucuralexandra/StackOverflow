package com.utcn.assignmentSD.service;

import com.utcn.assignmentSD.model.*;
import com.utcn.assignmentSD.repository.IQuestionRepository;
import com.utcn.assignmentSD.repository.IVoteARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VoteAnswerService {

    @Autowired
    IVoteARepository iVoteARepository;
    @Autowired
    IQuestionRepository iQuestionRepository;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;

    public List<VoteAnswer> getAll() {
        return (List<VoteAnswer>) iVoteARepository.findAll();
    }

    public VoteAnswer getVoteA(Integer id) {
        Optional<VoteAnswer> voteAnswer = iVoteARepository.findById(id);
        return voteAnswer.orElse(null);
    }

    public String deleteVoteA(Integer id) {
        try {
            VoteAnswer voteAnswer = this.getVoteA(id);
            iVoteARepository.delete(this.getVoteA(id));
            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }


    public VoteAnswer saveVoteA(Integer vote, Integer aId, Integer userId){
        List<VoteAnswer> votes = this.getAll();
        List<VoteAnswer> filteredVotes = votes.stream().filter(v-> v.getAnswerV().getId() == aId &&
                v.getUserA().getId() == userId).toList();
        if(filteredVotes.isEmpty()){
            //nu exista astfel de vot
            VoteAnswer voteAnswer = new VoteAnswer(vote);
            Answer a = answerService.getAnswer(aId);
            if(!Objects.equals(a.getUserr().getId(), userId)) {
                a.addVote(voteAnswer);
                User u = userService.getUser(userId);
                u.addVoteA(voteAnswer);
                voteAnswer.setAnswerV(a);
                voteAnswer.setUserA(u);
                return iVoteARepository.save(voteAnswer);
            }
        }
        return null;
    }

    public VoteAnswer updateVoteA(Integer id, VoteAnswer voteAnswer){
        VoteAnswer init= this.getVoteA(id);
        init.setVote(voteAnswer.getVote());
        return iVoteARepository.save(init);
    }
}
