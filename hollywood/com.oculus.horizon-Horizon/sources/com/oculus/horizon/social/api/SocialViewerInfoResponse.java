package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class SocialViewerInfoResponse {
    public final User user;

    public static class AvatarImage {
        public final String uri;
    }

    public static class ProfilePhoto {
        public final String uri;
    }

    public static class User {
        public final String alias;
        public final AvatarImage avatar_image;
        public final ProfilePhoto profile_photo;
        public final UserNuxFlags user_nux_flags;
    }

    public static class UserNuxFlags {
        public final boolean has_seen_vr_invite_profile_nux;
    }
}
