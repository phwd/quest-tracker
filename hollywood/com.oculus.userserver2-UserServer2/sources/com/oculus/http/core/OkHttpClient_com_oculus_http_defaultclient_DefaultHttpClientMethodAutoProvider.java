package com.oculus.http.core;

import X.AbstractC0029Ba;
import X.AbstractC0054Ej;
import X.BW;
import X.IX;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.annotations.Generated;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;

@Generated({"By: InjectorProcessor"})
public class OkHttpClient_com_oculus_http_defaultclient_DefaultHttpClientMethodAutoProvider extends AbstractC0029Ba<AbstractC0054Ej> {
    public final Object get() {
        return ApiModule.A00((AbstractC0054Ej) IX.A00(71, this), (Context) IX.A00(1, this), (PackageInfo) IX.A00(22, this), (String) IX.A00(60, this), (String) IX.A00(77, this), new BW(68, this), (OculusAuthorizationInterceptor) IX.A00(56, this));
    }
}
