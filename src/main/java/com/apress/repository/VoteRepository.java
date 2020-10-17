package com.apress.repository;

import com.apress.domain.Option;
import com.apress.domain.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository  extends CrudRepository<Vote, Long> {

}
