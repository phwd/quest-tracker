package com.oculus.http.core.interceptor;

import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.oculus.http.core.interceptor.InterceptorModule;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.http.useragent.UserAgentString;
import java.io.IOException;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.Response;

public class UserAgentInterceptor implements Interceptor {
    @UserAgentString
    private final String mUserAgentString;

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_http_core_interceptor_UserAgentInterceptor$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(InterceptorModule.UL_id.$ul_$xXXcom_oculus_http_core_interceptor_UserAgentInterceptor$xXXBINDING_ID, $ul_injector);
    }

    public static final UserAgentInterceptor $ul_$xXXcom_oculus_http_core_interceptor_UserAgentInterceptor$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (UserAgentInterceptor) UL.factorymap.get(InterceptorModule.UL_id.$ul_$xXXcom_oculus_http_core_interceptor_UserAgentInterceptor$xXXBINDING_ID, $ul_injector);
    }

    public static final UserAgentInterceptor $ul_$xXXcom_oculus_http_core_interceptor_UserAgentInterceptor$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return new UserAgentInterceptor($ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_http_core_interceptor_UserAgentInterceptor$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(InterceptorModule.UL_id.$ul_$xXXcom_oculus_http_core_interceptor_UserAgentInterceptor$xXXBINDING_ID, $ul_injector);
    }

    public UserAgentInterceptor(InjectorLike $ul_injector) {
        this.mUserAgentString = UserAgentModule.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentString$xXXACCESS_METHOD($ul_injector);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header("User-Agent", this.mUserAgentString).build());
    }
}
