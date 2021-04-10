package com.oculus.http.core.interceptor;

import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.http.useragent.UserAgentString;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

@Dependencies
public class UserAgentInterceptor implements Interceptor {
    @Inject
    @Eager
    @UserAgentString
    private final String mUserAgentString;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header("User-Agent", this.mUserAgentString).build());
    }
}
