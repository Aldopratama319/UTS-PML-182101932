package com.aldo.service;

import com.aldo.model.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetService {
    @GET("/users")
    Call<List<user>> getUserList();
}
