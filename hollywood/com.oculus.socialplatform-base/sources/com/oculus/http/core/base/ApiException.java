package com.oculus.http.core.base;

import retrofit.RetrofitError;

public final class ApiException extends Exception {
    public final ApiError mApiError;

    public ApiException(RetrofitError retrofitError, ApiError apiError) {
        super(retrofitError.getMessage(), retrofitError);
        this.mApiError = apiError;
    }

    public ApiError getApiError() {
        return this.mApiError;
    }
}
