package com.moneyhop.polling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhop.polling.Response;
import com.moneyhop.polling.model.Vote;
import com.moneyhop.polling.service.VotingService;

@RestController
public class VotingController {

	@Autowired
	private VotingService votingService;
	
	@PostMapping("/voteToPoll")
	public Response votePoll(@RequestBody Vote vote){
		return votingService.voteToPoll(vote);
	}
	
}
