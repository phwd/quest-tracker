package com.oculus.horizon.fbconnect;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import X.AnonymousClass0b9;
import android.content.Context;
import android.content.Intent;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;

public class FBConnectReceiver extends OculusPublicBroadcastReceiver implements AnonymousClass0QB {
    public static final String ACTION_UPDATE_CURRENT_FACEBOOK_ACCOUNT = "com.oculus.fbconnect.UPDATE_CURRENT_FACEBOOK_ACCOUNT";
    public static final String TAG = "FBConnectReceiver";
    public AnonymousClass0QC _UL_mInjectionContext;

    public FBConnectReceiver() {
        super("com.oculus.fbconnect.UPDATE_CURRENT_FACEBOOK_ACCOUNT");
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, FBConnectReceiver fBConnectReceiver) {
        fBConnectReceiver._UL_mInjectionContext = new AnonymousClass0QC(1, r2);
    }

    public static final void _UL_injectMe(Context context, FBConnectReceiver fBConnectReceiver) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), fBConnectReceiver);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void onReceive(Context context, Intent intent, AnonymousClass0b9 r6) {
        _UL_injectMe(context, this);
        if (intent.getAction().equals("com.oculus.fbconnect.UPDATE_CURRENT_FACEBOOK_ACCOUNT")) {
            ((FBConnectHelper) AnonymousClass0J2.A03(0, 486, this._UL_mInjectionContext)).updateFBConnectAccountAsync(TAG);
        }
    }
}
