package com.oculus.horizon.api;

import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;

public class ApiTaskCallback<T> extends ApiCallback<T> {
    public final AnonymousClass0DD<T> mCompletionSource = new AnonymousClass0DD<>();

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: X.0DC<TResult>, X.0DC<T> */
    public AnonymousClass0DC<T> getTask() {
        return (AnonymousClass0DC<TResult>) this.mCompletionSource.A00;
    }

    @Override // com.oculus.http.core.base.ApiCallback
    public void onError(ApiError apiError) {
        this.mCompletionSource.A01(apiError);
    }

    @Override // com.oculus.http.core.base.ApiCallback
    public void onResponse(T t) {
        this.mCompletionSource.A02(t);
    }
}
