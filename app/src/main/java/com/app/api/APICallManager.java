package com.app.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.app.modal.base.BaseResponse;
import com.app.modal.base.Errors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APICallManager<T> implements Callback<T>, APIStatusCode {

    private final APIType mCallType;
    private APICallHandler mAPICallHandler;
    private Call mCall;

    public APICallManager(APIType callType, APICallHandler callHandler) {
        this.mCallType = callType;
        this.mAPICallHandler = callHandler;
    }


    @Override
    public void onResponse(@NonNull Call<T> call, Response<T> response) {
        if (response.code() == OK) {
            if (mAPICallHandler != null) {
                BaseResponse body = (BaseResponse) response.body();
                if (body != null && body.results != null) {
                    mAPICallHandler.onSuccess(mCallType, body);
                } else {
                    Errors errors = new Errors();
                    errors.responseMessage = "Something went wrong";
                    mAPICallHandler.onFailure(mCallType, response.code(), errors);
                }
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, Throwable t) {
        if (mAPICallHandler != null) {
            Errors errors = new Errors();
            errors.responseMessage = "Something went wrong";
            mAPICallHandler.onFailure(mCallType, 0, errors);

            Log.d("EEEE", t.getMessage());
            t.printStackTrace();
        }
    }

    public void userListApi() {
        mCall = APIHandler.getApiClient().getUserList();
        if (mCall != null) mCall.enqueue(this);
    }
}