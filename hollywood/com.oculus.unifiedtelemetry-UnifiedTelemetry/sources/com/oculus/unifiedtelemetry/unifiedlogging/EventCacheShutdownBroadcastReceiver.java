package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AbstractC0386gl;
import X.QC;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.multiuser.UserClassifier;
import com.oculus.security.basecomponent.OculusSystemSecureBroadcastReceiver;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_UserMonitor_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class EventCacheShutdownBroadcastReceiver extends OculusSystemSecureBroadcastReceiver {
    public static final String TAG = "EventCacheShutdownBroadcastReceiver";
    public QC _UL_mInjectionContext;

    @Inject
    public EventCacheShutdownBroadcastReceiver(AbstractC0247Xu xu) {
        super(new String[0]);
        this._UL_mInjectionContext = new QC(3, xu);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void A08(Context context, Intent intent, AbstractC0386gl glVar) {
        if (((UserClassifier) AbstractC0096Hu.A03(1, 22, this._UL_mInjectionContext)).A01() && "android.intent.action.ACTION_SHUTDOWN".equals(intent.getAction())) {
            ((UserMonitor) AbstractC0096Hu.A03(2, 86, this._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mEventFactory.mEventCache.A03();
        }
    }
}
