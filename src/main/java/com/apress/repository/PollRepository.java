package com.apress.repository;

import com.apress.domain.Option;
import com.apress.domain.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository  extends CrudRepository<Poll, Long> {
   // Poll findOne(Long pollId);
    //   void deleteById(ID var1);
    //void delete(Long pollId);
}
