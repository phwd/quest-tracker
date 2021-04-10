package com.oculus.notifications;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UserAgentInterceptor extends Request.Builder implements Interceptor {
    private static final String HEADER_ACCEPT_KEY = "Accept";
    private static final String HEADER_ACCEPT_VALUE = "application/json";
    private static final String HEADER_USER_AGENT_KEY = "User-Agent";
    private Context mContext;

    public UserAgentInterceptor(Context context) {
        this.mContext = context;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        int i;
        UserAgentBuilder newBuilder = UserAgentBuilder.newBuilder();
        newBuilder.setHttpAgent(System.getProperty("http.agent"));
        newBuilder.setAppName("OculusSystemUX");
        String str = "1.0";
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            str = packageManager.getPackageInfo(this.mContext.getPackageName(), 0).versionName;
            i = packageManager.getPackageInfo(this.mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            i = 0;
        }
        newBuilder.setAppVersion(str);
        newBuilder.setBuildVersion(Integer.toString(i));
        newBuilder.setPackageName(this.mContext.getPackageName());
        newBuilder.setLocale(this.mContext.getApplicationContext().getResources().getConfiguration().getLocales().get(0).toString());
        return chain.proceed(chain.request().newBuilder().addHeader("User-Agent", newBuilder.build()).addHeader("Accept", HEADER_ACCEPT_VALUE).build());
    }
}
