package com.oculus.logging.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.PersistableBundle;
import com.facebook.debug.log.BLog;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.os.Version;
import java.util.Iterator;
import javax.annotation.Nullable;
import javax.inject.Provider;
import org.json.JSONException;
import org.json.JSONObject;

public class EventManagerImpl implements EventManager {
    private static final String TAG = EventManagerImpl.class.getSimpleName();
    private InjectionContext $ul_mInjectionContext;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance();

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_logging_utils_EventManagerImpl$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UtilsModule.UL_id.$ul_$xXXcom_oculus_logging_utils_EventManagerImpl$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_logging_utils_EventManagerImpl$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UtilsModule.UL_id.$ul_$xXXcom_oculus_logging_utils_EventManagerImpl$xXXBINDING_ID, $ul_injector);
    }

    public static final EventManagerImpl $ul_$xXXcom_oculus_logging_utils_EventManagerImpl$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (EventManagerImpl) UL.factorymap.get(UtilsModule.UL_id.$ul_$xXXcom_oculus_logging_utils_EventManagerImpl$xXXBINDING_ID, $ul_injector);
    }

    public static final EventManagerImpl $ul_$xXXcom_oculus_logging_utils_EventManagerImpl$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return new EventManagerImpl($ul_injector);
    }

    public EventManagerImpl(InjectorLike $ul_injector) {
        this.$ul_mInjectionContext = new InjectionContext(2, $ul_injector);
        this.mUnifiedTelemetryLogger.init((Context) FbInjector.lazyInstance(1, BundledAndroidModule.UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXBINDING_ID, this.$ul_mInjectionContext));
    }

    @Override // com.oculus.logging.utils.EventManager
    public Event createEvent(String eventName) {
        return new EventImpl(eventName);
    }

    @Override // com.oculus.logging.utils.EventManager
    public Event createEvent(String moduleName, String eventName) {
        return new EventImpl(moduleName, eventName);
    }

    @Override // com.oculus.logging.utils.EventManager
    public Event createEvent(String eventName, @Nullable String jsonContent, boolean deliverImmediately) {
        return new EventImpl(eventName, jsonContent, deliverImmediately);
    }

    public class EventImpl implements Event {
        private final AnalyticsEvent mAnalyticsEvent;
        private final boolean mLowLatency;

        private EventImpl(EventManagerImpl this$02, String eventName) {
            this(this$02, (String) null, eventName);
        }

        private EventImpl(EventManagerImpl this$02, @Nullable String moduleName, String eventName) {
            this(moduleName, eventName, (String) null, false);
        }

        private EventImpl(EventManagerImpl this$02, String eventName, @Nullable String jsonContent, boolean deliverImmediately) {
            this((String) null, eventName, jsonContent, deliverImmediately);
        }

        private EventImpl(String moduleName, @Nullable String eventName, String jsonContent, @Nullable boolean deliverImmediately) {
            this.mLowLatency = deliverImmediately;
            if (moduleName != null) {
                this.mAnalyticsEvent = new AnalyticsEvent(moduleName, eventName);
            } else {
                this.mAnalyticsEvent = new AnalyticsEvent(eventName);
            }
            if (jsonContent != null) {
                parseJson(jsonContent);
            }
        }

        private void parseJson(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                Iterator<String> contentKeys = jsonObject.keys();
                while (contentKeys.hasNext()) {
                    String contentKey = contentKeys.next();
                    Object contentValue = jsonObject.get(contentKey);
                    if (contentValue == null) {
                        addExtra(contentKey, (String) null);
                    } else if (contentValue instanceof String) {
                        addExtra(contentKey, (String) contentValue);
                    } else if (contentValue instanceof Integer) {
                        addExtra(contentKey, ((Integer) contentValue).intValue());
                    } else if (contentValue instanceof Long) {
                        addExtra(contentKey, ((Long) contentValue).longValue());
                    } else if (contentValue instanceof Double) {
                        addExtra(contentKey, ((Double) contentValue).doubleValue());
                    } else if (contentValue instanceof Boolean) {
                        addExtra(contentKey, ((Boolean) contentValue).booleanValue());
                    }
                }
            } catch (JSONException e) {
                BLog.e(EventManagerImpl.TAG, "Failed to parse json string content", e);
            }
        }

        @Override // com.oculus.logging.utils.Event
        public Event appendStorageInformation() {
            Long internalFreeSpace = ((StorageLoggingUtils) FbInjector.lazyInstance(0, UtilsModule.UL_id.$ul_$xXXcom_oculus_logging_utils_StorageLoggingUtils$xXXBINDING_ID, EventManagerImpl.this.$ul_mInjectionContext)).getInternalFreeSpace();
            if (internalFreeSpace != null) {
                this.mAnalyticsEvent.setExtra(StorageLoggingUtils.FREE_SPACE_INTERNAL, internalFreeSpace);
            }
            Long sdFreeSpace = ((StorageLoggingUtils) FbInjector.lazyInstance(0, UtilsModule.UL_id.$ul_$xXXcom_oculus_logging_utils_StorageLoggingUtils$xXXBINDING_ID, EventManagerImpl.this.$ul_mInjectionContext)).getSDFreeSpace();
            if (sdFreeSpace != null) {
                this.mAnalyticsEvent.setExtra(StorageLoggingUtils.FREE_SPACE_SD, sdFreeSpace);
            }
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public Event addExtra(String key, String value) {
            this.mAnalyticsEvent.setExtra(key, value);
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public Event addExtra(String key, int value) {
            this.mAnalyticsEvent.setExtra(key, Integer.valueOf(value));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public Event addExtra(String key, long value) {
            this.mAnalyticsEvent.setExtra(key, Long.valueOf(value));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public Event addExtra(String key, double value) {
            this.mAnalyticsEvent.setExtra(key, Double.valueOf(value));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public Event addExtra(String key, boolean value) {
            this.mAnalyticsEvent.setExtra(key, Boolean.valueOf(value));
            return this;
        }

        @Override // com.oculus.logging.utils.Event
        public void setTime(long time) {
            this.mAnalyticsEvent.setTime(time);
        }

        @Override // com.oculus.logging.utils.Event
        public void setUslTagForInternalUse() {
            this.mAnalyticsEvent.setUslTag();
        }

        @Override // com.oculus.logging.utils.Event
        public void setReactNativeTagForInternalUse() {
            this.mAnalyticsEvent.setReactNativeTag();
        }

        @Override // com.oculus.logging.utils.Event
        public void setNtTagForInternalUse() {
            this.mAnalyticsEvent.setNtTag();
        }

        @Override // com.oculus.logging.utils.Event
        public void setXplatTagForInternalUse() {
            this.mAnalyticsEvent.setXplatTag();
        }

        @Override // com.oculus.logging.utils.Event
        public void setHasDownloadedSamplingConfigTag() {
            this.mAnalyticsEvent.setHasDownloadedSamplingConfigTag();
        }

        @Override // com.oculus.logging.utils.Event
        public void setEventInDownloadedSamplingTag() {
            this.mAnalyticsEvent.setIsEventInSamplingConfigTag();
        }

        @Override // com.oculus.logging.utils.Event
        public void setEventInSessionlessSamplingConfigTag() {
            this.mAnalyticsEvent.setIsEventInSamplingConfigTag();
        }

        @Override // com.oculus.logging.utils.Event
        public void logAndRelease() {
            EventManagerImpl.this.mUnifiedTelemetryLogger.reportEvent(this.mAnalyticsEvent, this.mLowLatency);
        }
    }

    @Override // com.oculus.logging.utils.EventManager
    public void registerFunnel(String funnelName, int secondsToEndSinceLastUpdate) {
        this.mUnifiedTelemetryLogger.registerFunnel(funnelName, secondsToEndSinceLastUpdate);
    }

    @Override // com.oculus.logging.utils.EventManager
    public void startFunnel(String funnelName) {
        startFunnel(funnelName, null);
    }

    @Override // com.oculus.logging.utils.EventManager
    public void startFunnel(String funnelName, @Nullable Long instanceId) {
        if (instanceId != null) {
            this.mUnifiedTelemetryLogger.startFunnel(funnelName, instanceId.longValue());
        } else {
            this.mUnifiedTelemetryLogger.startFunnel(funnelName);
        }
    }

    @Override // com.oculus.logging.utils.EventManager
    public void endFunnel(String funnelName) {
        endFunnel(funnelName, null);
    }

    @Override // com.oculus.logging.utils.EventManager
    public void endFunnel(String funnelName, @Nullable Long instanceId) {
        if (instanceId != null) {
            this.mUnifiedTelemetryLogger.endFunnel(funnelName, instanceId.longValue());
        } else {
            this.mUnifiedTelemetryLogger.endFunnel(funnelName);
        }
    }

    @Override // com.oculus.logging.utils.EventManager
    public void reportFunnelAction(String funnelName, String action, @Nullable String tag, @Nullable FunnelData funnelData) {
        reportFunnelAction(funnelName, action, null, tag, funnelData);
    }

    @Override // com.oculus.logging.utils.EventManager
    public void reportFunnelAction(String funnelName, String action, @Nullable Long instanceId, @Nullable String tag, @Nullable FunnelData funnelData) {
        PersistableBundle persistableBundle = null;
        if (instanceId != null) {
            UnifiedTelemetryLogger unifiedTelemetryLogger = this.mUnifiedTelemetryLogger;
            long longValue = instanceId.longValue();
            if (funnelData != null) {
                persistableBundle = ((FunnelDataImpl) funnelData).mPersistableBundle;
            }
            unifiedTelemetryLogger.reportFunnelAction(funnelName, longValue, action, tag, persistableBundle);
            return;
        }
        UnifiedTelemetryLogger unifiedTelemetryLogger2 = this.mUnifiedTelemetryLogger;
        if (funnelData != null) {
            persistableBundle = ((FunnelDataImpl) funnelData).mPersistableBundle;
        }
        unifiedTelemetryLogger2.reportFunnelAction(funnelName, action, tag, persistableBundle);
    }

    @Override // com.oculus.logging.utils.EventManager
    public FunnelData createFunnelData() {
        return new FunnelDataImpl();
    }

    @TargetApi(Version.VERSION_21)
    public class FunnelDataImpl implements FunnelData {
        private final PersistableBundle mPersistableBundle;

        private FunnelDataImpl() {
            this.mPersistableBundle = new PersistableBundle();
        }

        @Override // com.oculus.logging.utils.FunnelData
        public FunnelData addExtra(String key, String value) {
            this.mPersistableBundle.putString(key, value);
            return this;
        }

        @Override // com.oculus.logging.utils.FunnelData
        public FunnelData addExtra(String key, int value) {
            this.mPersistableBundle.putInt(key, value);
            return this;
        }

        @Override // com.oculus.logging.utils.FunnelData
        public FunnelData addExtra(String key, long value) {
            this.mPersistableBundle.putLong(key, value);
            return this;
        }
    }
}
