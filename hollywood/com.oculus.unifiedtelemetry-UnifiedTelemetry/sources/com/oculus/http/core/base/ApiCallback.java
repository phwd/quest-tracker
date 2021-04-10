package com.oculus.http.core.base;

import com.oculus.http.core.base.ApiError;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class ApiCallback<T> implements Callback<T> {
    public ApiError mError;
    public boolean mIsComplete = false;
    public boolean mIsPaused = false;
    public T mResponse;

    @Override // retrofit.Callback
    public final void failure(RetrofitError retrofitError) {
        if (!this.mIsComplete) {
            this.mError = new ApiError(retrofitError);
            if (!this.mIsPaused) {
                this.mIsComplete = true;
            }
        }
    }

    @Override // retrofit.Callback
    public final void success(T t, Response response) {
        if (this.mIsComplete) {
            return;
        }
        if (t instanceof ValidatableApiResponse) {
            try {
                throw null;
            } catch (RuntimeException e) {
                this.mError = new ApiError(ApiError.Type.VALIDATIION_ERROR, e);
                if (!this.mIsPaused) {
                    this.mIsComplete = true;
                }
            }
        } else {
            this.mResponse = t;
            if (!this.mIsPaused) {
                this.mIsComplete = true;
                throw null;
            }
        }
    }
}
