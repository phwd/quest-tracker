package com.oculus.logging.utils;

import X.BZ;
import X.Om;
import X.SZ;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.PersistableBundle;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class EventManagerImpl implements EventManager {
    public static final String TAG = "EventManagerImpl";
    public Om _UL_mInjectionContext;
    public final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public class EventImpl implements Event {
        public final AnalyticsEvent mAnalyticsEvent;
        public final boolean mLowLatency = false;

        public EventImpl(@Nullable String str) {
            this.mAnalyticsEvent = new AnalyticsEvent(str);
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A0l(String str, int i) {
            this.mAnalyticsEvent.setExtra(str, Integer.valueOf(i));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A0m(String str, String str2) {
            this.mAnalyticsEvent.setExtra(str, str2);
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final void A2K() {
            EventManagerImpl.this.mUnifiedTelemetryLogger.reportEvent(this.mAnalyticsEvent, this.mLowLatency);
        }
    }

    @TargetApi(21)
    public class FunnelDataImpl implements FunnelData {
        public final PersistableBundle mPersistableBundle;
        public final /* synthetic */ EventManagerImpl this$0;
    }

    @Override // com.oculus.logging.utils.EventManager
    public final Event A1G(String str) {
        return new EventImpl(str);
    }

    @Inject
    public EventManagerImpl(SZ sz) {
        this._UL_mInjectionContext = new Om(2, sz);
        UnifiedTelemetryLogger instance = UnifiedTelemetryLogger.getInstance();
        this.mUnifiedTelemetryLogger = instance;
        instance.init((Context) BZ.A02(1, 1, this._UL_mInjectionContext));
    }
}
