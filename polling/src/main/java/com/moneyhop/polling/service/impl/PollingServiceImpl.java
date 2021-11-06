package com.moneyhop.polling.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moneyhop.polling.Response;
import com.moneyhop.polling.Response.StatusCode;
import com.moneyhop.polling.model.Poll;
import com.moneyhop.polling.model.Vote;
import com.moneyhop.polling.repository.PollingRepository;
import com.moneyhop.polling.repository.VoteRepository;
import com.moneyhop.polling.service.PollingService;

@Component
public class PollingServiceImpl implements PollingService {

	@Autowired
	private PollingRepository pollingRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Override
	public Response create(Poll poll) {
		
		Response rp = validate(poll);
		
		if(rp!=null) {
			return rp;
		}
		
		poll.setActive(true);
		
		try {
			pollingRepository.save(poll);
		} catch (Exception e) {
			Response.failed();
		}
		
		return Response.success();
	}

	private Response validate(Poll poll) {
		
		if(poll.getQuestion()==null||poll.getQuestion().isEmpty()) {
			return Response.mandatory("Question");
		}
		
		if(poll.getOptionsList()==null||poll.getOptionsList().size()<2) {
			return new Response(StatusCode.ERROR, "Atleast two options are mandatory");
		}
		
		return null;
	}

	@Override
	public Response update(Poll poll) {
		
		Response rp = validate(poll);
		
		if(rp!=null) {
			return rp;
		}
		
		try {
			pollingRepository.save(poll);
		} catch (Exception e) {
			Response.failed();
		}
		
		return Response.success();
	}


	@Override
	public Poll getbyId(long id) {
		return pollingRepository.findById(id).get();
	}
	
	@Override
	public Response delete(long id) {
		
		try {
			pollingRepository.deleteById(id);
		} catch (Exception e) {
			Response.failed();
		}
		
		return Response.success();
	}
	
	@Override
	public List<Poll> getActivePolls() {
		return pollingRepository.getActivePolls();
	}

	@Override
	public List<Poll> getPollResults(long Id) {

		List<Vote> votes = voteRepository.getByUserId(Id);

		List<Poll> polls = new ArrayList<>();

		for (Vote vote : votes) {
			Poll poll = pollingRepository.findById(vote.getPollId()).get();

			poll.getOptionsList().forEach(e -> {

				e.setVoteCount(voteRepository.votesCountByPoll(e.getId()));
			});

			polls.add(poll);
		}
		
		return polls;
	}
	

}
