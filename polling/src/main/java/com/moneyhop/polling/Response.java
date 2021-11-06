package com.moneyhop.polling;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {

	private String code;
	private String message;
	
	public enum StatusCode{
		
		ERROR("9999"),SUCCESS("0000"),FAILED("9000");
		String code;
		
		private StatusCode(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
		
	}
	
	public static Response failed() {
		return new Response(StatusCode.FAILED,"Unable to process");
	}
	
	public static Response success() {
		return new Response(StatusCode.SUCCESS,"Request processed successfully");
	}
	
	public static Response mandatory(String field) {
		return new Response(StatusCode.ERROR, field + " is mandatory or should not blank");
	}
	
	public static Response alreadyExists(String field) {
		return new Response(StatusCode.ERROR, field + " already exists");
	}
	
	public static Response invalid(String field) {
		return new Response(StatusCode.ERROR, "invalid "+ field);
	}
	
	public Response() {
		
	}
	
	public Response(StatusCode code, String desc) {
		super();
		this.code = code.getCode();
		if(StatusCode.FAILED==code) {
			this.message = desc;
		}
		this.message = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
