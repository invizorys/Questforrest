package com.mediahack.repository.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Roma on 05.10.2016.
 */

public interface MediaHackClient {

    @GET("/path")
    Call<List<Object>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
