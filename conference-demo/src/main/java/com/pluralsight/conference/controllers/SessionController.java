package com.pluralsight.conference.controllers;

import com.pluralsight.conference.models.Session;
import com.pluralsight.conference.repositories.SessionRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 *RestController will respond to payloads incoming and outgoing as JSON REST endpoints
 *
 * RequestMapping tells to route what the mapping url will look like
 */
@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    /*
     * If we make a call to api/v1/session it will route to the GetMapping method
     * GetMapping will tell which HTTP verb to use,which will be a get verb to call the endpoint
     */
    /*
     * findALl method will go out and query all the sessions in the database and return them as a list
     * of objects
     */
    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getById(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value="{id}", method= RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }
    @RequestMapping(value = {"id"}, method = RequestMethod.PUT)
    public Session update(@PathVariable Long id,Session session){
        Session existingSession = sessionRepository.getById(id);
        BeanUtils.copyProperties(session,existingSession,"session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
