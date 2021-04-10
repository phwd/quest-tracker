package com.oculus.notifications;

import android.content.Context;
import android.os.Build;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import okhttp3.OkHttpClient;

public class OkHttpClientFactory {
    public OkHttpClient mInstance;

    public static class Loader {
        public static volatile OkHttpClientFactory sInstance;

        public static final OkHttpClientFactory getInstance(Context context) {
            if (sInstance == null) {
                sInstance = new OkHttpClientFactory(context);
            }
            return sInstance;
        }
    }

    public OkHttpClient getClient() {
        return this.mInstance;
    }

    public static OkHttpClientFactory getInstance(Context context) {
        return Loader.getInstance(context);
    }

    public OkHttpClientFactory(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors.add(new UserAgentInterceptor(context));
        builder.certificatePinner(FbCertificatePinnerFactory.create(Build.TIME));
        this.mInstance = builder.build();
    }
}
