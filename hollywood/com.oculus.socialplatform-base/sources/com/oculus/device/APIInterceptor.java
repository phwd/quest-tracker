package com.oculus.device;

import android.content.Context;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import java.io.IOException;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class APIInterceptor implements Interceptor {
    public static final String TAG = "APIInterceptor";
    public final Context mContext;

    public static void logHeaders(Headers headers) {
        for (int i = 0; i < (headers.namesAndValues.length >> 1); i++) {
        }
    }

    public APIInterceptor(Context context) {
        this.mContext = context;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        for (Map.Entry<String, String> entry : NetworkHeaders.getHeaderMap(this.mContext).entrySet()) {
            newBuilder.header(entry.getKey(), entry.getValue());
        }
        HttpUrl.Builder newBuilder2 = request.url.newBuilder();
        newBuilder2.addQueryParameter("forced_locale", DeviceEnvironmentModuleImpl.getDeviceLocale(this.mContext));
        newBuilder.url(newBuilder2.build());
        return chain.proceed(newBuilder.build());
    }
}
