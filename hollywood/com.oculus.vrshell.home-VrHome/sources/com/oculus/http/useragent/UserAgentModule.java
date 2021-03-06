package com.oculus.http.useragent;

import com.facebook.common.android.AndroidModule;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.locale.LocaleModule;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

public abstract class UserAgentModule extends AbstractLibraryModule {

    public static final class UL_id {
        public static final int $ul_$xXXcom_oculus_http_useragent_UserAgentFactory$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_oculus_http_useragent_UserAgentFactory$xXXBINDING_ID : UL.id.dynamicId(Key.get(UserAgentFactory.class)));
        public static final int $ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXBINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) AppNameInUserAgent.class)));
        public static final int $ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXBINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) UserAgentAppVersionMap.class)));
        public static final int $ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentString$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentString$xXXBINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) UserAgentString.class)));
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Cjava_lang_String$x3E$xXXcom_oculus_http_useragent_UserAgentString$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentString$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Cjava_lang_String$x3E$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Cjava_lang_String$x3E$xXXcom_oculus_http_useragent_UserAgentString$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentString$xXXBINDING_ID, $ul_injector);
    }

    /* access modifiers changed from: package-private */
    @AppNameInUserAgent
    public abstract String assertUserAgentAppName();

    static class AutoGeneratedBindingsForUserAgentModule {
        AutoGeneratedBindingsForUserAgentModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.assertBindingInstalled(String.class, AppNameInUserAgent.class);
                binder.require(AndroidModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(com.oculus.android.AndroidModule.class);
                binder.require(LocaleModule.class);
                binder.bind(UserAgentFactory.class).toProvider(new UserAgentFactoryAutoProvider());
                binder.bindDefault(String.class).annotatedWith(UserAgentAppVersionMap.class).toProvider(new String_com_oculus_http_useragent_UserAgentAppVersionMapMethodAutoProvider());
                binder.bind(String.class).annotatedWith(UserAgentString.class).toProvider(new String_com_oculus_http_useragent_UserAgentStringMethodAutoProvider());
            }
        }
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Cjava_lang_String$x3E$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXBINDING_ID, $ul_injector);
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Cjava_lang_String$x3E$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXBINDING_ID, $ul_injector);
    }

    public static final String $ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (String) UL.factorymap.get(UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXBINDING_ID, $ul_injector);
    }

    public static final String $ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (String) UL.factorymap.get(UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXBINDING_ID, $ul_injector);
    }

    public static final String $ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return provideUserAgentAppVersionMap();
    }

    public static final String $ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentString$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (String) UL.factorymap.get(UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentString$xXXBINDING_ID, $ul_injector);
    }

    public static final String $ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentString$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return provideUserAgentString(UserAgentFactory.$ul_$xXXcom_oculus_http_useragent_UserAgentFactory$xXXACCESS_METHOD($ul_injector));
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Cjava_lang_String$x3E$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXBINDING_ID, $ul_injector);
    }

    @UserAgentString
    static String provideUserAgentString(UserAgentFactory userAgentFactory) {
        return userAgentFactory.makeUserAgent();
    }

    @UserAgentAppVersionMap
    static String provideUserAgentAppVersionMap() {
        return "";
    }
}
