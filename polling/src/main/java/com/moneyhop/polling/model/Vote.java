package com.moneyhop.polling.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name ="Votes")
@Entity
public class Vote {

	@Id
	@GeneratedValue
	private long id;
	
	private Long pollId;
	private Long pollOptionId;
 	private Long userId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getPollId() {
		return pollId;
	}
	public void setPollId(Long pollId) {
		this.pollId = pollId;
	}
	public Long getPollOptionId() {
		return pollOptionId;
	}
	public void setPollOptionId(Long pollOptionId) {
		this.pollOptionId = pollOptionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


}
