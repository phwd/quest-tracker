package com.oculus.unifiedtelemetry.unifiedlogging.xappsupport;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.QC;
import android.os.Binder;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.OculusLoggingEvent;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.StorageLoggingUtils;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;
import com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_UserMonitor_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_BINDING_ID"})
public class EventManagerImpl implements EventManager {
    public QC _UL_mInjectionContext;

    @Override // com.oculus.logging.utils.EventManager
    public final Event A1h(String str) {
        return new EventImpl(str, (LoggingHandler) AbstractC0096Hu.A03(1, 114, this._UL_mInjectionContext), ((UserMonitor) AbstractC0096Hu.A03(0, 86, this._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mEventFactory.A3S(str), (StorageLoggingUtils) AbstractC0096Hu.A03(2, 65, this._UL_mInjectionContext));
    }

    @Override // com.oculus.logging.utils.EventManager
    public final Event A1i(String str, String str2, boolean z) {
        OculusLoggingEvent A3T = ((UserMonitor) AbstractC0096Hu.A03(0, 86, this._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mEventFactory.A3T(null, str, z);
        if (A3T.A3I()) {
            A3T.A1A(str2);
        } else {
            A3T = null;
        }
        QC qc = this._UL_mInjectionContext;
        return new EventImpl(str, (LoggingHandler) AbstractC0096Hu.A03(1, 114, qc), A3T, (StorageLoggingUtils) AbstractC0096Hu.A03(2, 65, qc));
    }

    @Inject
    public EventManagerImpl(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(3, xu);
    }
}
