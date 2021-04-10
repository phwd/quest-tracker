package com.oculus.http.useragent;

import X.AnonymousClass0IP;
import X.AnonymousClass0VG;
import X.C00610Hs;
import com.facebook.annotations.Generated;
import com.oculus.locale.LocaleModule;

@Generated({"By: InjectorProcessor"})
public class UserAgentFactoryAutoProvider extends AnonymousClass0VG<UserAgentFactory> {
    public UserAgentFactory get() {
        return new UserAgentFactory(this, C00610Hs.A00(this), UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_ACCESS_METHOD(this), AnonymousClass0IP.A01(this), LocaleModule._UL__ULSEP_javax_inject_Provider_ULLT_java_util_Locale_ULGT__ULSEP_ACCESS_METHOD(this));
    }
}
