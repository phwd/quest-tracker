package com.oculus.alpenglow.http;

import X.AbstractC01750Lk;
import X.AbstractC02990bJ;
import X.AbstractC05850lW;
import X.AnonymousClass006;
import X.AnonymousClass08D;
import X.AnonymousClass0LG;
import X.AnonymousClass0P6;
import X.AnonymousClass0Qe;
import X.AnonymousClass0Qs;
import X.AnonymousClass0T7;
import X.AnonymousClass0T8;
import X.AnonymousClass0TB;
import X.AnonymousClass0Vp;
import X.AnonymousClass0m5;
import X.AnonymousClass0m6;
import X.AnonymousClass13m;
import X.C01400Gn;
import X.C01630Kf;
import X.C03200bn;
import X.C04670hG;
import X.C05810lS;
import X.C06320mZ;
import X.C06330ma;
import X.C06340mb;
import X.C06370me;
import X.EnumC05630kA;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.alpenglow.http.annotations.GraphTimeout;
import com.oculus.http.defaultclient.DefaultClientModule;
import com.oculus.http.defaultclient.DefaultHttpClient;
import com.oculus.util.constants.OculusConstants;
import com.squareup.okhttp.internal.framed.Hpack;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import retrofit.RestAdapter;

@InjectorModule
public abstract class HttpModule extends AbstractC01750Lk {
    public static final String HARDWARE_GRAPH_ENDPOINT_SYSPROP = "alpenglow_graph_endpoint";
    public static volatile AnonymousClass0P6 _UL__ULSEP_com_facebook_graphql_query_interfaces_IGraphQLQueryExecutor_ULSEP_INSTANCE;
    public static volatile AnonymousClass08D _UL__ULSEP_com_google_gson_Gson_ULSEP_INSTANCE;
    public static volatile Long _UL__ULSEP_java_lang_Long_ULSEP_com_oculus_alpenglow_http_annotations_GraphTimeout_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_java_lang_Long_ULSEP_com_oculus_alpenglow_http_annotations_GraphTimeout_ULSEP_LOCK = new Object();
    public static volatile String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_alpenglow_http_annotations_HardwareGraphEndpoint_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_java_lang_String_ULSEP_com_oculus_alpenglow_http_annotations_HardwareGraphEndpoint_ULSEP_LOCK = new Object();
    public static volatile String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_LOCK = new Object();
    public static volatile AnonymousClass0Qs _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE;
    public static final Object _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_LOCK = new Object();
    public static volatile RestAdapter _UL__ULSEP_retrofit_RestAdapter_ULSEP_INSTANCE;

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForHttpModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_facebook_common_time_Clock_ULSEP_BINDING_ID = Hpack.PREFIX_7_BITS;
        public static final int _UL__ULSEP_com_facebook_graphql_query_interfaces_IGraphQLQueryExecutor_ULSEP_BINDING_ID = 51;
        public static final int _UL__ULSEP_com_google_gson_Gson_ULSEP_BINDING_ID = 66;
        public static final int _UL__ULSEP_com_oculus_alpenglow_http_DeviceAuthorizationInterceptor_ULSEP_BINDING_ID = 21;
        public static final int _UL__ULSEP_com_oculus_alpenglow_http_EnterpriseServerGraphQLService_ULSEP_BINDING_ID = 63;
        public static final int _UL__ULSEP_com_oculus_alpenglow_http_HttpMethods_ULSEP_BINDING_ID = 109;
        public static final int _UL__ULSEP_com_oculus_alpenglow_http_UserAgentInterceptor_ULSEP_BINDING_ID = 30;
        public static final int _UL__ULSEP_java_lang_Long_ULSEP_com_oculus_alpenglow_http_annotations_GraphTimeout_ULSEP_BINDING_ID = 96;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_alpenglow_http_annotations_HardwareGraphEndpoint_ULSEP_BINDING_ID = 28;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID = 22;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_alpenglow_http_IGsonTypeAdapter_ULGT__ULSEP_BINDING_ID = 91;
        public static final int _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_BINDING_ID = 5;
        public static final int _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID = 47;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID = 113;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_BINDING_ID = 20;
    }

    @AutoGeneratedAccessMethod
    public static final C06330ma A0B(AbstractC02990bJ r1) {
        return (C06330ma) AnonymousClass13m.A00(5, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass0LG A00() {
        return C03200bn.A00;
    }

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass0P6 A01(AbstractC02990bJ r3) {
        if (_UL__ULSEP_com_facebook_graphql_query_interfaces_IGraphQLQueryExecutor_ULSEP_INSTANCE == null) {
            synchronized (AnonymousClass0P6.class) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_com_facebook_graphql_query_interfaces_IGraphQLQueryExecutor_ULSEP_INSTANCE, r3);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_facebook_graphql_query_interfaces_IGraphQLQueryExecutor_ULSEP_INSTANCE = EnterpriseServerGraphQLService.A00(r3.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_graphql_query_interfaces_IGraphQLQueryExecutor_ULSEP_INSTANCE;
    }

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass08D A02(AbstractC02990bJ r3) {
        if (_UL__ULSEP_com_google_gson_Gson_ULSEP_INSTANCE == null) {
            synchronized (AnonymousClass08D.class) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_com_google_gson_Gson_ULSEP_INSTANCE, r3);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_google_gson_Gson_ULSEP_INSTANCE = A03(A09(r3.getApplicationInjector()));
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_google_gson_Gson_ULSEP_INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0046, code lost:
        if ((r6 instanceof X.AnonymousClass0Bd) != false) goto L_0x0048;
     */
    @com.facebook.inject.ApplicationScoped
    @com.facebook.inject.ProviderMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.AnonymousClass08D A03(java.util.Set<com.oculus.alpenglow.http.IGsonTypeAdapter> r11) {
        /*
        // Method dump skipped, instructions count: 180
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.alpenglow.http.HttpModule.A03(java.util.Set):X.08D");
    }

    @AutoGeneratedAccessMethod
    public static final Long A04(AbstractC02990bJ r1) {
        return (Long) AnonymousClass13m.A00(96, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final Long A05(AbstractC02990bJ r4) {
        long j;
        if (_UL__ULSEP_java_lang_Long_ULSEP_com_oculus_alpenglow_http_annotations_GraphTimeout_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_java_lang_Long_ULSEP_com_oculus_alpenglow_http_annotations_GraphTimeout_ULSEP_LOCK) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_java_lang_Long_ULSEP_com_oculus_alpenglow_http_annotations_GraphTimeout_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        r4.getApplicationInjector();
                        if (TextUtils.isEmpty(SystemProperties.get(HARDWARE_GRAPH_ENDPOINT_SYSPROP))) {
                            j = 15000;
                        } else {
                            j = 120000;
                        }
                        _UL__ULSEP_java_lang_Long_ULSEP_com_oculus_alpenglow_http_annotations_GraphTimeout_ULSEP_INSTANCE = Long.valueOf(j);
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_java_lang_Long_ULSEP_com_oculus_alpenglow_http_annotations_GraphTimeout_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final String A06(AbstractC02990bJ r1) {
        return (String) AnonymousClass13m.A00(28, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final String A07(AbstractC02990bJ r4) {
        if (_UL__ULSEP_java_lang_String_ULSEP_com_oculus_alpenglow_http_annotations_HardwareGraphEndpoint_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_java_lang_String_ULSEP_com_oculus_alpenglow_http_annotations_HardwareGraphEndpoint_ULSEP_LOCK) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_java_lang_String_ULSEP_com_oculus_alpenglow_http_annotations_HardwareGraphEndpoint_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        r4.getApplicationInjector();
                        _UL__ULSEP_java_lang_String_ULSEP_com_oculus_alpenglow_http_annotations_HardwareGraphEndpoint_ULSEP_INSTANCE = SystemProperties.get(HARDWARE_GRAPH_ENDPOINT_SYSPROP, Constants.HARDWARE_GRAPH_ENDPOINT);
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_java_lang_String_ULSEP_com_oculus_alpenglow_http_annotations_HardwareGraphEndpoint_ULSEP_INSTANCE;
    }

    @AutoGeneratedFactoryMethod
    public static final String A08(AbstractC02990bJ r3) {
        if (_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_LOCK) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_INSTANCE, r3);
                if (A00 != null) {
                    try {
                        r3.getApplicationInjector();
                        _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_INSTANCE = OculusConstants.ALPENGLOW_APP_NAME;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Set A09(AbstractC02990bJ r1) {
        return (Set) AnonymousClass13m.A00(91, r1);
    }

    @ProviderMethod
    public static C06330ma A0A(C01630Kf r13) {
        if (r13.A00 < System.currentTimeMillis() - 31536000000L) {
            return C06330ma.A02;
        }
        C06370me r11 = new C06370me();
        String[] strArr = AnonymousClass0T8.A00;
        for (String str : strArr) {
            String[] strArr2 = AnonymousClass0TB.A00;
            for (String str2 : strArr2) {
                String[] strArr3 = {AnonymousClass006.A05("sha256/", str)};
                if (str2 != null) {
                    String str3 = strArr3[0];
                    List<C06340mb> list = r11.A00;
                    list.add(new C06340mb(str2, str3));
                    String A05 = AnonymousClass006.A05("*.", str2);
                    String[] strArr4 = {AnonymousClass006.A05("sha256/", str)};
                    if (A05 != null) {
                        list.add(new C06340mb(A05, strArr4[0]));
                    }
                }
                throw new NullPointerException("pattern == null");
            }
        }
        return new C06330ma(new LinkedHashSet(r11.A00), null);
    }

    @ProviderMethod
    public static AnonymousClass0m5 A0D() {
        AnonymousClass0m6 r3 = new AnonymousClass0m6(AnonymousClass0m5.A06);
        r3.A03(EnumC05630kA.TLS_1_2);
        r3.A02(C06320mZ.A0b, C06320mZ.A0n, C06320mZ.A0G);
        return new AnonymousClass0m5(r3);
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0m5 A0F(AbstractC02990bJ r1) {
        return (AnonymousClass0m5) AnonymousClass13m.A00(47, r1);
    }

    @ApplicationScoped
    @ProviderMethod
    @DefaultHttpClient
    public static AnonymousClass0Qs A0G(C01630Kf r6, AnonymousClass0m5 r7, C06330ma r8, DeviceAuthorizationInterceptor deviceAuthorizationInterceptor, UserAgentInterceptor userAgentInterceptor, @GraphTimeout Long l) {
        C05810lS r2 = new C05810lS();
        long longValue = l.longValue();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        r2.A0A = C05810lS.A00(longValue, timeUnit);
        r2.A0B = C05810lS.A00(longValue, timeUnit);
        r2.A0C = C05810lS.A00(0, timeUnit);
        if (r8 != null) {
            r2.A0F = r8;
            r2.A0D = Collections.unmodifiableList(new ArrayList(Arrays.asList(r7)));
            AnonymousClass0T7 r1 = new AnonymousClass0T7(r6.A00);
            SSLContext instance = SSLContext.getInstance("TLS");
            X509TrustManager[] x509TrustManagerArr = r1.A00;
            instance.init(null, x509TrustManagerArr, null);
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            X509TrustManager x509TrustManager = x509TrustManagerArr[0];
            if (socketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            } else if (x509TrustManager != null) {
                r2.A0E = socketFactory;
                r2.A0G = C04670hG.A00.A03(x509TrustManager);
                List<AbstractC05850lW> list = r2.A0I;
                list.add(deviceAuthorizationInterceptor);
                list.add(userAgentInterceptor);
                return new AnonymousClass0Qs(r2);
            } else {
                try {
                    throw new NullPointerException("trustManager == null");
                } catch (KeyManagementException | NoSuchAlgorithmException e) {
                    throw new RuntimeException("failed to setup ssl socket factory", e);
                }
            }
        } else {
            throw new NullPointerException("certificatePinner == null");
        }
    }

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass0Qs A0H(AbstractC02990bJ r8) {
        if (_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_LOCK) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE, r8);
                if (A00 != null) {
                    try {
                        AbstractC02990bJ applicationInjector = r8.getApplicationInjector();
                        _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE = A0G(C01400Gn.A00(applicationInjector), A0F(applicationInjector), A0B(applicationInjector), DeviceAuthorizationInterceptor.A00(applicationInjector), UserAgentInterceptor.A00(applicationInjector), A04(applicationInjector));
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final RestAdapter A0I(AbstractC02990bJ r1) {
        return (RestAdapter) AnonymousClass13m.A00(20, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final RestAdapter A0J(AbstractC02990bJ r5) {
        if (_UL__ULSEP_retrofit_RestAdapter_ULSEP_INSTANCE == null) {
            synchronized (RestAdapter.class) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_retrofit_RestAdapter_ULSEP_INSTANCE, r5);
                if (A00 != null) {
                    try {
                        AbstractC02990bJ applicationInjector = r5.getApplicationInjector();
                        AnonymousClass0Qs A002 = DefaultClientModule.A00(applicationInjector);
                        String A06 = A06(applicationInjector);
                        RestAdapter.Builder builder = new RestAdapter.Builder();
                        builder.setEndpoint(A06);
                        builder.setClient(new AnonymousClass0Vp(A002));
                        builder.setLogLevel(RestAdapter.LogLevel.FULL);
                        _UL__ULSEP_retrofit_RestAdapter_ULSEP_INSTANCE = builder.build();
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_retrofit_RestAdapter_ULSEP_INSTANCE;
    }

    @AutoGeneratedFactoryMethod
    public static final C06330ma A0C(AbstractC02990bJ r0) {
        return A0A(C01400Gn.A00(r0));
    }

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass0m5 A0E() {
        return A0D();
    }
}
