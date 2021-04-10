package com.oculus.mediaupload.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class SharingAutoSyncPreferenceResponse {
    public final Me me;

    public static class DevicePreferences {
        public final String sharing_auto_sync_preference;
    }

    public static class Me {
        public final DevicePreferences device_preferences;
    }
}
