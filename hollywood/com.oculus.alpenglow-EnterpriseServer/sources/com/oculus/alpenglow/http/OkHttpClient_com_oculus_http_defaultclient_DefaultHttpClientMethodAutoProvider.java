package com.oculus.alpenglow.http;

import X.AnonymousClass0Li;
import X.AnonymousClass0Qs;
import X.C01400Gn;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class OkHttpClient_com_oculus_http_defaultclient_DefaultHttpClientMethodAutoProvider extends AnonymousClass0Li<AnonymousClass0Qs> {
    public final Object get() {
        return HttpModule.A0G(C01400Gn.A00(this), HttpModule.A0F(this), HttpModule.A0B(this), DeviceAuthorizationInterceptor.A00(this), UserAgentInterceptor.A00(this), HttpModule.A04(this));
    }
}
