package com.mediahack.repository.rest;

import com.questforrest.dto.QuestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Roma on 05.10.2016.
 */

public interface MediaHackClient {

    @GET("/quest")
    Call<List<QuestDto>> getQuests();
}
