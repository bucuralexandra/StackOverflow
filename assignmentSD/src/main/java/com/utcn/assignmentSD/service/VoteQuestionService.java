package com.utcn.assignmentSD.service;

import com.utcn.assignmentSD.model.Question;
import com.utcn.assignmentSD.model.User;
import com.utcn.assignmentSD.model.VoteQuestion;
import com.utcn.assignmentSD.repository.IQuestionRepository;
import com.utcn.assignmentSD.repository.IVoteQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteQuestionService {

    @Autowired
    IVoteQRepository iVoteQRepository;
    @Autowired
    IQuestionRepository iQuestionRepository;
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    public List<VoteQuestion> getAll() {
        return (List<VoteQuestion>) iVoteQRepository.findAll();
    }

    public VoteQuestion getVoteQ(Integer id) {
        Optional<VoteQuestion> voteQuestion = iVoteQRepository.findById(id);
        return voteQuestion.orElse(null);
    }

    public String deleteVoteQ(Integer id) {
        try {
            VoteQuestion voteQuestion = this.getVoteQ(id);
            iVoteQRepository.delete(this.getVoteQ(id));
            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }


    public VoteQuestion saveVoteQ(Integer vote, Integer qId, Integer userId){
        List<VoteQuestion> votes = this.getAll();
        List<VoteQuestion> filteredVotes = votes.stream().filter(v-> v.getQuestion().getId() == qId &&
                v.getUserQ().getId() == userId).toList();
        if(filteredVotes.isEmpty()){
            //nu exista astfel de vot
            VoteQuestion voteQuestion = new VoteQuestion(vote);
            Question q = questionService.getQuestion(qId);
            if( q.getUser().getId() != userId) {
                q.addVote(voteQuestion);
                User u = userService.getUser(userId);
                u.addVoteQ(voteQuestion);
                voteQuestion.setQuestion(q);
                voteQuestion.setUserQ(u);
                return iVoteQRepository.save(voteQuestion);
            }
        }
      return null;
    }

    public VoteQuestion updateVoteQ(Integer id, VoteQuestion voteQuestion){
        VoteQuestion init= this.getVoteQ(id);
        init.setVote(voteQuestion.getVote());
        return iVoteQRepository.save(init);
    }

}
