package com.oculus.common.httpclient;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class VrShellUserAgentInterceptor extends Request.Builder implements Interceptor {
    private static final String APP_NAME = "OculusVrShell";
    private static final String HEADER_USER_AGENT_KEY = "User-Agent";
    private final String mUserAgent;

    public VrShellUserAgentInterceptor(String str, String str2, String str3, String str4) {
        this.mUserAgent = UserAgentBuilder.newBuilder().build(System.getProperty("http.agent"), APP_NAME, str, str3, str2, str4);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().addHeader(HEADER_USER_AGENT_KEY, this.mUserAgent).build());
    }
}
