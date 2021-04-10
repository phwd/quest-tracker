package com.oculus.auth.api;

import X.AnonymousClass0J3;
import com.facebook.annotations.Generated;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.ApiResponseConverter;
import com.oculus.http.core.LoggingErrorHandler;
import com.oculus.http.core.endpoint.EndpointModule;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_auth_api_FacebookLoginApiRestAdapterMethodAutoProvider extends AnonymousClass0J3<RestAdapter> {
    public RestAdapter get() {
        return ApiModule.provideFacebookLoginApiRestAdapter(ApiModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_auth_api_FacebookLoginHttpClient_ULSEP_ACCESS_METHOD(this), EndpointModule.A03(this), ApiModule.A08(this), ApiResponseConverter.A00(this), LoggingErrorHandler.A00(this));
    }
}
