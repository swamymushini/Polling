package com.moneyhop.polling.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Polls")
public class Poll {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String question;
	
	@OneToMany( cascade=CascadeType.ALL)
	@JoinColumn(name="poll_id" , referencedColumnName = "id")
	private Set<PollOption> optionsList;
	
	private boolean active;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<PollOption> getOptionsList() {
		return optionsList;
	}
	
	public void setOptionsList(Set<PollOption> optionsList) {
		this.optionsList = optionsList;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
