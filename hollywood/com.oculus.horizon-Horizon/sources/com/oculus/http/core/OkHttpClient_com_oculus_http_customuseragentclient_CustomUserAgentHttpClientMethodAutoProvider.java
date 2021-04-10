package com.oculus.http.core;

import X.AnonymousClass0J3;
import X.AnonymousClass0N1;
import X.C003108z;
import X.C003809k;
import com.facebook.annotations.Generated;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import com.oculus.locale.LocaleModule;

@Generated({"By: InjectorProcessor"})
public class OkHttpClient_com_oculus_http_customuseragentclient_CustomUserAgentHttpClientMethodAutoProvider extends AnonymousClass0J3<AnonymousClass0N1> {
    public final Object get() {
        return ApiModule.A05(HttpModule.A05(this), C003108z.A00(this), C003809k.A02(this), EndpointModule.A06(this), LocaleModule.A01(this), OculusAuthorizationInterceptor.A00(this));
    }
}
