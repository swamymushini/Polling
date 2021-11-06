package com.moneyhop.polling.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moneyhop.polling.Response;
import com.moneyhop.polling.Response.StatusCode;
import com.moneyhop.polling.model.Vote;
import com.moneyhop.polling.repository.PollingRepository;
import com.moneyhop.polling.repository.UserRepository;
import com.moneyhop.polling.repository.VoteRepository;
import com.moneyhop.polling.service.VotingService;

@Component
public class VotingServiceImpl implements VotingService {

	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PollingRepository pollRepository;
	
	@Override
	public Response voteToPoll(Vote vote) {
		Response rp = null;

		rp = validate(vote);

		if (rp != null) {
			return rp;
		}

		try {
			voteRepository.save(vote);
		} catch (Exception e) {
			return Response.failed();
		}
		
		return Response.success();
	}
	
	
	private Response validate(Vote vote) {

		if (vote.getPollId() == null)
			return Response.mandatory("PollId");
		else if (pollRepository.findById(vote.getPollId()).isEmpty())
			return Response.invalid("PollId");

		if (vote.getPollOptionId() == null)
			return Response.mandatory("PollOptionId");
		else if (pollRepository.verifyPollOption(vote.getPollId(), vote.getPollOptionId()) == 0)
			return Response.invalid("PollId & PollOptionId");

		if (vote.getUserId() == null)
			return Response.mandatory("UserId");
		else if (userRepository.findById(vote.getUserId()).isEmpty())
			return Response.invalid("UserId");
		else if (voteRepository.dedupVotes(vote.getPollId(), vote.getUserId()) > 0)
			return new Response(StatusCode.ERROR, "user has already voted for this poll");

		return null;
	}

}
