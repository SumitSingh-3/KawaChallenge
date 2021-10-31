package com.app.api;

import com.app.modal.base.BaseResponse;
import com.app.modal.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiService {

    @GET("api/?inc=gender,name,nat,location,picture,email&results=20")
    Call<BaseResponse> getUserList();

}
