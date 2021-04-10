package com.facebook.tigon.oktigon;

import X.AnonymousClass0N1;
import X.AnonymousClass1Re;
import X.C03250cX;
import com.facebook.http.annotations.UserAgentString;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OkTigonService extends AnonymousClass1Re {
    public static volatile OkTigonService _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_INSTANCE;

    static {
        C03250cX.A01("oktigon");
    }

    @Inject
    public OkTigonService(@OkTigonClient AnonymousClass0N1 r1, @UserAgentString String str) {
        super(r1, str);
    }
}
