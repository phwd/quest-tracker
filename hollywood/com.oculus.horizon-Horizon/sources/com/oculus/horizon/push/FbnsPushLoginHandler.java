package com.oculus.horizon.push;

import X.AnonymousClass0DC;
import X.AnonymousClass0aL;
import android.content.Context;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.base.app.AppInfo;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID"})
public class FbnsPushLoginHandler implements LoginHandler {
    public static final String TAG = "FbnsPushLoginHandler";
    public final String mAppId;
    public final Context mContext;

    @Override // com.oculus.auth.handler.LoginHandler
    public final AnonymousClass0DC<Void> afterLoginAsync() {
        AnonymousClass0aL.A00(this.mContext, this.mAppId);
        return AnonymousClass0DC.A04(null);
    }

    @Inject
    public FbnsPushLoginHandler(@ForAppContext Context context, AppInfo appInfo) {
        this.mContext = context;
        this.mAppId = appInfo.appId;
    }
}
