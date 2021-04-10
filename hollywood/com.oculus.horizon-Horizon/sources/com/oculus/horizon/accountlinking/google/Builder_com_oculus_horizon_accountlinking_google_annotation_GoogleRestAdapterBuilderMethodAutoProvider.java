package com.oculus.horizon.accountlinking.google;

import X.AnonymousClass0J3;
import X.AnonymousClass117;
import com.facebook.annotations.Generated;
import com.oculus.horizon.accountlinking.google.interceptor.GoogleAuthorizationInterceptor;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.ApiResponseConverter;
import com.oculus.http.core.interceptor.UserAgentInterceptor;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class Builder_com_oculus_horizon_accountlinking_google_annotation_GoogleRestAdapterBuilderMethodAutoProvider extends AnonymousClass0J3<RestAdapter.Builder> {
    public final Object get() {
        return GoogleAPIModule.A00(HttpModule.A05(this), ApiModule.A06(this), ApiModule.A08(this), (UserAgentInterceptor) AnonymousClass117.A00(187, this), ApiResponseConverter.A00(this), (GoogleAuthorizationInterceptor) AnonymousClass117.A00(138, this));
    }
}
