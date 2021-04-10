package com.oculus.oktigon;

import com.facebook.http.annotations.HttpAnnotationsModule;
import com.facebook.http.annotations.UserAgentString;
import com.facebook.http.config.NetworkConfig;
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
import com.facebook.tigon.oktigon.OkTigonClient;
import com.facebook.tigon.oktigon.OkTigonModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.http.defaultclient.DefaultClientModule;
import com.oculus.http.defaultclient.DefaultHttpClient;
import com.oculus.http.useragent.UserAgentModule;
import java.lang.annotation.Annotation;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@InjectorModule
public class OculusOkTigonModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_facebook_http_config_NetworkConfig_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_http_config_NetworkConfig_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(NetworkConfig.class)));
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) UserAgentString.class)));
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(OkHttpClient.class, (Class<? extends Annotation>) OkTigonClient.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_BINDING_ID, injectorLike);
    }

    @OkTigonClient
    @ProviderMethod
    static OkHttpClient getOkHttpClient(@DefaultHttpClient OkHttpClient okHttpClient) {
        return okHttpClient;
    }

    @ProviderMethod
    static NetworkConfig provideNetworkConfig() {
        return null;
    }

    @UserAgentString
    @ProviderMethod
    static String provideUserAgentString(@com.oculus.http.useragent.UserAgentString String str) {
        return str;
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForOculusOkTigonModule {
        AutoGeneratedBindingsForOculusOkTigonModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(HttpAnnotationsModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(OkTigonModule.class);
                binder.require(DefaultClientModule.class);
                binder.require(UserAgentModule.class);
                binder.bind(NetworkConfig.class).toProvider(new NetworkConfigMethodAutoProvider());
                binder.bind(String.class).annotatedWith(UserAgentString.class).toProvider(new String_com_facebook_http_annotations_UserAgentStringMethodAutoProvider());
                binder.bind(OkHttpClient.class).annotatedWith(OkTigonClient.class).toProvider(new OkHttpClient_com_facebook_tigon_oktigon_OkTigonClientMethodAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final NetworkConfig _UL__ULSEP_com_facebook_http_config_NetworkConfig_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (NetworkConfig) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_http_config_NetworkConfig_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final NetworkConfig _UL__ULSEP_com_facebook_http_config_NetworkConfig_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideNetworkConfig();
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_http_config_NetworkConfig_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_facebook_http_config_NetworkConfig_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_okhttp3_OkHttpClient_ULGT__ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (String) UL.factorymap.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideUserAgentString(UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_http_config_NetworkConfig_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_facebook_http_config_NetworkConfig_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OkHttpClient) UL.factorymap.get(UL_id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OkHttpClient _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return getOkHttpClient(DefaultClientModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(injectorLike));
    }
}
