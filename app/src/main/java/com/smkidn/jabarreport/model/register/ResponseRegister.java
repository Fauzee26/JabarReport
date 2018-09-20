package com.smkidn.jabarreport.model.register;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("result")
	private String result;

	@SerializedName("msg")
	private String msg;

	@SerializedName("log")
	private String log;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setLog(String log){
		this.log = log;
	}

	public String getLog(){
		return log;
	}

	@Override
 	public String toString(){
		return 
			"ResponseRegister{" + 
			"result = '" + result + '\'' + 
			",msg = '" + msg + '\'' + 
			",log = '" + log + '\'' + 
			"}";
		}
}