package com.oculus.horizon.social.api;

import com.oculus.http.core.base.ValidatableApiResponse;

public class HasSeenActivityPrivacyUpdateRoadblockResponse implements ValidatableApiResponse {
    public final Viewer viewer;

    public static class SocialSettingsInfo {
        public boolean has_seen_activity_roadblock;
        public boolean has_seen_nux_on_android;
    }

    public static class Viewer {
        public final SocialSettingsInfo social_settings_info;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        Viewer viewer2 = this.viewer;
        if (viewer2 == null) {
            str = "Received an invalid HasSeenActivityPrivacyUpdateRoadblockResponse. Response didn't have a viewer";
        } else if (viewer2.social_settings_info == null) {
            str = "Received an invalid HasSeenActivityPrivacyUpdateRoadblockResponse. Viewer didn't have a social_settings_info";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
