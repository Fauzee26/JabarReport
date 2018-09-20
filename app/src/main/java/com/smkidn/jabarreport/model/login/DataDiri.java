package com.smkidn.jabarreport.model.login;

import com.google.gson.annotations.SerializedName;

public class DataDiri{

	@SerializedName("alamat_user")
	private String alamatUser;

	@SerializedName("gender_user")
	private String genderUser;

	@SerializedName("foto_user")
	private String fotoUser;

	@SerializedName("email_user")
	private String emailUser;

	@SerializedName("password_user")
	private String passwordUser;

	@SerializedName("tgllahir_user")
	private String tgllahirUser;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("nama_user")
	private String namaUser;

	@SerializedName("username_user")
	private String usernameUser;

	public void setAlamatUser(String alamatUser){
		this.alamatUser = alamatUser;
	}

	public String getAlamatUser(){
		return alamatUser;
	}

	public void setGenderUser(String genderUser){
		this.genderUser = genderUser;
	}

	public String getGenderUser(){
		return genderUser;
	}

	public void setFotoUser(String fotoUser){
		this.fotoUser = fotoUser;
	}

	public String getFotoUser(){
		return fotoUser;
	}

	public void setEmailUser(String emailUser){
		this.emailUser = emailUser;
	}

	public String getEmailUser(){
		return emailUser;
	}

	public void setPasswordUser(String passwordUser){
		this.passwordUser = passwordUser;
	}

	public String getPasswordUser(){
		return passwordUser;
	}

	public void setTgllahirUser(String tgllahirUser){
		this.tgllahirUser = tgllahirUser;
	}

	public String getTgllahirUser(){
		return tgllahirUser;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setNamaUser(String namaUser){
		this.namaUser = namaUser;
	}

	public String getNamaUser(){
		return namaUser;
	}

	public void setUsernameUser(String usernameUser){
		this.usernameUser = usernameUser;
	}

	public String getUsernameUser(){
		return usernameUser;
	}

	@Override
 	public String toString(){
		return 
			"DataDiri{" + 
			"alamat_user = '" + alamatUser + '\'' + 
			",gender_user = '" + genderUser + '\'' + 
			",foto_user = '" + fotoUser + '\'' + 
			",email_user = '" + emailUser + '\'' + 
			",password_user = '" + passwordUser + '\'' + 
			",tgllahir_user = '" + tgllahirUser + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",nama_user = '" + namaUser + '\'' + 
			",username_user = '" + usernameUser + '\'' + 
			"}";
		}
}