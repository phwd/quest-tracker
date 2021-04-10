package com.oculus.panelapp.anytimeui.v2.tablet.settings.util;

import android.content.Context;
import android.text.TextUtils;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;

public class SettingsLogger {
    private String mSectionName = "";
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public static final class VoiceActivityEvent {
        private static final String EVENT_NAME = "oculus_assistant_privacy_voice_activity_event";
        private static final String EXTRA_LATENCY = "latency_ms";
        private static final String EXTRA_SUB_EVENT = "sub_event";

        public static final class SubEvent {
            public static final String AUDIO_END = "audio_end";
            public static final String AUDIO_PLAYED = "audio_played";
            public static final String DELETE_ALL_ATTEMPTED = "delete_all_attempted";
            public static final String DELETE_ALL_CANCELLED = "delete_all_cancelled";
            public static final String DELETE_ALL_CONFIRMED = "delete_all_confirmed";
            public static final String DELETE_ALL_REQUEST_ERROR = "delete_all_request_error";
            public static final String DELETE_ALL_REQUEST_SUCCESS = "delete_all_request_success";
            public static final String DELETE_HISTORY_REQUEST = "delete_history_request";
            public static final String DELETE_HISTORY_REQUEST_ERROR = "delete_history_request_error";
            public static final String DELETE_HISTORY_REQUEST_SUCCESS = "delete_history_request_success";
            public static final String EMPTY_VIEW = "empty_view";
            public static final String LOAD_MORE = "loadMore";
            public static final String SETTINGS_ASSISTANT_SECTION_ENTRY = "settings_assistant_section_entry";
            public static final String SETTINGS_ASSISTANT_SECTION_EXIT = "settings_assistant_section_exit";
            public static final String SETTINGS_VOICE_ACTIVITY_SECTION_ENTRY = "settings_voice_activity_section_entry";
            public static final String SETTINGS_VOICE_ACTIVITY_SECTION_EXIT = "settings_voice_activity_section_exit";
            public static final String TOGGLE_VOICE_STORAGE_DISABLE = "toggle_voice_storage_disable";
            public static final String TOGGLE_VOICE_STORAGE_ENABLE = "toggle_voice_storage_enable";
        }
    }

    public SettingsLogger(Context context) {
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
        this.mUnifiedTelemetryLogger.init(context.getApplicationContext());
    }

    public void setSection(String str) {
        this.mSectionName = str;
    }

    public void logButtonClick(String str) {
        if (!TextUtils.isEmpty(str)) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_settings_change_setting");
            analyticsEvent.setExtra("settingName", str);
            analyticsEvent.setExtra("section_name", this.mSectionName);
            analyticsEvent.setExtra("setting_action", AssistantComponentContract.Components.ButtonComponent.TYPE);
            reportEvent(analyticsEvent);
        }
    }

    public void logToggleChange(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_settings_change_setting");
            analyticsEvent.setExtra("settingName", str);
            analyticsEvent.setExtra("section_name", this.mSectionName);
            analyticsEvent.setExtra("setting_action", AssistantComponentContract.Components.ToggleComponent.TYPE);
            analyticsEvent.setExtra("newValue", String.valueOf(z));
            reportEvent(analyticsEvent);
        }
    }

    public void logDropdownChange(String str, Object obj, Object obj2) {
        if (!TextUtils.isEmpty(str)) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_settings_change_setting");
            analyticsEvent.setExtra("settingName", str);
            analyticsEvent.setExtra("section_name", this.mSectionName);
            analyticsEvent.setExtra("setting_action", "dropdown");
            analyticsEvent.setExtra("oldValue", obj.toString());
            analyticsEvent.setExtra("newValue", obj2.toString());
            reportEvent(analyticsEvent);
        }
    }

    public void logNavigation(String str) {
        if (!TextUtils.isEmpty(str)) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_settings_change_setting");
            analyticsEvent.setExtra("settingName", str);
            analyticsEvent.setExtra("section_name", this.mSectionName);
            analyticsEvent.setExtra("setting_action", "navigate");
            reportEvent(analyticsEvent);
        }
    }

    public void logSliderStartDrag(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_settings_change_setting");
            analyticsEvent.setExtra("settingName", str);
            analyticsEvent.setExtra("section_name", this.mSectionName);
            analyticsEvent.setExtra("setting_action", "slider_start");
            analyticsEvent.setExtra("oldValue", Integer.valueOf(i));
            reportEvent(analyticsEvent);
        }
    }

    public void logSliderStopDrag(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_settings_change_setting");
            analyticsEvent.setExtra("settingName", str);
            analyticsEvent.setExtra("section_name", this.mSectionName);
            analyticsEvent.setExtra("setting_action", "slider_stop");
            analyticsEvent.setExtra("newValue", Integer.valueOf(i));
            reportEvent(analyticsEvent);
        }
    }

    public void logVoiceActivityEvent(String str) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_assistant_privacy_voice_activity_event");
        analyticsEvent.setExtra("sub_event", str);
        reportEvent(analyticsEvent);
    }

    public void logVoiceActivityEventWithLatency(String str, long j) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_assistant_privacy_voice_activity_event");
        analyticsEvent.setExtra("sub_event", str);
        analyticsEvent.setExtra("latency_ms", Long.valueOf(j));
        reportEvent(analyticsEvent);
    }

    private void reportEvent(AnalyticsEvent analyticsEvent) {
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    private static final class SettingsActionEvent {
        private static final String EVENT_NAME = "oculus_mobile_settings_change_setting";
        private static final String EXTRA_ACTION_TYPE = "setting_action";
        static final String EXTRA_NEW_VALUE = "newValue";
        static final String EXTRA_OLD_VALUE = "oldValue";
        private static final String EXTRA_SECTION_NAME = "section_name";
        private static final String EXTRA_SETTING_NAME = "settingName";

        private SettingsActionEvent() {
        }

        private static final class Type {
            private static final String BUTTON = "button";
            private static final String DROPDOWN = "dropdown";
            private static final String NAVIGATE = "navigate";
            private static final String SLIDER_START = "slider_start";
            private static final String SLIDER_STOP = "slider_stop";
            private static final String TOGGLE = "toggle";

            private Type() {
            }
        }
    }
}
