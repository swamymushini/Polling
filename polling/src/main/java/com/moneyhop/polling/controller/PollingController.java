package com.moneyhop.polling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhop.polling.Response;
import com.moneyhop.polling.model.Poll;
import com.moneyhop.polling.service.PollingService;

@RestController
public class PollingController {

	@Autowired
	private PollingService pollService;
	
	@PostMapping("/createPoll")
	public Response createPoll(@RequestBody Poll poll) {
		
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println(principal.getUsername());
		
		return pollService.create(poll);
	}
	
	@PutMapping("/updatePoll")
	public Response updatePoll(@RequestBody Poll poll) {
		return pollService.update(poll);
	}
	
	@DeleteMapping("/deletePoll/{id}")
	public Response deletePoll(@PathVariable long id) {
		return pollService.delete(id);
	}
	
	@GetMapping("/getPoll/{id}")
	public Poll getPoll(@PathVariable long id) {
		return pollService.getbyId(id);
	}
	
	@GetMapping("/getActivePolls")
	public List<Poll> getActivePolls(){
		return pollService.getActivePolls();
	}
	
	@GetMapping("/getPollResults/{id}")
	public List<Poll> getPollResults(@PathVariable("id") long id){
		return pollService.getPollResults(id);
	}
	
}
