package com.oculus.horizon.social.api;

import com.oculus.http.core.base.ValidatableApiResponse;

public class HasSeenNuxResponse implements ValidatableApiResponse {
    public final Viewer viewer;

    public static class SocialSettingsInfo {
        public Boolean has_seen_nux_on_android;
    }

    public static class Viewer {
        public final SocialSettingsInfo social_settings_info;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        Viewer viewer2 = this.viewer;
        if (viewer2 == null) {
            throw new NullPointerException("Received an invalid HasSeenNuxResponse. Response didn't have a viewer");
        } else if (viewer2.social_settings_info == null) {
            throw new NullPointerException("Received an invalid HasSeenNuxResponse. Viewer didn't have a social_settings_info");
        }
    }
}
