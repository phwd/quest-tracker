package com.oculus.http.common;

import X.AbstractC00970Od;
import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0IG;
import X.AnonymousClass0JH;
import X.AnonymousClass0MD;
import X.AnonymousClass0Qr;
import X.AnonymousClass0UY;
import X.AnonymousClass0Ud;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C01150Rm;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.http.socketconfig.SocketConfigModule;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.internal.Util;

@InjectorModule
public class HttpModule extends AnonymousClass0VI {
    public static final String TAG = "HttpModule";

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_BINDING_ID = 5;
        public static final int _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID = 48;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID = 54;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_CertificatePinner_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(5, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_CertificatePinner_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(5, r1);
    }

    @AutoGeneratedAccessMethod
    public static final CertificatePinner _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (CertificatePinner) AnonymousClass1TK.A00(5, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_ConnectionSpec_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(48, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(54, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_ConnectionSpec_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(48, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(54, r1);
    }

    @AutoGeneratedAccessMethod
    public static final ConnectionSpec _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (ConnectionSpec) AnonymousClass1TK.A00(48, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (OkHttpClient) AnonymousClass1TK.A00(54, r2, null);
    }

    @ProviderMethod
    public static CertificatePinner provideCertificatePinner(AnonymousClass0JH r1) {
        return AnonymousClass0Ud.A00(r1.A00);
    }

    @ProviderMethod
    public static ConnectionSpec provideConnetionSpec() {
        ConnectionSpec.Builder builder = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS);
        builder.tlsVersions(TlsVersion.TLS_1_2);
        builder.cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256);
        return new ConnectionSpec(builder);
    }

    @ProviderMethod
    public static OkHttpClient provideOkHttpClient(AbstractC00970Od r4, CertificatePinner certificatePinner, ConnectionSpec connectionSpec, AnonymousClass0JH r7) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        builder.connectTimeout((long) r4.getConnectTimeoutMillis(), timeUnit);
        builder.readTimeout((long) r4.getSocketTimeoutMillis(), timeUnit);
        builder.writeTimeout(0, timeUnit);
        builder.certificatePinner(certificatePinner);
        builder.connectionSpecs = Util.immutableList(Arrays.asList(connectionSpec));
        AnonymousClass0UY r2 = new AnonymousClass0UY(r7.A00);
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            X509TrustManager[] x509TrustManagerArr = r2.A00;
            instance.init(null, x509TrustManagerArr, null);
            builder.sslSocketFactory(instance.getSocketFactory(), x509TrustManagerArr[0]);
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            AnonymousClass0MD.A07(TAG, "failed to setup ssl socket factory", e);
        }
        return builder.build();
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForHttpModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }

    @AutoGeneratedFactoryMethod
    public static final CertificatePinner _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_FACTORY_METHOD(AnonymousClass0lg r2, Object obj) {
        CertificatePinner A00 = AnonymousClass0Ud.A00(AnonymousClass0IG.A00(r2).A00);
        C01150Rm.A00(A00, r2);
        return A00;
    }

    @AutoGeneratedFactoryMethod
    public static final ConnectionSpec _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        ConnectionSpec provideConnetionSpec = provideConnetionSpec();
        C01150Rm.A00(provideConnetionSpec, r1);
        return provideConnetionSpec;
    }

    @AutoGeneratedFactoryMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_FACTORY_METHOD(AnonymousClass0lg r4, Object obj) {
        OkHttpClient provideOkHttpClient = provideOkHttpClient(SocketConfigModule._UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_ACCESS_METHOD(r4), _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_ACCESS_METHOD(r4), _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_ACCESS_METHOD(r4), AnonymousClass0IG.A00(r4));
        C01150Rm.A00(provideOkHttpClient, r4);
        return provideOkHttpClient;
    }
}