package com.oculus.logging.utils;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.PersistableBundle;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class EventManagerImpl implements EventManager {
    public static final String TAG = "EventManagerImpl";
    public AnonymousClass0R7 _UL_mInjectionContext;
    public final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public class EventImpl implements Event {
        public final AnalyticsEvent mAnalyticsEvent;
        public final boolean mLowLatency;

        public EventImpl(@Nullable String str, String str2, @Nullable boolean z) {
            String str3;
            this.mLowLatency = z;
            this.mAnalyticsEvent = new AnalyticsEvent(str);
            if (str2 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        Object obj = jSONObject.get(next);
                        if (obj == null) {
                            str3 = null;
                        } else if (obj instanceof String) {
                            str3 = (String) obj;
                        } else if (obj instanceof Integer) {
                            A0y(next, ((Integer) obj).intValue());
                        } else if (obj instanceof Long) {
                            this.mAnalyticsEvent.setExtra(next, Long.valueOf(((Long) obj).longValue()));
                        } else if (obj instanceof Double) {
                            this.mAnalyticsEvent.setExtra(next, Double.valueOf(((Double) obj).doubleValue()));
                        } else if (obj instanceof Boolean) {
                            A10(next, ((Boolean) obj).booleanValue());
                        }
                        A0z(next, str3);
                    }
                } catch (JSONException e) {
                    AnonymousClass0NK.A04(EventManagerImpl.TAG, "Failed to parse json string content", e);
                }
            }
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A0y(String str, int i) {
            this.mAnalyticsEvent.setExtra(str, Integer.valueOf(i));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A0z(String str, String str2) {
            this.mAnalyticsEvent.setExtra(str, str2);
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A10(String str, boolean z) {
            this.mAnalyticsEvent.setExtra(str, Boolean.valueOf(z));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final void A5i() {
            EventManagerImpl.this.mUnifiedTelemetryLogger.reportEvent(this.mAnalyticsEvent, this.mLowLatency);
        }

        @Override // com.oculus.logging.utils.Event
        public final void A8I() {
            this.mAnalyticsEvent.setXplatTag();
        }
    }

    @TargetApi(21)
    public class FunnelDataImpl implements FunnelData {
        public final PersistableBundle mPersistableBundle;
        public final /* synthetic */ EventManagerImpl this$0;
    }

    @Override // com.oculus.logging.utils.EventManager
    public final Event A1z(String str) {
        return new EventImpl(str, null, false);
    }

    @Override // com.oculus.logging.utils.EventManager
    public final Event A20(String str, @Nullable String str2, boolean z) {
        return new EventImpl(str, str2, z);
    }

    @Inject
    public EventManagerImpl(AbstractC02990bJ r5) {
        this._UL_mInjectionContext = new AnonymousClass0R7(2, r5);
        UnifiedTelemetryLogger instance = UnifiedTelemetryLogger.getInstance();
        this.mUnifiedTelemetryLogger = instance;
        instance.init((Context) AnonymousClass0Lh.A03(1, 4, this._UL_mInjectionContext));
    }
}
