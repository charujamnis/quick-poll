package com.apress.controller;

import com.apress.domain.Poll;
import com.apress.repository.PollRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.util.Optional;


@RestController
public class PollController {

    @Inject
    private PollRepository pollRepository;

    //Get all polls.
    @RequestMapping(value="/polls",method= RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls(){
            Iterable<Poll> allPolls=pollRepository.findAll();
            return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    //add new poll
    @RequestMapping(value="/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Poll poll){
        if(poll==null){
            //204 status no content.
            ResponseEntity.noContent().build();
        }
        //we are adding the Poll
        poll=pollRepository.save(poll);
        //set the location header for the newly created resource.
        HttpHeaders responseHeaders = new HttpHeaders();
        //retrieving the id from the poll and using it for creating new URI
        //need to understand the code.
        URI newPollUri= ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(poll.getId())
                        .toUri();
        responseHeaders.setLocation(newPollUri);
        //return ResponseEntity.created(newPollUri).build();
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId){
            Optional<Poll> p = pollRepository.findById(pollId);
            return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        //save the entity
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
        // delete the poll
        //they have given pollrepository.delete(pollId);
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
