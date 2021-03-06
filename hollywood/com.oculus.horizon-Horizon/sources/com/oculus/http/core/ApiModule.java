package com.oculus.http.core;

import X.AbstractC06640p5;
import X.AbstractC08380wS;
import X.AnonymousClass0J5;
import X.AnonymousClass0N1;
import X.AnonymousClass0Pi;
import X.AnonymousClass0UJ;
import X.AnonymousClass117;
import X.C003108z;
import X.C003809k;
import X.C04220h4;
import X.C08360wQ;
import android.content.Context;
import android.content.pm.PackageInfo;
import androidx.viewpager.widget.ViewPager;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.annotations.OculusApiEndpoint;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.core.interceptor.ApiInterceptor;
import com.oculus.http.core.interceptor.AuthorizationInterceptor;
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
import java.util.List;
import java.util.Locale;
import javax.inject.Provider;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;

@InjectorModule
public class ApiModule extends AnonymousClass0J5 {
    public static volatile AnonymousClass0N1 _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE;
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
        public static final int _UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_BINDING_ID = 254;
        public static final int _UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_BINDING_ID = 299;
        public static final int _UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_BINDING_ID = 43;
        public static final int _UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID = 479;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_BINDING_ID = 546;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID = 394;
        public static final int _UL__ULSEP_retrofit_ErrorHandler_ULSEP_BINDING_ID = 214;
        public static final int _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_BINDING_ID = 12;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_BINDING_ID = 280;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_BINDING_ID = 3;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_BINDING_ID = AuthorizationInterceptor.HTTP_STATUS_UNAUTHORIZED;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_BINDING_ID = 2104;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID = ViewPager.MIN_FLING_VELOCITY;
    }

    @AutoGeneratedFactoryMethod
    public static final Boolean A00() {
        return false;
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0N1 A01(AbstractC06640p5 r1) {
        return (AnonymousClass0N1) AnonymousClass117.A00(546, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass0N1 A03(AbstractC06640p5 r9) {
        if (_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_LOCK) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE, r9);
                if (A00 != null) {
                    try {
                        AbstractC06640p5 applicationInjector = r9.getApplicationInjector();
                        _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE = A04(HttpModule.A05(applicationInjector), C003108z.A00(applicationInjector), C003809k.A02(applicationInjector), EndpointModule.A06(applicationInjector), UserAgentModule.A01(applicationInjector), LocaleModule.A01(applicationInjector), OculusAuthorizationInterceptor.A00(applicationInjector));
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE;
    }

    @ApplicationScoped
    @ProviderMethod
    @DefaultHttpClient
    public static AnonymousClass0N1 A04(AnonymousClass0N1 r4, @ForAppContext Context context, PackageInfo packageInfo, @OculusApiEndpoint String str, @UserAgentString String str2, Provider<Locale> provider, OculusAuthorizationInterceptor oculusAuthorizationInterceptor) {
        C08360wQ r3 = new C08360wQ(r4);
        if (CustomApiEndpointValidator.DISABLE_CERT_CHECK_URI_PATTERN.matcher(str).matches()) {
            CustomApiEndpointValidator.A00(r3);
        }
        ApiInterceptor A00 = ApiInterceptor.A00(context, packageInfo, str2, provider);
        List<AbstractC08380wS> list = r3.A0N;
        list.add(A00);
        list.add(oculusAuthorizationInterceptor);
        list.add(new GzipInterceptor());
        list.add(new C04220h4());
        list.add(new NpeExceptionHandlerInterceptor());
        list.add(new RequestHeadersInterceptor(RequestHeaders.DEFAULT_REQUEST_HEADERS));
        return new AnonymousClass0N1(r3);
    }

    @CustomUserAgentHttpClient
    @ProviderMethod
    public static AnonymousClass0N1 A05(AnonymousClass0N1 r3, @ForAppContext Context context, PackageInfo packageInfo, @OculusApiEndpoint String str, Provider<Locale> provider, OculusAuthorizationInterceptor oculusAuthorizationInterceptor) {
        C08360wQ r2 = new C08360wQ(r3);
        if (CustomApiEndpointValidator.DISABLE_CERT_CHECK_URI_PATTERN.matcher(str).matches()) {
            CustomApiEndpointValidator.A00(r2);
        }
        ApiInterceptor A00 = ApiInterceptor.A00(context, packageInfo, null, provider);
        List<AbstractC08380wS> list = r2.A0N;
        list.add(A00);
        list.add(oculusAuthorizationInterceptor);
        list.add(new GzipInterceptor());
        list.add(new C04220h4());
        list.add(new NpeExceptionHandlerInterceptor());
        return new AnonymousClass0N1(r2);
    }

    @AutoGeneratedAccessMethod
    public static final ErrorHandler A06(AbstractC06640p5 r1) {
        return (ErrorHandler) AnonymousClass117.A00(214, r1);
    }

    @AutoGeneratedAccessMethod
    public static final RestAdapter.LogLevel A08(AbstractC06640p5 r1) {
        return (RestAdapter.LogLevel) AnonymousClass117.A00(12, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter A0A(AbstractC06640p5 r8) {
        if (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_LOCK) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE, r8);
                if (A00 != null) {
                    try {
                        AbstractC06640p5 applicationInjector = r8.getApplicationInjector();
                        AnonymousClass0N1 A002 = DefaultClientModule.A00(applicationInjector);
                        String A03 = EndpointModule.A03(applicationInjector);
                        ApiResponseConverter A003 = ApiResponseConverter.A00(applicationInjector);
                        RestAdapter.LogLevel A08 = A08(applicationInjector);
                        LoggingErrorHandler A004 = LoggingErrorHandler.A00(applicationInjector);
                        RestAdapter.Builder builder = new RestAdapter.Builder();
                        builder.setEndpoint(A03);
                        builder.setErrorHandler(A004);
                        builder.setClient(new AnonymousClass0UJ(A002));
                        builder.setConverter(A003);
                        builder.setLogLevel(A08);
                        _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE = builder.build();
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_INSTANCE;
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter A0B(AbstractC06640p5 r8) {
        if (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_LOCK) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE, r8);
                if (A00 != null) {
                    try {
                        AbstractC06640p5 applicationInjector = r8.getApplicationInjector();
                        AnonymousClass0N1 A002 = DefaultClientModule.A00(applicationInjector);
                        String A04 = EndpointModule.A04(applicationInjector);
                        ApiResponseConverter A003 = ApiResponseConverter.A00(applicationInjector);
                        RestAdapter.LogLevel A08 = A08(applicationInjector);
                        LoggingErrorHandler A004 = LoggingErrorHandler.A00(applicationInjector);
                        RestAdapter.Builder builder = new RestAdapter.Builder();
                        builder.setEndpoint(A04);
                        builder.setErrorHandler(A004);
                        builder.setClient(new AnonymousClass0UJ(A002));
                        builder.setConverter(A003);
                        builder.setLogLevel(A08);
                        _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE = builder.build();
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final RestAdapter A0C(AbstractC06640p5 r1) {
        return (RestAdapter) AnonymousClass117.A00(ViewPager.MIN_FLING_VELOCITY, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter A0D(AbstractC06640p5 r8) {
        if (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_LOCK) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE, r8);
                if (A00 != null) {
                    try {
                        AbstractC06640p5 applicationInjector = r8.getApplicationInjector();
                        AnonymousClass0N1 A002 = DefaultClientModule.A00(applicationInjector);
                        String A06 = EndpointModule.A06(applicationInjector);
                        ErrorHandler A062 = A06(applicationInjector);
                        ApiResponseConverter A003 = ApiResponseConverter.A00(applicationInjector);
                        RestAdapter.LogLevel A08 = A08(applicationInjector);
                        RestAdapter.Builder builder = new RestAdapter.Builder();
                        builder.setEndpoint(A06);
                        builder.setErrorHandler(A062);
                        builder.setClient(new AnonymousClass0UJ(A002));
                        builder.setConverter(A003);
                        builder.setLogLevel(A08);
                        _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE = builder.build();
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_INSTANCE;
    }

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass0N1 A02(AbstractC06640p5 r5) {
        return A05(HttpModule.A05(r5), C003108z.A00(r5), C003809k.A02(r5), EndpointModule.A06(r5), LocaleModule.A01(r5), OculusAuthorizationInterceptor.A00(r5));
    }

    @AutoGeneratedFactoryMethod
    public static final ErrorHandler A07(AbstractC06640p5 r0) {
        return LoggingErrorHandler.A00(r0);
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter A09(AbstractC06640p5 r6) {
        AnonymousClass0N1 A01 = A01(r6);
        String A06 = EndpointModule.A06(r6);
        ErrorHandler A062 = A06(r6);
        ApiResponseConverter A00 = ApiResponseConverter.A00(r6);
        RestAdapter.LogLevel A08 = A08(r6);
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(A06);
        builder.setErrorHandler(A062);
        builder.setClient(new AnonymousClass0UJ(A01));
        builder.setConverter(A00);
        builder.setLogLevel(A08);
        return builder.build();
    }
}
