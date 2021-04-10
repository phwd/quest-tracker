package com.oculus.http.core.base;

import com.google.common.base.Preconditions;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class ApiCallback<T> implements Callback<T> {
    private ApiError mError;
    private boolean mIsComplete = false;
    private boolean mIsPaused = false;
    private T mResponse;

    public abstract void onError(ApiError apiError);

    public abstract void onResponse(T t);

    @Override // retrofit.Callback
    public final void success(T t, Response response) {
        if (!this.mIsComplete) {
            if (t instanceof ValidatableApiResponse) {
                try {
                    t.validate();
                } catch (RuntimeException e) {
                    this.mError = ApiError.makeValidationError(e);
                    if (!this.mIsPaused) {
                        this.mIsComplete = true;
                        handleError();
                        return;
                    }
                    return;
                }
            }
            this.mResponse = t;
            if (!this.mIsPaused) {
                this.mIsComplete = true;
                onResponse(this.mResponse);
            }
        }
    }

    @Override // retrofit.Callback
    public final void failure(RetrofitError retrofitError) {
        if (!this.mIsComplete) {
            this.mError = new ApiError(retrofitError);
            if (!this.mIsPaused) {
                this.mIsComplete = true;
                handleError();
            }
        }
    }

    public void resume() {
        if (!this.mIsComplete) {
            if (this.mResponse == null && this.mError == null) {
                this.mIsPaused = false;
                return;
            }
            Preconditions.checkArgument(this.mIsPaused);
            this.mIsComplete = true;
            T t = this.mResponse;
            if (t != null) {
                onResponse(t);
            } else {
                handleError();
            }
        }
    }

    public void pause() {
        this.mIsPaused = true;
    }

    public boolean isComplete() {
        return this.mIsComplete;
    }

    public boolean isPaused() {
        return this.mIsPaused;
    }

    private void handleError() {
        onError(this.mError);
    }
}
