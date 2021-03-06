package com.oculus.http.core;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0IP;
import X.AnonymousClass0Qj;
import X.AnonymousClass0Qr;
import X.AnonymousClass0VB;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1Su;
import X.AnonymousClass1TK;
import X.C00610Hs;
import X.C01150Rm;
import X.C06201Sb;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.annotations.CustomUserAgentOculusRestAdapter;
import com.oculus.http.core.annotations.FacebookApiEndpoint;
import com.oculus.http.core.annotations.FacebookApiRestAdapter;
import com.oculus.http.core.annotations.FacebookGraphEndpoint;
import com.oculus.http.core.annotations.FacebookGraphRestAdapter;
import com.oculus.http.core.annotations.FacebookGraphVideoEndpoint;
import com.oculus.http.core.annotations.FacebookGraphVideoRestAdapter;
import com.oculus.http.core.annotations.OculusApiEndpoint;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.annotations.ReportGraphBackendException;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.core.interceptor.ApiInterceptor;
import com.oculus.http.core.interceptor.GzipInterceptor;
import com.oculus.http.core.interceptor.NpeExceptionHandlerInterceptor;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import com.oculus.http.core.interceptor.RequestHeadersInterceptor;
import com.oculus.http.core.util.CustomApiEndpointValidator;
import com.oculus.http.customuseragentclient.CustomUserAgentHttpClient;
import com.oculus.http.defaultclient.DefaultClientModule;
import com.oculus.http.defaultclient.DefaultHttpClient;
import com.oculus.http.headers.RequestHeaders;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.http.useragent.UserAgentString;
import com.oculus.locale.LocaleModule;
import java.util.Locale;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;

@InjectorModule
public class ApiModule extends AnonymousClass0VI {
    public static volatile OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_LOCK = new Object();

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_BINDING_ID = 99;
        public static final int _UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_BINDING_ID = 12;
        public static final int _UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_BINDING_ID = 41;
        public static final int _UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID = 31;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_BINDING_ID = 89;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID = 97;
        public static final int _UL__ULSEP_retrofit_ErrorHandler_ULSEP_BINDING_ID = 65;
        public static final int _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_BINDING_ID = 16;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_BINDING_ID = 2137;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_BINDING_ID = 2051;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_BINDING_ID = 2090;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_BINDING_ID = 2053;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID = 100;
    }

    @AutoGeneratedFactoryMethod
    public static final Boolean _UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        C01150Rm.A00(false, r1);
        return false;
    }

    @ProviderMethod(asDefault = true)
    public static ErrorHandler provideErrorHandler(LoggingErrorHandler loggingErrorHandler) {
        return loggingErrorHandler;
    }

    @ReportGraphBackendException
    @ProviderMethod(asDefault = true)
    public static Boolean provideReportGraphBackendException() {
        return false;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_Boolean_ULGT__ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(31, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(89, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(97, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_retrofit_ErrorHandler_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(65, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_retrofit_RestAdapter_LogLevel_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(16, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(2137, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(2051, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(2090, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(2053, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(100, r2);
    }

    @AutoGeneratedAccessMethod
    public static final Boolean _UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (Boolean) AnonymousClass1TK.A00(31, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(31, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(89, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(97, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_retrofit_ErrorHandler_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(65, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_retrofit_RestAdapter_LogLevel_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(16, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(2137, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(2051, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(2090, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(2053, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_retrofit_RestAdapter_ULGT__ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(100, r2);
    }

    @AutoGeneratedAccessMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (OkHttpClient) AnonymousClass1TK.A00(89, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (OkHttpClient) AnonymousClass1TK.A00(97, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_FACTORY_METHOD(AnonymousClass0lg r9, Object obj) {
        if (_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_LOCK) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE, r9);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r9.getApplicationInjector();
                        OkHttpClient provideDefaultHttpClient = provideDefaultHttpClient(HttpModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_ACCESS_METHOD(applicationInjector), C00610Hs.A00(applicationInjector), AnonymousClass0IP.A01(applicationInjector), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(applicationInjector), UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_ACCESS_METHOD(applicationInjector), LocaleModule._UL__ULSEP_javax_inject_Provider_ULLT_java_util_Locale_ULGT__ULSEP_ACCESS_METHOD(applicationInjector), OculusAuthorizationInterceptor._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_ACCESS_METHOD(applicationInjector));
                        C01150Rm.A00(provideDefaultHttpClient, applicationInjector);
                        _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE = provideDefaultHttpClient;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final ErrorHandler _UL__ULSEP_retrofit_ErrorHandler_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (ErrorHandler) AnonymousClass1TK.A00(65, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final RestAdapter.LogLevel _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (RestAdapter.LogLevel) AnonymousClass1TK.A00(16, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter.LogLevel _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.NONE;
        C01150Rm.A00(logLevel, r1);
        return logLevel;
    }

    @AutoGeneratedAccessMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (RestAdapter) AnonymousClass1TK.A00(2137, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (RestAdapter) AnonymousClass1TK.A00(2051, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_FACTORY_METHOD(AnonymousClass0lg r8, Object obj) {
        if (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_LOCK) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE, r8);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r8.getApplicationInjector();
                        RestAdapter provideFacebookApiRestAdapter = provideFacebookApiRestAdapter(DefaultClientModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(applicationInjector), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_ACCESS_METHOD(applicationInjector), ApiResponseConverter._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_ACCESS_METHOD(applicationInjector), _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_ACCESS_METHOD(applicationInjector), LoggingErrorHandler._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD(applicationInjector));
                        C01150Rm.A00(provideFacebookApiRestAdapter, applicationInjector);
                        _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE = provideFacebookApiRestAdapter;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (RestAdapter) AnonymousClass1TK.A00(2090, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_FACTORY_METHOD(AnonymousClass0lg r8, Object obj) {
        if (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_LOCK) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE, r8);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r8.getApplicationInjector();
                        RestAdapter provideFacebookGraphRestAdapter = provideFacebookGraphRestAdapter(DefaultClientModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(applicationInjector), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_ACCESS_METHOD(applicationInjector), ApiResponseConverter._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_ACCESS_METHOD(applicationInjector), _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_ACCESS_METHOD(applicationInjector), LoggingErrorHandler._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD(applicationInjector));
                        C01150Rm.A00(provideFacebookGraphRestAdapter, applicationInjector);
                        _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE = provideFacebookGraphRestAdapter;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (RestAdapter) AnonymousClass1TK.A00(2053, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_FACTORY_METHOD(AnonymousClass0lg r8, Object obj) {
        if (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_LOCK) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_INSTANCE, r8);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r8.getApplicationInjector();
                        RestAdapter provideFacebookGraphVideoRestAdapter = provideFacebookGraphVideoRestAdapter(DefaultClientModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(applicationInjector), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_ACCESS_METHOD(applicationInjector), ApiResponseConverter._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_ACCESS_METHOD(applicationInjector), _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_ACCESS_METHOD(applicationInjector), LoggingErrorHandler._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD(applicationInjector));
                        C01150Rm.A00(provideFacebookGraphVideoRestAdapter, applicationInjector);
                        _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_INSTANCE = provideFacebookGraphVideoRestAdapter;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (RestAdapter) AnonymousClass1TK.A00(100, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_FACTORY_METHOD(AnonymousClass0lg r8, Object obj) {
        if (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_LOCK) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE, r8);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r8.getApplicationInjector();
                        RestAdapter provideOculusRestAdapter = provideOculusRestAdapter(DefaultClientModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(applicationInjector), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(applicationInjector), _UL__ULSEP_retrofit_ErrorHandler_ULSEP_ACCESS_METHOD(applicationInjector), ApiResponseConverter._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_ACCESS_METHOD(applicationInjector), _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_ACCESS_METHOD(applicationInjector));
                        C01150Rm.A00(provideOculusRestAdapter, applicationInjector);
                        _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE = provideOculusRestAdapter;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE;
    }

    @ProviderMethod
    @CustomUserAgentOculusRestAdapter
    public static RestAdapter provideCustomUserAgentOculusRestAdapter(@CustomUserAgentHttpClient OkHttpClient okHttpClient, @OculusApiEndpoint String str, ErrorHandler errorHandler, ApiResponseConverter apiResponseConverter, RestAdapter.LogLevel logLevel) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(str);
        builder.setErrorHandler(errorHandler);
        builder.setClient(new AnonymousClass1Su(okHttpClient));
        builder.setConverter(apiResponseConverter);
        builder.setLogLevel(logLevel);
        return builder.build();
    }

    @FacebookApiRestAdapter
    @ApplicationScoped
    @ProviderMethod(asDefault = true)
    public static RestAdapter provideFacebookApiRestAdapter(@DefaultHttpClient OkHttpClient okHttpClient, @FacebookApiEndpoint String str, ApiResponseConverter apiResponseConverter, RestAdapter.LogLevel logLevel, LoggingErrorHandler loggingErrorHandler) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(str);
        builder.setErrorHandler(loggingErrorHandler);
        builder.setClient(new AnonymousClass1Su(okHttpClient));
        builder.setConverter(apiResponseConverter);
        builder.setLogLevel(logLevel);
        return builder.build();
    }

    @ApplicationScoped
    @ProviderMethod(asDefault = true)
    @FacebookGraphRestAdapter
    public static RestAdapter provideFacebookGraphRestAdapter(@DefaultHttpClient OkHttpClient okHttpClient, @FacebookGraphEndpoint String str, ApiResponseConverter apiResponseConverter, RestAdapter.LogLevel logLevel, LoggingErrorHandler loggingErrorHandler) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(str);
        builder.setErrorHandler(loggingErrorHandler);
        builder.setClient(new AnonymousClass1Su(okHttpClient));
        builder.setConverter(apiResponseConverter);
        builder.setLogLevel(logLevel);
        return builder.build();
    }

    @ApplicationScoped
    @ProviderMethod(asDefault = true)
    @FacebookGraphVideoRestAdapter
    public static RestAdapter provideFacebookGraphVideoRestAdapter(@DefaultHttpClient OkHttpClient okHttpClient, @FacebookGraphVideoEndpoint String str, ApiResponseConverter apiResponseConverter, RestAdapter.LogLevel logLevel, LoggingErrorHandler loggingErrorHandler) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(str);
        builder.setErrorHandler(loggingErrorHandler);
        builder.setClient(new AnonymousClass1Su(okHttpClient));
        builder.setConverter(apiResponseConverter);
        builder.setLogLevel(logLevel);
        return builder.build();
    }

    @ProviderMethod(asDefault = true)
    public static RestAdapter.LogLevel provideLogLevel() {
        return RestAdapter.LogLevel.NONE;
    }

    @OculusRestAdapter
    @ApplicationScoped
    @ProviderMethod
    public static RestAdapter provideOculusRestAdapter(@DefaultHttpClient OkHttpClient okHttpClient, @OculusApiEndpoint String str, ErrorHandler errorHandler, ApiResponseConverter apiResponseConverter, RestAdapter.LogLevel logLevel) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(str);
        builder.setErrorHandler(errorHandler);
        builder.setClient(new AnonymousClass1Su(okHttpClient));
        builder.setConverter(apiResponseConverter);
        builder.setLogLevel(logLevel);
        return builder.build();
    }

    @AutoGeneratedFactoryMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_FACTORY_METHOD(AnonymousClass0lg r6, Object obj) {
        OkHttpClient provideCustomUserAgentHttpClient = provideCustomUserAgentHttpClient(HttpModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_ACCESS_METHOD(r6), C00610Hs.A00(r6), AnonymousClass0IP.A01(r6), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(r6), LocaleModule._UL__ULSEP_javax_inject_Provider_ULLT_java_util_Locale_ULGT__ULSEP_ACCESS_METHOD(r6), OculusAuthorizationInterceptor._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_ACCESS_METHOD(r6));
        C01150Rm.A00(provideCustomUserAgentHttpClient, r6);
        return provideCustomUserAgentHttpClient;
    }

    @AutoGeneratedFactoryMethod
    public static final ErrorHandler _UL__ULSEP_retrofit_ErrorHandler_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        LoggingErrorHandler _UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD = LoggingErrorHandler._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD(r1);
        C01150Rm.A00(_UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD, r1);
        return _UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD;
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_FACTORY_METHOD(AnonymousClass0lg r5, Object obj) {
        RestAdapter provideCustomUserAgentOculusRestAdapter = provideCustomUserAgentOculusRestAdapter(_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_ACCESS_METHOD(r5), EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_ACCESS_METHOD(r5), _UL__ULSEP_retrofit_ErrorHandler_ULSEP_ACCESS_METHOD(r5), ApiResponseConverter._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_ACCESS_METHOD(r5), _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_ACCESS_METHOD(r5));
        C01150Rm.A00(provideCustomUserAgentOculusRestAdapter, r5);
        return provideCustomUserAgentOculusRestAdapter;
    }

    @CustomUserAgentHttpClient
    @ProviderMethod
    public static OkHttpClient provideCustomUserAgentHttpClient(OkHttpClient okHttpClient, @ForAppContext Context context, PackageInfo packageInfo, @OculusApiEndpoint String str, Provider<Locale> provider, OculusAuthorizationInterceptor oculusAuthorizationInterceptor) {
        OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
        if (CustomApiEndpointValidator.isDisabledUriValid(str)) {
            CustomApiEndpointValidator.disableCertCheckForHttpClient(newBuilder);
        }
        newBuilder.networkInterceptors.add(ApiInterceptor.create(context, packageInfo, null, provider));
        newBuilder.networkInterceptors.add(oculusAuthorizationInterceptor);
        newBuilder.networkInterceptors.add(new GzipInterceptor());
        newBuilder.networkInterceptors.add(new C06201Sb());
        newBuilder.networkInterceptors.add(new NpeExceptionHandlerInterceptor());
        return newBuilder.build();
    }

    @ApplicationScoped
    @ProviderMethod
    @DefaultHttpClient
    public static OkHttpClient provideDefaultHttpClient(OkHttpClient okHttpClient, @ForAppContext Context context, PackageInfo packageInfo, @OculusApiEndpoint String str, @UserAgentString String str2, Provider<Locale> provider, OculusAuthorizationInterceptor oculusAuthorizationInterceptor) {
        OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
        if (CustomApiEndpointValidator.isDisabledUriValid(str)) {
            CustomApiEndpointValidator.disableCertCheckForHttpClient(newBuilder);
        }
        newBuilder.networkInterceptors.add(ApiInterceptor.create(context, packageInfo, str2, provider));
        newBuilder.networkInterceptors.add(oculusAuthorizationInterceptor);
        newBuilder.networkInterceptors.add(new GzipInterceptor());
        newBuilder.networkInterceptors.add(new C06201Sb());
        newBuilder.networkInterceptors.add(new NpeExceptionHandlerInterceptor());
        newBuilder.networkInterceptors.add(new RequestHeadersInterceptor(RequestHeaders.DEFAULT_REQUEST_HEADERS));
        return newBuilder.build();
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForApiModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }
}
