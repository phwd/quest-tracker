package com.oculus.logging.utils;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.PersistableBundle;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
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
    public AnonymousClass0QC _UL_mInjectionContext;
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
                            A13(next, ((Number) obj).intValue());
                        } else if (obj instanceof Long) {
                            A14(next, ((Number) obj).longValue());
                        } else if (obj instanceof Double) {
                            A12(next, ((Number) obj).doubleValue());
                        } else if (obj instanceof Boolean) {
                            A16(next, ((Boolean) obj).booleanValue());
                        }
                        A15(next, str3);
                    }
                } catch (JSONException e) {
                    AnonymousClass0NO.A0B(EventManagerImpl.TAG, "Failed to parse json string content", e);
                }
            }
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A12(String str, double d) {
            this.mAnalyticsEvent.setExtra(str, Double.valueOf(d));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A13(String str, int i) {
            this.mAnalyticsEvent.setExtra(str, Integer.valueOf(i));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A14(String str, long j) {
            this.mAnalyticsEvent.setExtra(str, Long.valueOf(j));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A15(String str, String str2) {
            this.mAnalyticsEvent.setExtra(str, str2);
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A16(String str, boolean z) {
            this.mAnalyticsEvent.setExtra(str, Boolean.valueOf(z));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final Event A1G() {
            Long A01 = StorageLoggingUtils.A01(((Context) AnonymousClass0J2.A03(0, 80, ((StorageLoggingUtils) AnonymousClass0J2.A03(0, 204, EventManagerImpl.this._UL_mInjectionContext))._UL_mInjectionContext)).getFilesDir().getAbsolutePath());
            if (A01 != null) {
                this.mAnalyticsEvent.setExtra(StorageLoggingUtils.FREE_SPACE_INTERNAL, A01);
            }
            Long A012 = StorageLoggingUtils.A01(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (A012 != null) {
                this.mAnalyticsEvent.setExtra(StorageLoggingUtils.FREE_SPACE_SD, A012);
            }
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public final void A5L() {
            EventManagerImpl.this.mUnifiedTelemetryLogger.reportEvent(this.mAnalyticsEvent, this.mLowLatency);
        }

        @Override // com.oculus.logging.utils.Event
        public final void A8q(long j) {
            this.mAnalyticsEvent.setTime(j);
        }

        @Override // com.oculus.logging.utils.Event
        public final void A8v() {
            this.mAnalyticsEvent.setXplatTag();
        }
    }

    @TargetApi(21)
    public class FunnelDataImpl implements FunnelData {
        public final PersistableBundle mPersistableBundle = new PersistableBundle();

        public FunnelDataImpl() {
        }

        @Override // com.oculus.logging.utils.FunnelData
        public final FunnelData A17(String str, long j) {
            this.mPersistableBundle.putLong(str, j);
            return this;
        }

        @Override // com.oculus.logging.utils.FunnelData
        public final FunnelData A18(String str, String str2) {
            this.mPersistableBundle.putString(str, str2);
            return this;
        }
    }

    @Override // com.oculus.logging.utils.EventManager
    public final Event A22(String str) {
        return new EventImpl(str, null, false);
    }

    @Override // com.oculus.logging.utils.EventManager
    public final void A8F(String str, String str2, @Nullable String str3, @Nullable FunnelData funnelData) {
        PersistableBundle persistableBundle = null;
        UnifiedTelemetryLogger unifiedTelemetryLogger = this.mUnifiedTelemetryLogger;
        if (funnelData != null) {
            persistableBundle = ((FunnelDataImpl) funnelData).mPersistableBundle;
        }
        unifiedTelemetryLogger.reportFunnelAction(str, str2, str3, persistableBundle);
    }

    @AutoGeneratedAccessMethod
    public static final EventManagerImpl A00(AbstractC06640p5 r1) {
        return (EventManagerImpl) AnonymousClass117.A00(46, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final EventManagerImpl A01(AbstractC06640p5 r1) {
        return new EventManagerImpl(r1);
    }

    @Override // com.oculus.logging.utils.EventManager
    public final Event A23(String str, @Nullable String str2, boolean z) {
        return new EventImpl(str, str2, z);
    }

    @Override // com.oculus.logging.utils.EventManager
    public final FunnelData A24() {
        return new FunnelDataImpl();
    }

    @Override // com.oculus.logging.utils.EventManager
    public final void A2Q(String str) {
        this.mUnifiedTelemetryLogger.endFunnel(str);
    }

    @Override // com.oculus.logging.utils.EventManager
    public final void A84(String str, int i) {
        this.mUnifiedTelemetryLogger.registerFunnel(str, i);
    }

    @Override // com.oculus.logging.utils.EventManager
    public final void A9H(String str) {
        this.mUnifiedTelemetryLogger.startFunnel(str);
    }

    @Inject
    public EventManagerImpl(AbstractC06640p5 r5) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r5);
        UnifiedTelemetryLogger instance = UnifiedTelemetryLogger.getInstance();
        this.mUnifiedTelemetryLogger = instance;
        instance.init((Context) AnonymousClass0J2.A03(1, 294, this._UL_mInjectionContext));
    }
}
