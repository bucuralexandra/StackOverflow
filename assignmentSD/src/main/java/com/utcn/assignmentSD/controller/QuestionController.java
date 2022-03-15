package com.utcn.assignmentSD.controller;

import com.utcn.assignmentSD.model.Question;
import com.utcn.assignmentSD.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllQ")
    @ResponseBody
    public List<Question> getQuestions()
    {
        return questionService.getAllQuestion();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllQSorted")
    @ResponseBody
    public List<Question> getQuestionsSorted()
    {
        return questionService.getAllQuestionSorted();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByTitle")
    @ResponseBody
    public List<Question> findByTitle(@RequestParam(name = "title")String title)
    {
        return questionService.findByTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByTag")
    @ResponseBody
    public List<Question> findByTag(@RequestParam(name = "tag")String tag)
    {
        return questionService.findByTag(tag);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getQuestion")
    @ResponseBody
    public Question getQuestion(@RequestParam(name = "id") Integer id) {
        return questionService.getQuestion(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteQuestion")
    @ResponseBody
    public String deleteQuestion(@RequestParam(name = "id") Integer id) {
        return questionService.deleteQuestion(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveQuestion")
    @ResponseBody
    public Question saveQuestion(@RequestBody Question question, @RequestParam(name="id")Integer id, @RequestParam(name = "tags") List<String> tagNames) {
        return questionService.saveQuestion(question.getTitle(), question.getBody(), id, tagNames);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/updateQ")
    @ResponseBody
    public Question updateQuestion(@RequestParam(name = "id") Integer id,@RequestParam(name = "tags") List<String> tagNames, @RequestParam(name="title") String title, @RequestParam(name="body") String body) {
        return questionService.updateQuestion(id,tagNames,title, body);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getVote")
    @ResponseBody
    public String seeVotes(@RequestParam(name = "id") Integer id) {
        return questionService.computeVote(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/seeAnswers")
    @ResponseBody
    public String seeAnswers(@RequestParam(name = "id") Integer id) {
        return questionService.getAnswers(id);
    }
}