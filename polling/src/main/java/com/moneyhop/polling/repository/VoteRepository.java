package com.moneyhop.polling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moneyhop.polling.model.Vote;

public interface VoteRepository  extends JpaRepository<Vote, Long>{

	@Query(value="select count(*) from Votes v where v.poll_option_id = ?1" , nativeQuery = true)
	long votesCountByPoll(long optionId);

	@Query(value="select count(*) from Votes v where v.poll_id = ?1 and user_id = ?2" , nativeQuery = true)
	int dedupVotes(long pollId, long userId);
	
	List<Vote>  getByUserId(long id);
}
