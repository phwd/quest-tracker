package com.oculus.http.useragent;

import com.facebook.common.android.AndroidModule;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.locale.LocaleModule;

public class UserAgentFactoryAutoProvider extends AbstractProvider<UserAgentFactory> {
    @Override // javax.inject.Provider
    public UserAgentFactory get() {
        return new UserAgentFactory(this, BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(this), UserAgentModule.$ul_$xXXjava_lang_String$xXXcom_oculus_http_useragent_AppNameInUserAgent$xXXACCESS_METHOD(this), AndroidModule.$ul_$xXXandroid_content_pm_PackageInfo$xXXACCESS_METHOD(this), LocaleModule.$ul_$xXXjavax_inject_Provider$x3Cjava_util_Locale$x3E$xXXACCESS_METHOD(this));
    }
}
