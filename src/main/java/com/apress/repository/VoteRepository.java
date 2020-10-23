package com.apress.repository;

import com.apress.domain.Option;
import com.apress.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository
public interface VoteRepository  extends CrudRepository<Vote, Long> {

    @Query(value="select v.* from Option o, Vote v where o.POLL_ID=? and v.OPTION_ID=o.OPTION_ID", nativeQuery=true)
    public Iterable<Vote> findByPoll(Long pollId);
}
