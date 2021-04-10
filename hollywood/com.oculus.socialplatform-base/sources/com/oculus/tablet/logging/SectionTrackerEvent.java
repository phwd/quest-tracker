package com.oculus.tablet.logging;

public enum SectionTrackerEvent {
    APPS_TABLET("apps_tablet"),
    INTERNAL_SETTINGS_GENERAL_TABLET("internal_settings_general_tablet"),
    MESSENGER_TABLET("messenger_tablet"),
    NOTIFICATIONS_TABLET("notifications_tablet"),
    PAUSE_TABLET("pause_tablet"),
    PROFILE_TABLET("profile_tablet"),
    SETTINGS_TABLET("settings_tablet"),
    SHARING_TABLET("sharing_tablet"),
    SOCIAL_TABLET("social_tablet");
    
    public final String mTelemetrySectionId;

    public String getTelemetrySectionId() {
        return this.mTelemetrySectionId;
    }

    /* access modifiers changed from: public */
    SectionTrackerEvent(String str) {
        this.mTelemetrySectionId = str;
    }
}
