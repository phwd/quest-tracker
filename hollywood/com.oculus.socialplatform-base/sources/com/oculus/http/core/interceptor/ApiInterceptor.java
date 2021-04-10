package com.oculus.http.core.interceptor;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.oculus.http.core.uuid.ApiUUID;
import com.oculus.util.device.DeviceUtils;
import java.io.IOException;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.inject.Provider;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    public final String mBuildModel = DeviceUtils.getBuildModel();
    public final int mBuildNumber;
    public final String mDeviceId;
    public final Provider<Locale> mLocaleProvider;
    @Nullable
    public final String mUserAgent;

    private String getAcceptLanguage() {
        return this.mLocaleProvider.get().toString().replace('_', '-');
    }

    public ApiInterceptor(String str, @Nullable String str2, int i, Provider<Locale> provider) {
        this.mDeviceId = str;
        this.mUserAgent = str2;
        this.mBuildNumber = i;
        this.mLocaleProvider = provider;
    }

    public static ApiInterceptor create(Context context, PackageInfo packageInfo, @Nullable String str, Provider<Locale> provider) {
        int i;
        String deviceId = DeviceUtils.getDeviceId(context);
        if (packageInfo != null) {
            i = packageInfo.versionCode;
        } else {
            i = -1;
        }
        return new ApiInterceptor(deviceId, str, i, provider);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        newBuilder.addHeader("Accept-Language", getAcceptLanguage());
        newBuilder.addHeader("X-ANDROID-ID", this.mDeviceId);
        newBuilder.addHeader("X-Build-Model", this.mBuildModel);
        newBuilder.addHeader("X-Build-Number", String.valueOf(this.mBuildNumber));
        newBuilder.addHeader("X-Oculus-Feature", "1");
        newBuilder.addHeader(ApiUUID.HEADER_NAME, ApiUUID.gen());
        String str = this.mUserAgent;
        if (str != null) {
            newBuilder.addHeader("User-Agent", str);
        }
        HttpUrl.Builder newBuilder2 = request.url.newBuilder();
        newBuilder2.addQueryParameter("forced_locale", this.mLocaleProvider.get().toString());
        newBuilder.url(newBuilder2.build());
        return chain.proceed(newBuilder.build());
    }
}
