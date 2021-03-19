package com.example.mysql_crud.remote

import com.example.mysql_crud.Constant
import com.example.mysql_crud.model.Contacts
import retrofit2.http.*

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


abstract class ApiInterface {
    @POST("retrofit/POST/readcontacts.php")
    abstract fun getContacts(): Call<List<Contacts?>?>?

    @FormUrlEncoded
    @POST("retrofit/POST/addcontact.php")
    abstract fun insertUser(
        @Field("name") name: String?,
        @Field("email") email: String?
    ): Call<Contacts?>?


    //for signup
    @FormUrlEncoded
    @POST("retrofit/POST/signup.php")
    abstract fun signUp(
        @Field(Constant.KEY_NAME) name: String?,
        @Field(Constant.KEY_CELL) cell: String?,
        @Field(Constant.KEY_PASSWORD) password: String?,
    ): Call<Contacts?>?


    //for login
    @FormUrlEncoded
    @POST("retrofit/POST/login.php")
    abstract fun login(
        @Field(Constant.KEY_CELL) cell: String?,
        @Field(Constant.KEY_PASSWORD) password: String?
    ): Call<Contacts?>?


    @FormUrlEncoded
    @POST("retrofit/POST/editcontact.php")
    abstract fun editUser(
        @Field("id") id: String?,
        @Field("name") name: String?,
        @Field("email") email: String?
    ): Call<Contacts?>?


    @FormUrlEncoded
    @POST("retrofit/POST/deletecontact.php")
    abstract fun deleteUser(
        @Field("id") id: Int
    ): Call<Contacts?>?


    //for live data search
    @GET("retrofit/GET/getcontacts.php")
    abstract fun getContact(
    ): Call<List<Contacts?>?>?
}