package com.smkidn.jabarreport.model.login;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("result")
	private String result;

	@SerializedName("msg")
	private String msg;

	@SerializedName("log")
	private String log;

	@SerializedName("dataDiri")
	private DataDiri dataDiri;

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

	public void setDataDiri(DataDiri dataDiri){
		this.dataDiri = dataDiri;
	}

	public DataDiri getDataDiri(){
		return dataDiri;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLogin{" + 
			"result = '" + result + '\'' + 
			",msg = '" + msg + '\'' + 
			",log = '" + log + '\'' + 
			",dataDiri = '" + dataDiri + '\'' + 
			"}";
		}
}