package com.oculus.http.common;

import com.facebook.common.identifiers.IdentifiersModule;
import com.facebook.common.manifest.AppBuildInfo;
import com.facebook.common.manifest.ManifestModule;
import com.facebook.debug.log.BLog;
import com.facebook.http.config.SocketConfig;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.netlite.certificatepinning.FbPinningSSLContextFactory;
import com.facebook.netlite.certificatepinning.okhttp.FbCertificatePinnerFactory;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.common.build.BuildConstants;
import com.oculus.http.socketconfig.SocketConfigModule;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

@InjectorModule
public class HttpModule extends AbstractLibraryModule {
    private static final String TAG = "HttpModule";

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_okhttp3_CertificatePinner_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(CertificatePinner.class)));
        public static final int _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(ConnectionSpec.class)));
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(OkHttpClient.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForHttpModule {
        AutoGeneratedBindingsForHttpModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(IdentifiersModule.class);
                binder.require(ManifestModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(SocketConfigModule.class);
                binder.bind(CertificatePinner.class).toProvider(new CertificatePinnerMethodAutoProvider());
                binder.bind(ConnectionSpec.class).toProvider(new ConnectionSpecMethodAutoProvider());
                binder.bind(OkHttpClient.class).toProvider(new OkHttpClientMethodAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_CertificatePinner_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_okhttp3_CertificatePinner_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_ConnectionSpec_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_CertificatePinner_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_okhttp3_CertificatePinner_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_ConnectionSpec_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final CertificatePinner _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (CertificatePinner) UL.factorymap.get(UL_id._UL__ULSEP_okhttp3_CertificatePinner_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final CertificatePinner _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideCertificatePinner(ManifestModule._UL__ULSEP_com_facebook_common_manifest_AppBuildInfo_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final ConnectionSpec _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ConnectionSpec) UL.factorymap.get(UL_id._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ConnectionSpec _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideConnetionSpec();
    }

    @AutoGeneratedAccessMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OkHttpClient) UL.factorymap.get(UL_id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideOkHttpClient(SocketConfigModule._UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_ACCESS_METHOD(injectorLike), _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_ACCESS_METHOD(injectorLike), _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_ACCESS_METHOD(injectorLike), ManifestModule._UL__ULSEP_com_facebook_common_manifest_AppBuildInfo_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @ProviderMethod
    static OkHttpClient provideOkHttpClient(SocketConfig socketConfig, CertificatePinner certificatePinner, ConnectionSpec connectionSpec, AppBuildInfo appBuildInfo) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout((long) socketConfig.getConnectTimeoutMillis(), TimeUnit.MILLISECONDS);
        builder.readTimeout((long) socketConfig.getSocketTimeoutMillis(), TimeUnit.MILLISECONDS);
        builder.writeTimeout(0, TimeUnit.MILLISECONDS);
        builder.certificatePinner(certificatePinner);
        builder.connectionSpecs(Arrays.asList(connectionSpec));
        FbPinningSSLContextFactory fbPinningSSLContextFactory = new FbPinningSSLContextFactory(appBuildInfo.buildUTCTimestamp);
        if (BuildConstants.DEBUG) {
            fbPinningSSLContextFactory.enableSandbox();
        }
        try {
            builder.sslSocketFactory(fbPinningSSLContextFactory.create().getSocketFactory(), fbPinningSSLContextFactory.getTrustManager());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            BLog.e(TAG, "failed to setup ssl socket factory", e);
        }
        return builder.build();
    }

    @ProviderMethod
    static ConnectionSpec provideConnetionSpec() {
        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256).build();
    }

    @ProviderMethod
    static CertificatePinner provideCertificatePinner(AppBuildInfo appBuildInfo) {
        if (BuildConstants.DEBUG) {
            return FbCertificatePinnerFactory.createWithRampart(appBuildInfo.buildUTCTimestamp);
        }
        return FbCertificatePinnerFactory.create(appBuildInfo.buildUTCTimestamp);
    }
}
