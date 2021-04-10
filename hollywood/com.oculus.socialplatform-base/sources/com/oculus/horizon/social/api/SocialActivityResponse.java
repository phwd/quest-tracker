package com.oculus.horizon.social.api;

import com.oculus.horizon.api.common.user.ProfilePhoto;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class SocialActivityResponse {
    public final SocialActivity social_activity;

    public static class SocialActivity {
        public final Application application;
        public final String deeplink;
        public final ProfilePhoto image;
        public final String subtitle;
        public final String title;

        public static class Application {
            public final String package_name;
        }
    }
}
