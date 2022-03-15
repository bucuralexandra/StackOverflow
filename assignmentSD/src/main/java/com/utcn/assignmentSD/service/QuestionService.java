package com.utcn.assignmentSD.service;

import com.utcn.assignmentSD.model.*;
import com.utcn.assignmentSD.repository.IQuestionRepository;
import com.utcn.assignmentSD.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    IQuestionRepository iQuestionRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    TagService tagService;

    public List<Question> getAllQuestion() {
        return (List<Question>) iQuestionRepository.findAll();
    }

    public List<Question> getAllQuestionSorted() {
        List<Question> q = (List<Question>) iQuestionRepository.findAll();
        q.sort(Comparator.comparing(Question::getCreationDate).reversed());
        return q;
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = iQuestionRepository.findById(id);
        return question.orElse(null);
    }

    public String deleteQuestion(Integer id) {
        try {
            Question q = this.getQuestion(id);
            User u = q.getUser();
            u.deleteQuestion(q);
            iQuestionRepository.delete(this.getQuestion(id));
            return "Delete success.";
        } catch (Exception e) {
            return "Delete failed.";
        }
    }

    public Question saveQuestion(String name, String body, Integer id, List<String> tags) {
        Optional<User> user = iUserRepository.findById(id);
        User insertedUser = user.orElse(null);
        if (user.isPresent()) {
            Question q = new Question(name, body, Calendar.getInstance().getTime(), insertedUser);
            for (String nameTag : tags) {
                Tag t = tagService.getByTitleTag(nameTag);
                if (t != null) {
                    q.addTag(t);
                    t.addQuestion(q);
                } else {
                    Tag newTag = new Tag(nameTag);
                    newTag.addQuestion(q);
                    q.addTag(newTag);
                    tagService.saveTag(newTag);
                }
            }
            insertedUser.addQuestion(q);
            return iQuestionRepository.save(q);
        }
        return null;
    }

    public List<Question> findByTitle(String title) {
        List<Question> allQ = this.getAllQuestion();
        return allQ.stream().filter(question -> question.getTitle().toLowerCase().contains(title.toLowerCase())).toList();
    }

    public List<Question> findByTag(String titleTag) {
        List<Question> allQ = this.getAllQuestion();
        return allQ.stream().filter(question -> question.getTags().stream().anyMatch(t -> t.getName().equalsIgnoreCase(titleTag))).toList();
    }

    public Question updateQuestion(Integer id, List<String> tagNames, String title, String body) {
        Question init = this.getQuestion(id);
        init.setTitle(title);
        init.setBody(body);

        if(!tagNames.isEmpty()) {
            Set<Tag> tags = new HashSet<>();
            for (String name : tagNames) {
                Tag t = tagService.getByTitleTag(name);
                if (t != null) {
                    tags.add(t);
                } else {
                    Tag tag = new Tag(name);
                    tags.add(tag);
                    tagService.saveTag(tag);
                }
            }
            init.setTags(tags);
            for (Tag t : tags) {
                t.addQuestion(init);
            }
        }
        return iQuestionRepository.save(init);
    }
    public String computeVote(Integer id)
    {
        Optional<Question> question = iQuestionRepository.findById(id);
        if(question.isPresent())
        {
            int s = 0;
            for(VoteQuestion v: question.get().getVotes())
            {
                s += v.getVote();
            }
            return "Title:" + question.get().getTitle() + "\n" +
                    "Text: " +question.get().getBody() + "\n" +
                    "Votes: " + s;
        }
        return "Ceva rau";
    }
    public String getAnswers(Integer id)
    {
        String s = "";
        Question q = this.getQuestion(id);

        List<Answer> answers = q.getAnswers().stream().toList();
        answers.stream().sorted((o1,o2)-> o1.compareTo(o2)).collect(Collectors.toList());
        for(Answer a : answers)
        {
            s+= a.toString();
            s+= "\n";
        }
            return s;
    }
}