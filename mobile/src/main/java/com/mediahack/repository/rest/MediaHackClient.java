package com.mediahack.repository.rest;

import com.questforrest.dto.AnswerResponseDto;
import com.questforrest.dto.QuestListResponseDto;
import com.questforrest.dto.QuestProgressResponseDto;
import com.questforrest.dto.RegistrationRequestDto;
import com.questforrest.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Roma on 05.10.2016.
 */

public interface MediaHackClient {

    @POST("/auth/vk/authorize")
    Call<UserDto> login(@Body RegistrationRequestDto registrationRequestDto);

    @GET("/quest")
    Call<QuestListResponseDto> getQuests();

    @GET("/quest/progress/{questId}")
    Call<QuestProgressResponseDto> getQuestProgress(@Path("questId") Long questId);

    @POST("/quest/resolve/{taskProgressId}")
    Call<AnswerResponseDto> resolveTask(@Path("taskProgressId") Long taskProgressId, @Body String answer);

    @POST("/quest/{questId}/enroll/{questCode}")
    Call<QuestProgressResponseDto> enrollQuest(@Path("questId") Long questId, @Path("questCode") String questCode);

    @POST("/quest/{questId}/team/{teamName}")
    Call<QuestProgressResponseDto> createTeam(@Path("questId") Long questId, @Path("teamName") String teamName);

    @POST("/profile/shareVK")
    Call<Void> shareVk();
}
