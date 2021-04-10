package com.squareup.okhttp;

import java.io.IOException;

public interface Interceptor {

    public interface Chain {
        Response proceed(Request request) throws IOException;
    }

    Response intercept(Chain chain) throws IOException;
}
