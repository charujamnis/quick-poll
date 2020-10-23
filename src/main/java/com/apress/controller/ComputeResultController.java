package com.apress.controller;

import com.apress.domain.Vote;
import com.apress.dto.OptionCount;
import com.apress.dto.VoteResult;
import com.apress.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {

    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value="/computeResult", method= RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId){
        VoteResult voteResult=new VoteResult();
        Iterable<Vote> allVotes=voteRepository.findByPoll(pollId);

      //  System.out.println("The votes : "+ allVotes);
        //algorithm to count votes.
        int totalVotes=0;
        Map<Long, OptionCount> tempMap= new HashMap<Long, OptionCount>();
        for(Vote v:allVotes){
            totalVotes++;

            OptionCount optionCount=tempMap.get(v.getOption().getId());
            if(optionCount==null){
                optionCount=new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(),optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1);
        }
        voteResult.setTotalValues(totalVotes);
        voteResult.setResults(tempMap.values());
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}
