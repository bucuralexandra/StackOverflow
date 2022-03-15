package com.utcn.assignmentSD.controller;

import com.utcn.assignmentSD.model.VoteQuestion;
import com.utcn.assignmentSD.service.VoteQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/votesQ")
public class VoteQuestionController {

    @Autowired
    VoteQuestionService voteQuestionService;


    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<VoteQuestion> getAll() {
        return voteQuestionService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getVoteQ")
    @ResponseBody
    public VoteQuestion getVoteQ(@RequestParam(name = "id") Integer id) {
        return voteQuestionService.getVoteQ(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteVoteQ")
    @ResponseBody
    public String deleteVoteQ(@RequestParam(name = "id") Integer id) {
        return voteQuestionService.deleteVoteQ(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveVoteQ")
    @ResponseBody
    public VoteQuestion saveVoteQ(@RequestParam(name="userId") Integer userId,
                                  @RequestParam(name="qId") Integer qId,
                                  @RequestParam(name="vote") Integer vote) {
        return voteQuestionService.saveVoteQ(vote,qId,userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateVoteQ")
    @ResponseBody
    public VoteQuestion updateVoteQ(@RequestParam(name = "id") Integer id, @RequestBody VoteQuestion voteQuestion) {
        return voteQuestionService.updateVoteQ(id, voteQuestion);
    }
}
