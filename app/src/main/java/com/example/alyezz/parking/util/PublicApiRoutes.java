package com.example.alyezz.parking.util;



import com.example.alyezz.parking.model.Sensor;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface PublicApiRoutes {

    @GET("/get_all.php")
    void getSensors(Callback<List<Sensor>> callback);

}