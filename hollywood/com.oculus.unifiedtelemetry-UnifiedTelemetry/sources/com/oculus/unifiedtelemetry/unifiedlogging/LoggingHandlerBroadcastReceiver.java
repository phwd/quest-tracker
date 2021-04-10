package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AbstractC0386gl;
import X.QC;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.OculusLoggingEvent;
import com.oculus.multiuser.UserClassifier;
import com.oculus.security.basecomponent.OculusSystemSecureBroadcastReceiver;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_EventMonitoring_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_UserMonitor_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class LoggingHandlerBroadcastReceiver extends OculusSystemSecureBroadcastReceiver {
    public static final String TAG = "LoggingHandlerBroadcastReceiver";
    public QC _UL_mInjectionContext;

    @Inject
    public LoggingHandlerBroadcastReceiver(AbstractC0247Xu xu) {
        super(new String[0]);
        this._UL_mInjectionContext = new QC(5, xu);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void A08(Context context, Intent intent, AbstractC0386gl glVar) {
        if (((UserClassifier) AbstractC0096Hu.A03(3, 22, this._UL_mInjectionContext)).A01() && "android.intent.action.ACTION_SHUTDOWN".equals(intent.getAction())) {
            String A00 = EventMonitoring.A00((EventMonitoring) AbstractC0096Hu.A03(1, 16, this._UL_mInjectionContext), true);
            if (!TextUtils.isEmpty(A00)) {
                OculusLoggingEvent A3T = ((UserMonitor) AbstractC0096Hu.A03(4, 86, this._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mEventFactory.A3T(null, EventMonitoring.UT_TELEMETRY_MONITORING, true);
                if (A3T.A3I()) {
                    A3T.A1A(A00);
                    ((LoggingHandler) AbstractC0096Hu.A03(2, 114, this._UL_mInjectionContext)).A0A(EventMonitoring.UT_TELEMETRY_MONITORING, A3T);
                }
            }
        }
    }
}
