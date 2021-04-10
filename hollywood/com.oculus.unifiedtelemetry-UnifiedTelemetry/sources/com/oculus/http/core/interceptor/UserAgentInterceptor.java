package com.oculus.http.core.interceptor;

import X.AbstractC0247Xu;
import X.C0359dg;
import X.C0363dk;
import X.Cdo;
import X.L3;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.http.useragent.UserAgentString;
import java.io.IOException;

@Dependencies({"_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID"})
public class UserAgentInterceptor implements Cdo {
    @Inject
    @Eager
    @UserAgentString
    public final String mUserAgentString;

    @Override // X.Cdo
    public final C0359dg A38(L3 l3) throws IOException {
        C0363dk dkVar = new C0363dk(l3.A01);
        dkVar.A02("User-Agent", this.mUserAgentString);
        return l3.A00(dkVar.A00());
    }

    @Inject
    public UserAgentInterceptor(AbstractC0247Xu xu) {
        this.mUserAgentString = UserAgentModule.A02(xu);
    }
}
