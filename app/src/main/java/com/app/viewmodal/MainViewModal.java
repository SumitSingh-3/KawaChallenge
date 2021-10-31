package com.app.viewmodal;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.api.APICallHandler;
import com.app.api.APICallManager;
import com.app.api.APIType;
import com.app.modal.base.BaseResponse;
import com.app.modal.base.Errors;


public class MainViewModal extends ViewModel implements APICallHandler {


    public MutableLiveData<BaseResponse> userResponse = new MutableLiveData<>();
    public MutableLiveData<Errors> error = new MutableLiveData<>();


    @Override
    public void onSuccess(APIType apiType, Object response) {
        if (apiType == APIType.API_CALL) {
            userResponse.setValue((BaseResponse) response);
        }
    }

    @Override
    public void onFailure(APIType apiType, int code, Errors errors) {
        error.setValue(errors);
    }

    public void getUserAPI() {
        APICallManager apiCallManager = new APICallManager(APIType.API_CALL, this);
        apiCallManager.userListApi();
    }
}
