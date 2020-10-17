package com.apress.controller;

import com.apress.domain.Poll;
import com.apress.repository.PollRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;


@RestController
public class PollController {

    @Inject
    private PollRepository pollRepository;

   /* public static void main(String[] args) {
        SpringApplication.run(PollController.class, args);
    }*/
    
    //Get all polls.
    @RequestMapping(value="/polls",method= RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls(){
            Iterable<Poll> allPolls=pollRepository.findAll();
            return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    //add new poll
    @RequestMapping(value="/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Poll poll){
        poll=pollRepository.save(poll);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }
}
