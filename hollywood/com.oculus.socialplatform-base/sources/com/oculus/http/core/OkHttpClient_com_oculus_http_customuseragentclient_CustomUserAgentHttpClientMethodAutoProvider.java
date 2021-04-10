package com.oculus.http.core;

import X.AnonymousClass0IP;
import X.AnonymousClass0VG;
import X.C00610Hs;
import com.facebook.annotations.Generated;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import com.oculus.locale.LocaleModule;
import okhttp3.OkHttpClient;

@Generated({"By: InjectorProcessor"})
public class OkHttpClient_com_oculus_http_customuseragentclient_CustomUserAgentHttpClientMethodAutoProvider extends AnonymousClass0VG<OkHttpClient> {
    public OkHttpClient get() {
        return ApiModule.provideCustomUserAgentHttpClient(HttpModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_ACCESS_METHOD(this), C00610Hs.A00(this), AnonymousClass0IP.A01(this), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(this), LocaleModule._UL__ULSEP_javax_inject_Provider_ULLT_java_util_Locale_ULGT__ULSEP_ACCESS_METHOD(this), OculusAuthorizationInterceptor._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_ACCESS_METHOD(this));
    }
}
