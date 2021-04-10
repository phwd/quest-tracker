package com.oculus.http.useragent;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.common.build.BuildConfig;
import java.lang.annotation.Annotation;

@InjectorModule
public abstract class UserAgentModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(UserAgentFactory.class)));
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) AppNameInUserAgent.class)));
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) UserAgentAppVersionMap.class)));
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) UserAgentString.class)));
    }

    @UserAgentAppVersionMap
    @ProviderMethod
    static String provideUserAgentAppVersionMap() {
        return BuildConfig.PROVIDER_SUFFIX;
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (String) UL.factorymap.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideUserAgentAppVersionMap();
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (String) UL.factorymap.get(UL_id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideUserAgentString(UserAgentFactory._UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @ProviderMethod
    @UserAgentString
    static String provideUserAgentString(UserAgentFactory userAgentFactory) {
        return userAgentFactory.makeUserAgent();
    }
}