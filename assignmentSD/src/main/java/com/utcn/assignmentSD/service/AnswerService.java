package com.utcn.assignmentSD.service;


import com.utcn.assignmentSD.model.*;
import com.utcn.assignmentSD.repository.IAnswerRepository;
import com.utcn.assignmentSD.repository.IQuestionRepository;
import com.utcn.assignmentSD.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    IAnswerRepository iAnswerRepository;
    @Autowired
    IQuestionRepository iQuestionRepository;
    @Autowired
    IUserRepository iUserRepository;

    public List<Answer> getAll() {
        return (List<Answer>) iAnswerRepository.findAll();
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = iAnswerRepository.findById(id);
        return answer.orElse(null);
    }

    public String deleteAnswer(Integer id) {
        try {
            Answer answer = this.getAnswer(id);
            Question question = answer.getQuestionn();
            User user = answer.getUserr();
            user.deleteAnswer(answer);
            question.deleteAnswer(answer);
            iAnswerRepository.delete(this.getAnswer(id));
            return "Delete success.";
        } catch (Exception e) {
            return "Delete failed.";
        }
    }

    public Answer saveAnswer(String text, Integer idQuestion, Integer idUser) {
        Optional<Question> question = iQuestionRepository.findById(idQuestion);
        Optional<User> user = iUserRepository.findById(idUser);

        if (question.isPresent() && user.isPresent()) {
            Question answeredQuestion = question.get();
            User u = user.get();
            Answer answer = new Answer(text, Calendar.getInstance().getTime(), answeredQuestion);
            answeredQuestion.addAnswer(answer);
            u.addAnswer(answer);
            answer.setUserr(u);
            answer.setQuestionn(answeredQuestion);
            return iAnswerRepository.save(answer);
        }
       return null;
    }

    public Answer updateAnswer(Integer id, Answer updatedAnswer) {
        Answer initAnswer = this.getAnswer(id);
        initAnswer.setText(updatedAnswer.getText());
        return iAnswerRepository.save(initAnswer);
    }

    public Question getQuestion(Integer id)
    {
        Answer a = this.getAnswer(id);
        if (a != null)
        {
            return a.getQuestionn();
        }
        return null;
    }

    public User getUser(Integer id)
    {
        Answer a = this.getAnswer(id);
        if (a != null)
        {
            return a.getUserr();
        }
        return null;
    }

    public String computeVote(Integer id)
    {
        Optional<Answer> answer = iAnswerRepository.findById(id);
        if(answer.isPresent())
        {
            int s = 0;
            for(VoteAnswer v: answer.get().getVotes())
            {
                s += v.getVote();
            }
            return "Text: " +answer.get().getText() + "\n" +
                    "Votes: " + s;
        }
        return "Ceva rau";
    }
}
