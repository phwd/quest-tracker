package com.oculus.http.useragent;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0IP;
import X.AnonymousClass0RE;
import X.AnonymousClass0VC;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C00610Hs;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.locale.LocaleModule;
import java.util.Locale;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID"})
public class UserAgentFactory {
    public AnonymousClass0RE _UL_mInjectionContext;
    public final String mAppNameInUserAgent;
    public final Context mContext;
    public final Provider<Locale> mLocaleProvider;
    public final PackageInfo mPackageInfo;

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_http_useragent_UserAgentFactory_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(7, r1);
    }

    @AutoGeneratedAccessMethod
    public static final UserAgentFactory _UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (UserAgentFactory) AnonymousClass1TK.A00(7, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final UserAgentFactory _UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_FACTORY_METHOD(AnonymousClass0lg r4, Object obj) {
        return new UserAgentFactory(r4, C00610Hs.A00(r4), UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_ACCESS_METHOD(r4), AnonymousClass0IP.A01(r4), LocaleModule._UL__ULSEP_javax_inject_Provider_ULLT_java_util_Locale_ULGT__ULSEP_ACCESS_METHOD(r4));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_http_useragent_UserAgentFactory_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(7, r1);
    }

    public String makeUserAgent() {
        UserAgentBuilder userAgentBuilder = new UserAgentBuilder(this.mContext);
        userAgentBuilder.mHttpAgent = System.getProperty("http.agent");
        userAgentBuilder.setAppName(this.mAppNameInUserAgent);
        userAgentBuilder.setAppVersion(this.mPackageInfo.versionName);
        userAgentBuilder.setBuildVersion(String.valueOf(this.mPackageInfo.versionCode));
        userAgentBuilder.setLocale(this.mLocaleProvider.get().toString());
        userAgentBuilder.setPackageName(this.mContext.getPackageName());
        userAgentBuilder.setUserAgentAppVersionMap((String) AnonymousClass0VF.A04(26, this._UL_mInjectionContext));
        return userAgentBuilder.build();
    }

    @Inject
    public UserAgentFactory(AnonymousClass0lg r3, @ForAppContext Context context, @AppNameInUserAgent String str, PackageInfo packageInfo, Provider<Locale> provider) {
        this._UL_mInjectionContext = new AnonymousClass0RE(0, r3);
        this.mContext = context;
        this.mAppNameInUserAgent = str;
        this.mPackageInfo = packageInfo;
        this.mLocaleProvider = provider;
    }
}