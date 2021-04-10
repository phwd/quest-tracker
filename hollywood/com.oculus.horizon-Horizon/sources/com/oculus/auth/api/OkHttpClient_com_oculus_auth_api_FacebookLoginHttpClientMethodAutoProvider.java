package com.oculus.auth.api;

import X.AnonymousClass0J3;
import X.AnonymousClass0N1;
import X.C003108z;
import X.C003809k;
import com.facebook.annotations.Generated;
import com.oculus.http.common.HttpModule;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.locale.LocaleModule;

@Generated({"By: InjectorProcessor"})
public class OkHttpClient_com_oculus_auth_api_FacebookLoginHttpClientMethodAutoProvider extends AnonymousClass0J3<AnonymousClass0N1> {
    public AnonymousClass0N1 get() {
        return ApiModule.provideFacebookLoginHttpClient(HttpModule.A05(this), C003108z.A00(this), C003809k.A02(this), UserAgentModule.A01(this), LocaleModule.A01(this), FacebookAuthorizationInterceptor._UL__ULSEP_com_oculus_auth_api_FacebookAuthorizationInterceptor_ULSEP_ACCESS_METHOD(this));
    }
}
