package com.oculus.http.core.interceptor;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.oculus.http.core.uuid.ApiUUID;
import com.oculus.util.device.DeviceUtils;
import java.io.IOException;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    private final String mBuildModel = DeviceUtils.getBuildModel();
    private final int mBuildNumber;
    private final String mDeviceId;
    private final Provider<Locale> mLocaleProvider;
    @Nullable
    private final String mUserAgent;

    public static ApiInterceptor create(Context applicationContext, PackageInfo packageInfo, @Nullable String userAgentString, Provider<Locale> localeProvider) {
        return new ApiInterceptor(DeviceUtils.getDeviceId(applicationContext), userAgentString, packageInfo != null ? packageInfo.versionCode : -1, localeProvider);
    }

    private ApiInterceptor(String deviceId, @Nullable String userAgent, int buildNumber, Provider<Locale> localeProvider) {
        this.mDeviceId = deviceId;
        this.mUserAgent = userAgent;
        this.mBuildNumber = buildNumber;
        this.mLocaleProvider = localeProvider;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder builder = oldRequest.newBuilder().addHeader("Accept-Language", getAcceptLanguage()).addHeader("X-ANDROID-ID", this.mDeviceId).addHeader("X-Build-Model", this.mBuildModel).addHeader("X-Build-Number", String.valueOf(this.mBuildNumber)).addHeader("X-Oculus-Feature", "1").addHeader(ApiUUID.HEADER_NAME, ApiUUID.gen());
        if (this.mUserAgent != null) {
            builder.addHeader("User-Agent", this.mUserAgent);
        }
        builder.url(oldRequest.url().newBuilder().addQueryParameter("forced_locale", this.mLocaleProvider.get().toString()).build());
        return chain.proceed(builder.build());
    }

    private String getAcceptLanguage() {
        return this.mLocaleProvider.get().toString().replace('_', '-');
    }
}
