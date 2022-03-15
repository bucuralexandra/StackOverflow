package com.utcn.assignmentSD.controller;

import com.utcn.assignmentSD.model.Tag;
import com.utcn.assignmentSD.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/tags")
public class TagController {

    @Autowired
    TagService tagService;


    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<Tag> getTags() {
        return tagService.getAllTags();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTag")
    @ResponseBody
    public Tag getTag(@RequestParam(name = "id") Integer id) {
        return tagService.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTagByTitle")
    @ResponseBody
    public List<Tag> getTagbyTitle(@RequestParam(name = "name") String title) {
        return tagService.getByTitle(title);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteTag")
    @ResponseBody
    public String deleteTag(@RequestParam(name = "id") Integer id) {
        return tagService.deleteTag(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateTag")
    @ResponseBody
    public Tag updateTag(@RequestParam(name = "id") Integer id, @RequestBody Tag tag) {
        return tagService.updateTag(id, tag);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveTag")
    @ResponseBody
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }
}