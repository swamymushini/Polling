package com.moneyhop.polling.service;

import java.util.List;

import com.moneyhop.polling.Response;
import com.moneyhop.polling.model.Poll;


public interface PollingService {

	Response create(Poll poll);
	
	Response update(Poll poll);
	
	Poll getbyId(long Id);
	
	Response delete(long Id);
	
	List<Poll> getActivePolls();
	
	List<Poll> getPollResults(long Id);
}
