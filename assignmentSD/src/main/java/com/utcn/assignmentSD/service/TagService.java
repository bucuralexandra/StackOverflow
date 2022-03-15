package com.utcn.assignmentSD.service;

import com.utcn.assignmentSD.model.Tag;
import com.utcn.assignmentSD.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    public ITagRepository iTagRepository;

    public List<Tag> getAllTags()
    {
        return (List<Tag>) iTagRepository.findAll();
    }

    public Tag getById(Integer id)
    {
      Optional<Tag> tag = iTagRepository.findById(id);
      return tag.orElse(null);
    }

    public String deleteTag(Integer id) {
        try {
            iTagRepository.delete(this.getById(id));
            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }

    public Tag saveTag(Tag tag)
    {
        return iTagRepository.save(tag);
    }

    public Tag updateTag(Integer id, Tag tag)
    {
        Tag initialTag = this.getById(id);
        initialTag.setName(tag.getName());
        return iTagRepository.save(initialTag);
    }

    public List<Tag> getByTitle(String title) {
        List<Tag> allT = this.getAllTags();
        return  allT.stream().filter(tag -> tag.getName().toLowerCase().contains(title.toLowerCase())).toList();
    }

    public Tag getByTitleTag(String title) {
        List<Tag> allT = this.getAllTags();
        Optional<Tag> t =  allT.stream().filter(tag -> tag.getName().equalsIgnoreCase(title)).findFirst();
        return t.orElse(null);
    }
}
