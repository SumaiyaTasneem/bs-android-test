package com.bs.androidtest.presenter.service;

import com.bs.androidtest.model.ImageList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("v2/list")
    Call<List<ImageList>> getAllImages();
}
