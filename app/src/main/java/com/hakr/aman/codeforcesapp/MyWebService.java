package com.hakr.aman.codeforcesapp;

//import com.example.aman.codeforcesapp.model.Post;
import com.hakr.aman.codeforcesapp.model.Result;
import com.hakr.aman.codeforcesapp.model.usersubmissioninfo;
//import com.example.aman.codeforcesapp.model.firstTry;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyWebService {
  String BASE_URL="https://codeforces.com/api/";
  String FEED="posts";
  Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
  @GET("user.status")
    Call<usersubmissioninfo> getUserSubmissions(@Query("handle") String handlename, @Query("from") int from, @Query("count") int count);
 // Call<usersubmissioninfo> getUserSubmissions();

  @GET("user.info")
  Call<Result> getUserInfo1(@Query("handles") String handlename);

}
