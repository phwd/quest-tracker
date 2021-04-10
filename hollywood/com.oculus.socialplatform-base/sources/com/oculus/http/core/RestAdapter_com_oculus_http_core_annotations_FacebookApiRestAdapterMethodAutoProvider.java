package com.oculus.http.core;

import X.AnonymousClass0VG;
import com.facebook.annotations.Generated;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.defaultclient.DefaultClientModule;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_http_core_annotations_FacebookApiRestAdapterMethodAutoProvider extends AnonymousClass0VG<RestAdapter> {
    public RestAdapter get() {
        return ApiModule.provideFacebookApiRestAdapter(DefaultClientModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(this), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_ACCESS_METHOD(this), ApiResponseConverter._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_ACCESS_METHOD(this), ApiModule._UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_ACCESS_METHOD(this), LoggingErrorHandler._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD(this));
    }
}
