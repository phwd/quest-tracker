package com.oculus.logging.utils;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies
public class EventManagerImpl implements EventManager {
    private static final String TAG = "EventManagerImpl";
    private InjectionContext _UL_mInjectionContext;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance();

    @AutoGeneratedAccessMethod
    public static final EventManagerImpl _UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (EventManagerImpl) UL.factorymap.get(UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final EventManagerImpl _UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new EventManagerImpl(injectorLike);
    }

    @Inject
    public EventManagerImpl(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(2, injectorLike);
        this.mUnifiedTelemetryLogger.init((Context) FbInjector.lazyInstance(1, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID, this._UL_mInjectionContext));
    }

    @Override // com.oculus.logging.utils.EventManager
    public Event createEvent(String str) {
        return new EventImpl(str);
    }

    @Override // com.oculus.logging.utils.EventManager
    public Event createEvent(String str, @Nullable String str2, boolean z) {
        return new EventImpl(str, str2, z);
    }

    public class EventImpl implements Event {
        private final AnalyticsEvent mAnalyticsEvent;
        private final boolean mLowLatency;

        private EventImpl(EventManagerImpl eventManagerImpl, String str) {
            this(eventManagerImpl, (String) null, str);
        }

        private EventImpl(@Nullable EventManagerImpl eventManagerImpl, String str, String str2) {
            this(str, str2, (String) null, false);
        }

        private EventImpl(EventManagerImpl eventManagerImpl, @Nullable String str, String str2, boolean z) {
            this((String) null, str, str2, z);
        }

        private EventImpl(@Nullable String str, String str2, @Nullable String str3, boolean z) {
            this.mLowLatency = z;
            if (str != null) {
                this.mAnalyticsEvent = new AnalyticsEvent(str, str2);
            } else {
                this.mAnalyticsEvent = new AnalyticsEvent(str2);
            }
            if (str3 != null) {
                parseJson(str3);
            }
        }

        private void parseJson(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    Object obj = jSONObject.get(next);
                    if (obj == null) {
                        addExtra(next, (String) null);
                    } else if (obj instanceof String) {
                        addExtra(next, (String) obj);
                    } else if (obj instanceof Integer) {
                        addExtra(next, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        addExtra(next, ((Long) obj).longValue());
                    } else if (obj instanceof Double) {
                        addExtra(next, ((Double) obj).doubleValue());
                    } else if (obj instanceof Boolean) {
                        addExtra(next, ((Boolean) obj).booleanValue());
                    }
                }
            } catch (JSONException e) {
                BLog.e(EventManagerImpl.TAG, "Failed to parse json string content", e);
            }
        }

        @Override // com.oculus.logging.utils.Event
        public Event addExtra(String str, String str2) {
            this.mAnalyticsEvent.setExtra(str, str2);
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public Event addExtra(String str, int i) {
            this.mAnalyticsEvent.setExtra(str, Integer.valueOf(i));
            return this;
        }

        public Event addExtra(String str, long j) {
            this.mAnalyticsEvent.setExtra(str, Long.valueOf(j));
            return this;
        }

        public Event addExtra(String str, double d) {
            this.mAnalyticsEvent.setExtra(str, Double.valueOf(d));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public Event addExtra(String str, boolean z) {
            this.mAnalyticsEvent.setExtra(str, Boolean.valueOf(z));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public void setXplatTagForInternalUse() {
            this.mAnalyticsEvent.setXplatTag();
        }

        @Override // com.oculus.logging.utils.Event
        public void logAndRelease() {
            EventManagerImpl.this.mUnifiedTelemetryLogger.reportEvent(this.mAnalyticsEvent, this.mLowLatency);
        }
    }
}