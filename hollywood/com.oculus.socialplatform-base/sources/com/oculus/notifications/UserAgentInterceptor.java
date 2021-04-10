package com.oculus.notifications;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UserAgentInterceptor extends Request.Builder implements Interceptor {
    public static final String HEADER_ACCEPT_KEY = "Accept";
    public static final String HEADER_ACCEPT_VALUE = "application/json";
    public static final String HEADER_USER_AGENT_KEY = "User-Agent";
    public Context mContext;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        int i;
        UserAgentBuilder userAgentBuilder = new UserAgentBuilder();
        userAgentBuilder.mHttpAgent = System.getProperty("http.agent");
        userAgentBuilder.setAppName("OculusSystemUX");
        String str = "1.0";
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            str = packageManager.getPackageInfo(this.mContext.getPackageName(), 0).versionName;
            i = packageManager.getPackageInfo(this.mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            i = 0;
        }
        userAgentBuilder.setAppVersion(str);
        userAgentBuilder.setBuildVersion(Integer.toString(i));
        userAgentBuilder.setPackageName(this.mContext.getPackageName());
        userAgentBuilder.setLocale(this.mContext.getApplicationContext().getResources().getConfiguration().getLocales().get(0).toString());
        Request.Builder newBuilder = chain.request().newBuilder();
        newBuilder.addHeader("User-Agent", userAgentBuilder.build());
        newBuilder.addHeader(HEADER_ACCEPT_KEY, "application/json");
        return chain.proceed(newBuilder.build());
    }

    public UserAgentInterceptor(Context context) {
        this.mContext = context;
    }
}
