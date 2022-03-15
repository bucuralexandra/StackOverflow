package com.utcn.assignmentSD.controller;

import com.utcn.assignmentSD.model.Answer;
import com.utcn.assignmentSD.model.Question;
import com.utcn.assignmentSD.model.User;
import com.utcn.assignmentSD.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/answers")
public class AnswerController {

    @Autowired
    AnswerService answerService;


    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<Answer> getAnswers() {
        return answerService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAnswer")
    @ResponseBody
    public Answer getAnswer(@RequestParam(name = "id") Integer id) {
        return answerService.getAnswer(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteAnswer")
    @ResponseBody
    public String deleteAnswer(@RequestParam(name = "id") Integer id) {
        return answerService.deleteAnswer(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveAnswer")
    @ResponseBody
    public Answer saveAnswer(@RequestParam (name="questionId") Integer qId,
                             @RequestParam (name="userId") Integer uId,
                             @RequestParam (name="text") String text) {
        return answerService.saveAnswer(text, qId, uId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateAnswer")
    @ResponseBody
    public Answer updateAnswer(@RequestParam(name = "id") Integer id, @RequestBody Answer answer) {
        return answerService.updateAnswer(id, answer);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAnswerQuestion")
    @ResponseBody
    public Question getAnswerQ(@RequestParam(name = "id") Integer id) {
        return answerService.getQuestion(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAnswerUser")
    @ResponseBody
    public User getUser(@RequestParam(name = "id") Integer id) {
        return answerService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getVote")
    @ResponseBody
    public String seeVotes(@RequestParam(name = "id") Integer id) {
        return answerService.computeVote(id);
    }
}