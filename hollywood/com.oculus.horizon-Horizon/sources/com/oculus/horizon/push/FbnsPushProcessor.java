package com.oculus.horizon.push;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_java_util_Set_ULLT_com_oculus_horizon_push_FbnsPushHandler_ULGT__ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class FbnsPushProcessor {
    public static final String MSG_DATA_FIELD = "data";
    public static final String MSG_NOTIFICATION_UUID = "PushNotifID";
    public static final String MSG_PARAMS_FIELD = "params";
    public static final String MSG_TYPE_FIELD = "type";
    public static final String TAG = "FbnsPushProcessor";
    public AnonymousClass0QC _UL_mInjectionContext;
    public final Set<String> mPushIdDuplicateManager = new HashSet();

    @Inject
    public FbnsPushProcessor(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
