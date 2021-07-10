package com.pluralsight.conference.controllers;


import com.pluralsight.conference.models.Session;
import com.pluralsight.conference.models.Speaker;
import com.pluralsight.conference.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    @Autowired
    SpeakerRepository speakerRepository;


    @GetMapping
    public List<Speaker> speakers(){
        return speakerRepository.findAll();
    }
    @GetMapping
    @RequestMapping("{id}")
    public Speaker speaker(@PathVariable long id)
    {
        return speakerRepository.getById(id);
    }
    @PostMapping
    public Speaker create(@RequestBody final Speaker session){
        return speakerRepository.saveAndFlush(session);
    }
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable  Long id){
        speakerRepository.deleteById(id);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existing = speakerRepository.getById(id);
        BeanUtils.copyProperties(speaker, existing , "speaker_id");
        return speakerRepository.saveAndFlush(existing);
    }

}
