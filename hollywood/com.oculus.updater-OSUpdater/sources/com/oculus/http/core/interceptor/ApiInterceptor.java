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

    public static ApiInterceptor create(Context context, PackageInfo packageInfo, @Nullable String str, Provider<Locale> provider) {
        return new ApiInterceptor(DeviceUtils.getDeviceId(context), str, packageInfo != null ? packageInfo.versionCode : -1, provider);
    }

    private ApiInterceptor(String str, @Nullable String str2, int i, Provider<Locale> provider) {
        this.mDeviceId = str;
        this.mUserAgent = str2;
        this.mBuildNumber = i;
        this.mLocaleProvider = provider;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder addHeader = request.newBuilder().addHeader("Accept-Language", getAcceptLanguage()).addHeader("X-ANDROID-ID", this.mDeviceId).addHeader("X-Build-Model", this.mBuildModel).addHeader("X-Build-Number", String.valueOf(this.mBuildNumber)).addHeader("X-Oculus-Feature", "1").addHeader("oculus-request-id", ApiUUID.gen());
        String str = this.mUserAgent;
        if (str != null) {
            addHeader.addHeader("User-Agent", str);
        }
        addHeader.url(request.url().newBuilder().addQueryParameter("forced_locale", this.mLocaleProvider.get().toString()).build());
        return chain.proceed(addHeader.build());
    }

    private String getAcceptLanguage() {
        return this.mLocaleProvider.get().toString().replace('_', '-');
    }
}
