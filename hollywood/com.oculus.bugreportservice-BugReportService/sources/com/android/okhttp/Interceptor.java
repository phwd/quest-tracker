package com.android.okhttp;

public interface Interceptor {

    public interface Chain {
        Response proceed(Request request);
    }

    Response intercept(Chain chain);
}
