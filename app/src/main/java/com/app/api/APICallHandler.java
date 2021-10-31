package com.app.api;

import com.app.modal.base.Errors;

public interface APICallHandler<T> {

    void onSuccess(APIType apiType, T response);

    void onFailure(APIType apiType, int code, Errors errors);
}
