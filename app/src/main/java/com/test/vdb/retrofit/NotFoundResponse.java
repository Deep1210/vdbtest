package com.test.vdb.retrofit;

import com.google.gson.annotations.SerializedName;

public class NotFoundResponse{

	@SerializedName("detail")
	private String detail;

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return detail;
	}
}