package com.oculus.http.useragent;

import X.AbstractC0192Xx;
import X.Om;
import X.SZ;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import java.util.Locale;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID"})
public class UserAgentFactory {
    public Om _UL_mInjectionContext;
    public final String mAppNameInUserAgent;
    public final Context mContext;
    public final AbstractC0192Xx<Locale> mLocaleProvider;
    public final PackageInfo mPackageInfo;

    @Inject
    public UserAgentFactory(SZ sz, @ForAppContext Context context, @AppNameInUserAgent String str, PackageInfo packageInfo, AbstractC0192Xx<Locale> xx) {
        this._UL_mInjectionContext = new Om(1, sz);
        this.mContext = context;
        this.mAppNameInUserAgent = str;
        this.mPackageInfo = packageInfo;
        this.mLocaleProvider = xx;
    }
}
