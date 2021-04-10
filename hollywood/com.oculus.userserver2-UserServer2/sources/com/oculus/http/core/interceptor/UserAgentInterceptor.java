package com.oculus.http.core.interceptor;

import X.EU;
import X.IX;
import X.SZ;
import X.XK;
import X.XO;
import X.XS;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.http.useragent.UserAgentString;
import java.io.IOException;

@Dependencies({"_UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID"})
public class UserAgentInterceptor implements XS {
    @Inject
    @Eager
    @UserAgentString
    public final String mUserAgentString;

    @Override // X.XS
    public final XK A29(EU eu) throws IOException {
        XO xo = new XO(eu.A01);
        xo.A02(HttpRequestMultipart.USER_AGENT, this.mUserAgentString);
        return eu.A00(xo.A00());
    }

    @Inject
    public UserAgentInterceptor(SZ sz) {
        this.mUserAgentString = (String) IX.A00(77, sz);
    }
}
