package com.oculus.http.useragent;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0J5;
import X.AnonymousClass117;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public abstract class UserAgentModule extends AnonymousClass0J5 {

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForUserAgentModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_BINDING_ID = 446;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID = 171;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID = 18;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID = 62;
    }

    @AutoGeneratedAccessMethod
    public static final String A00(AbstractC06640p5 r1) {
        return (String) AnonymousClass117.A00(171, r1);
    }

    @AutoGeneratedAccessMethod
    public static final String A01(AbstractC06640p5 r1) {
        return (String) AnonymousClass117.A00(62, r1);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: java.util.Map<java.lang.String, java.lang.String> */
    /* JADX WARN: Multi-variable type inference failed */
    @ProviderMethod
    @UserAgentString
    public static String A03(UserAgentFactory userAgentFactory) {
        UserAgentBuilder userAgentBuilder = new UserAgentBuilder(userAgentFactory.mContext);
        userAgentBuilder.mHttpAgent = System.getProperty("http.agent");
        userAgentBuilder.mProperties.put("FBAN", userAgentFactory.mAppNameInUserAgent);
        userAgentBuilder.mProperties.put("FBAV", userAgentFactory.mPackageInfo.versionName);
        userAgentBuilder.mProperties.put("FBBV", String.valueOf(userAgentFactory.mPackageInfo.versionCode));
        userAgentBuilder.mProperties.put("FBLC", userAgentFactory.mLocaleProvider.get().toString());
        userAgentBuilder.mProperties.put("FBPN", userAgentFactory.mContext.getPackageName());
        userAgentBuilder.mProperties.put(UserAgentBuilder.FB_APP_VERSION_MAP, AnonymousClass0J2.A03(0, 18, userAgentFactory._UL_mInjectionContext));
        return userAgentBuilder.A01();
    }

    @AutoGeneratedFactoryMethod
    public static final String A02(AbstractC06640p5 r0) {
        return A03(UserAgentFactory.A00(r0));
    }
}
