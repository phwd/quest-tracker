package com.oculus.http.useragent;

import X.AbstractC0247Xu;
import X.C00208d;
import X.C00238n;
import X.C0088Gy;
import X.C0515sp;
import X.QC;
import X.eJ;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import java.util.Locale;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID"})
public class UserAgentFactory {
    public QC _UL_mInjectionContext;
    public final String mAppNameInUserAgent;
    public final Context mContext;
    public final eJ<Locale> mLocaleProvider;
    public final PackageInfo mPackageInfo;

    @AutoGeneratedAccessMethod
    public static final UserAgentFactory A00(AbstractC0247Xu xu) {
        return (UserAgentFactory) C0515sp.A00(6, xu);
    }

    @AutoGeneratedFactoryMethod
    public static final UserAgentFactory A01(AbstractC0247Xu xu) {
        return new UserAgentFactory(xu, C00208d.A00(xu), UserAgentModule.A01(xu), C00238n.A00(xu), new C0088Gy(56, xu));
    }

    @Inject
    public UserAgentFactory(AbstractC0247Xu xu, @ForAppContext Context context, @AppNameInUserAgent String str, PackageInfo packageInfo, eJ<Locale> eJVar) {
        this._UL_mInjectionContext = new QC(1, xu);
        this.mContext = context;
        this.mAppNameInUserAgent = str;
        this.mPackageInfo = packageInfo;
        this.mLocaleProvider = eJVar;
    }
}
