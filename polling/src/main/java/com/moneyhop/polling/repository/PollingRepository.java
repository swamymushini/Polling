package com.moneyhop.polling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moneyhop.polling.model.Poll;

public interface PollingRepository  extends JpaRepository<Poll, Long>{

	@Query( value="select * from polls p where active = 1", nativeQuery = true)
	List<Poll> getActivePolls();
	
	@Query( value="select count(*) from poll_options p where poll_id = ?1 and id = ?2", nativeQuery = true)
	int verifyPollOption(long pollId, long pollOptionId);
	
}
 