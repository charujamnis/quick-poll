package com.apress.repository;

import com.apress.domain.Option;
import com.apress.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository  extends CrudRepository<Poll, Long> {
}
