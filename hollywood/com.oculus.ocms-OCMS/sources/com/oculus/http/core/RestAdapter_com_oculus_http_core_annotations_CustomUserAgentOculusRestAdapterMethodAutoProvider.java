package com.oculus.http.core;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.http.core.endpoint.EndpointModule;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class RestAdapter_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapterMethodAutoProvider extends AbstractProvider<RestAdapter> {
    @Override // javax.inject.Provider
    public RestAdapter get() {
        return ApiModule.provideCustomUserAgentOculusRestAdapter(ApiModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_ACCESS_METHOD(this), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(this), ApiModule._UL__ULSEP_retrofit_ErrorHandler_ULSEP_ACCESS_METHOD(this), ApiResponseConverter._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_ACCESS_METHOD(this), ApiModule._UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_ACCESS_METHOD(this));
    }
}
