package com.apress.controller;

import com.apress.domain.Poll;
import com.apress.repository.PollRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;


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

        //set the location header for the newly created resource.
        HttpHeaders responseHeaders = new HttpHeaders();

        //need to understand the code.
        URI newPollUri= ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(poll.getId())
                        .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }
}
