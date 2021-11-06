package com.moneyhop.polling.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "PollOptions")
public class PollOption {

	@Id
	@GeneratedValue
	private long id;
	private char code;
	private String desc;
	
	@Transient
	private long voteCount;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public char getCode() {
		return code;
	}
	
	public void setCode(char code) {
		this.code = code;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}
	
	@Override
	public int hashCode() {
	    return Objects.hashCode(this.code);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (o == this)
	        return true;
	    if (!(o instanceof PollOption))
	        return false;
	    PollOption other = (PollOption)o;
	    
	    if(this.code == ' ' || other.code == ' ') {
	    	  return false;
	    }
	   
	    return this.code==other.code;
	}	
}
