package com.oculus.http.core;

import X.AbstractC0097Hv;
import X.C00208d;
import X.C00238n;
import X.C0088Gy;
import X.LD;
import com.facebook.annotations.Generated;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;

@Generated({"By: InjectorProcessor"})
public class OkHttpClient_com_oculus_http_customuseragentclient_CustomUserAgentHttpClientMethodAutoProvider extends AbstractC0097Hv<LD> {
    public final Object get() {
        return ApiModule.A05(HttpModule.A06(this), C00208d.A00(this), C00238n.A00(this), EndpointModule.A07(this), new C0088Gy(56, this), OculusAuthorizationInterceptor.A00(this));
    }
}
