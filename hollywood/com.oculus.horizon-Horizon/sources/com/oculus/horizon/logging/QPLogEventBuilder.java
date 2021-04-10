package com.oculus.horizon.logging;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class QPLogEventBuilder {
    public static final String EVENT_ACTION = "click";
    public static final String EVENT_MODULE = "quick_promotion";
    public static final String EVENT_VIEW = "view";
    public static final String KEY_INSTANCE_LOG_DATA = "instance_log_data";
    public static final String KEY_OBJECT_ID = "object_id";
    public static final String KEY_PROMOTION_ID = "promotion_id";
    public static final String KEY_TRIGGER = "trigger";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public QPLogEventBuilder(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
