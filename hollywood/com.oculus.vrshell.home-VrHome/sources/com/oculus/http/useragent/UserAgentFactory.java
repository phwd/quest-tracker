package com.oculus.http.useragent;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.common.android.AndroidModule;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.locale.LocaleModule;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Provider;

public class UserAgentFactory {
    private InjectionContext $ul_mInjectionContext;
    private final String mAppNameInUserAgent;
    private final Context mContext;
    private final Provider<Locale> mLocaleProvider;
    private final PackageInfo mPackageInfo;

    public static final UserAgentFactory $ul_$xXXcom_oculus_http_useragent_UserAgentFactory$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (UserAgentFactory) UL.factorymap.get(UserAgentModule.UL_id.$ul_$xXXcom_oculus_http_useragent_UserAgentFactory$xXXBINDING_ID, $ul_injector);
    }

    public static final UserAgentFactory $ul_$xXXcom_oculus_http_useragent_UserAgentFactory$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return new UserAgentFactory($ul_injector, BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD($ul_injector), UserAgentModule.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXACCESS_METHOD($ul_injector), AndroidModule.$ul_$xXXandroid_content_pm_PackageInfo$xXXACCESS_METHOD($ul_injector), LocaleModule.$ul_$xXXjavax_inject_Provider$x3Cjava_util_Locale$x3E$xXXACCESS_METHOD($ul_injector));
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_http_useragent_UserAgentFactory$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UserAgentModule.UL_id.$ul_$xXXcom_oculus_http_useragent_UserAgentFactory$xXXBINDING_ID, $ul_injector);
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_http_useragent_UserAgentFactory$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UserAgentModule.UL_id.$ul_$xXXcom_oculus_http_useragent_UserAgentFactory$xXXBINDING_ID, $ul_injector);
    }

    @Inject
    public UserAgentFactory(InjectorLike $ul_injector, @ForAppContext Context context, @AppNameInUserAgent String appNameInUserAgent, PackageInfo packageInfo, Provider<Locale> localeProvider) {
        this.$ul_mInjectionContext = new InjectionContext(1, $ul_injector);
        this.mContext = context;
        this.mAppNameInUserAgent = appNameInUserAgent;
        this.mPackageInfo = packageInfo;
        this.mLocaleProvider = localeProvider;
    }

    public String makeUserAgent() {
        return new UserAgentBuilder(this.mContext).setHttpAgent(System.getProperty("http.agent")).setAppName(this.mAppNameInUserAgent).setAppVersion(this.mPackageInfo.versionName).setBuildVersion(String.valueOf(this.mPackageInfo.versionCode)).setLocale(this.mLocaleProvider.get().toString()).setPackageName(this.mContext.getPackageName()).setUserAgentAppVersionMap((String) FbInjector.lazyInstance(0, UserAgentModule.UL_id.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_UserAgentAppVersionMap$xXXBINDING_ID, this.$ul_mInjectionContext)).build();
    }
}
