package com.test.vdb.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErrorBodyResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("success")
	private boolean success;

	@SerializedName("error")
	private List<String> error;

	@SerializedName("registration_id")
	private List<String> registrationId;

	public List<String> getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(List<String> registrationId) {
		this.registrationId = registrationId;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setError(List<String> error){
		this.error = error;
	}

	public List<String> getError(){
		return error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}