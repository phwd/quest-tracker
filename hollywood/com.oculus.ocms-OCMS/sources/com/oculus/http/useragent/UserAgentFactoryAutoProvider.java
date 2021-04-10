package com.oculus.http.useragent;

import com.facebook.annotations.Generated;
import com.facebook.common.android.AndroidModule;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.locale.LocaleModule;

@Generated({"By: InjectorProcessor"})
public class UserAgentFactoryAutoProvider extends AbstractProvider<UserAgentFactory> {
    @Override // javax.inject.Provider
    public UserAgentFactory get() {
        return new UserAgentFactory(this, BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this), UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_ACCESS_METHOD(this), AndroidModule._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_ACCESS_METHOD(this), LocaleModule._UL__ULSEP_javax_inject_Provider_ULLT_java_util_Locale_ULGT__ULSEP_ACCESS_METHOD(this));
    }
}
