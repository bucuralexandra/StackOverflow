package com.utcn.assignmentSD.controller;

import com.utcn.assignmentSD.model.VoteAnswer;
import com.utcn.assignmentSD.service.VoteAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/votesA")
public class VoteAnswerController {
    @Autowired
    VoteAnswerService voteAnswerService;


    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<VoteAnswer> getAll() {
        return voteAnswerService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getVoteA")
    @ResponseBody
    public VoteAnswer getVoteQ(@RequestParam(name = "id") Integer id) {
        return voteAnswerService.getVoteA(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteVoteA")
    @ResponseBody
    public String deleteVoteA(@RequestParam(name = "id") Integer id) {
        return voteAnswerService.deleteVoteA(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveVoteA")
    @ResponseBody
    public VoteAnswer saveVoteA(@RequestParam(name="userId") Integer userId,
                                  @RequestParam(name="aId") Integer aId,
                                  @RequestParam(name="vote") Integer vote) {
        return voteAnswerService.saveVoteA(vote,aId,userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateVoteA")
    @ResponseBody
    public VoteAnswer updateVoteQ(@RequestParam(name = "id") Integer id, @RequestBody VoteAnswer voteAnswer) {
        return voteAnswerService.updateVoteA(id, voteAnswer);
    }
}
