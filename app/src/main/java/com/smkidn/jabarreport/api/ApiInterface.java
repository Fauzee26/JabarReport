package com.smkidn.jabarreport.api;

import com.smkidn.jabarreport.model.login.ResponseLogin;
import com.smkidn.jabarreport.model.register.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> response_login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("registrasi")
    Call<ResponseRegister> responseRegister(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password,
            @Field("alamat") String alamat,
            @Field("tgllahir") String tgllahir,
            @Field("gender") String gender
    );
}
