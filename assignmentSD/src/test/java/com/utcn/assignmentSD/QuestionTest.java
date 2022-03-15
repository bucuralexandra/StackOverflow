package com.utcn.assignmentSD;


import com.utcn.assignmentSD.model.Question;
import com.utcn.assignmentSD.model.Tag;
import com.utcn.assignmentSD.service.AnswerService;
import com.utcn.assignmentSD.service.QuestionService;
import com.utcn.assignmentSD.service.VoteQuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@SpringBootTest
@Transactional
public class QuestionTest {

    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    VoteQuestionService voteQuestionService;


    @Test
    void saveQuestionTest()
    {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("random");
        tags.add("super");
        questionService.saveQuestion("O micuta intrebare","Un body", 6, tags);
        List<Question> qs = questionService.findByTitle("O micuta intrebare");
        Question q = qs.get(0);
        assert(questionService.getQuestion(q.getId())).getTitle().equals("O micuta intrebare");
    }


    @Test
    void deleteQuestionTest()
    {
        List<Question> questionList = questionService.getAllQuestion();
        Question q = questionList.get(0);
        String s = questionService.deleteQuestion(q.getId());
        assert Objects.equals(s, "Delete success.");
        assert questionService.getQuestion(q.getId()) == null;
    }

    @Test
    void changeTagsTest()
    {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("random");
        tags.add("super");
        questionService.saveQuestion("O micuta intrebare","Un body", 6, tags);
        List<Question> qs = questionService.findByTitle("O micuta intrebare");
        Question q = qs.get(0);
        tags.add("super super");
        questionService.updateQuestion(q.getId(),tags,"O micuta intrebare", "Un body");
        Question qq = questionService.getQuestion(q.getId());
        assert sameTags(qq.getTags(),tags);

    }

    private boolean sameTags(Set<Tag> tags, List<String> tagNames) {
        if (tags.size() != tagNames.size()) return false;
        Object[] ts = tags.toArray();
        for(int i = 0; i < tags.size(); i++)
        {
            if(!ts[i].equals(tagNames.get(i))) return true;
        }
        return true;
    }

    @Test
    void computeVoteTest()
    {
        int actualValue = 2;
        Question q = questionService.getQuestion(6);
        int initialValue = q.computeVote();

        assert actualValue == initialValue;

    }
}
