package com.oculus.http.core;

import X.AbstractC0031Bc;
import X.AbstractC0054Ej;
import X.AbstractC0192Xx;
import X.S0;
import X.XQ;
import X.XS;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.http.core.annotations.OculusApiEndpoint;
import com.oculus.http.core.interceptor.ApiInterceptor;
import com.oculus.http.core.interceptor.GzipInterceptor;
import com.oculus.http.core.interceptor.NpeExceptionHandlerInterceptor;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import com.oculus.http.core.interceptor.RequestHeadersInterceptor;
import com.oculus.http.core.util.CustomApiEndpointValidator;
import com.oculus.http.customuseragentclient.CustomUserAgentHttpClient;
import com.oculus.http.defaultclient.DefaultHttpClient;
import com.oculus.http.headers.RequestHeaders;
import com.oculus.http.useragent.UserAgentString;
import java.util.List;
import java.util.Locale;
import retrofit.RestAdapter;

@InjectorModule
public class ApiModule extends AbstractC0031Bc {
    public static volatile AbstractC0054Ej _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_LOCK = new Object();

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForApiModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_BINDING_ID = 53;
        public static final int _UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_BINDING_ID = 13;
        public static final int _UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_BINDING_ID = 58;
        public static final int _UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID = 50;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_BINDING_ID = 34;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID = 51;
        public static final int _UL__ULSEP_retrofit_ErrorHandler_ULSEP_BINDING_ID = 82;
        public static final int _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_BINDING_ID = 25;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_BINDING_ID = 2119;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_BINDING_ID = 2051;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_BINDING_ID = 2115;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_BINDING_ID = 2054;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID = 2114;
    }

    @ApplicationScoped
    @ProviderMethod
    @DefaultHttpClient
    public static AbstractC0054Ej A00(AbstractC0054Ej ej, @ForAppContext Context context, PackageInfo packageInfo, @OculusApiEndpoint String str, @UserAgentString String str2, AbstractC0192Xx<Locale> xx, OculusAuthorizationInterceptor oculusAuthorizationInterceptor) {
        XQ xq = new XQ(ej);
        if (CustomApiEndpointValidator.DISABLE_CERT_CHECK_URI_PATTERN.matcher(str).matches()) {
            CustomApiEndpointValidator.A00(xq);
        }
        ApiInterceptor A00 = ApiInterceptor.A00(context, packageInfo, str2, xx);
        List<XS> list = xq.A0N;
        list.add(A00);
        list.add(oculusAuthorizationInterceptor);
        list.add(new GzipInterceptor());
        list.add(new S0());
        list.add(new NpeExceptionHandlerInterceptor());
        list.add(new RequestHeadersInterceptor(RequestHeaders.DEFAULT_REQUEST_HEADERS));
        return new AbstractC0054Ej(xq);
    }

    @CustomUserAgentHttpClient
    @ProviderMethod
    public static AbstractC0054Ej A01(AbstractC0054Ej ej, @ForAppContext Context context, PackageInfo packageInfo, @OculusApiEndpoint String str, AbstractC0192Xx<Locale> xx, OculusAuthorizationInterceptor oculusAuthorizationInterceptor) {
        XQ xq = new XQ(ej);
        if (CustomApiEndpointValidator.DISABLE_CERT_CHECK_URI_PATTERN.matcher(str).matches()) {
            CustomApiEndpointValidator.A00(xq);
        }
        ApiInterceptor A00 = ApiInterceptor.A00(context, packageInfo, null, xx);
        List<XS> list = xq.A0N;
        list.add(A00);
        list.add(oculusAuthorizationInterceptor);
        list.add(new GzipInterceptor());
        list.add(new S0());
        list.add(new NpeExceptionHandlerInterceptor());
        return new AbstractC0054Ej(xq);
    }
}
