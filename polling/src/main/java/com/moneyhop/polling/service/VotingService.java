package com.moneyhop.polling.service;

import com.moneyhop.polling.Response;
import com.moneyhop.polling.model.Vote;

public interface VotingService {
	 Response voteToPoll(Vote vote);
}
