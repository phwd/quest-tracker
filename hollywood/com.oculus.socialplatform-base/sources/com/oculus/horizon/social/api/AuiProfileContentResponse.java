package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class AuiProfileContentResponse {
    public final User user;

    public static class AvatarImage {
        public final String uri;
    }

    public static class Friends {
        public final int count;
    }

    public static class MostRecentPresence {
        public final boolean is_current;
    }

    public static class ProfilePhoto {
        public final String uri;
    }

    public static class User {
        public final String alias;
        public final AvatarImage avatar_image;
        public final String biography;
        public final Friends friends;
        public final MostRecentPresence most_recent_presence;
        public final String name;
        public final String presence;
        public final ProfilePhoto profile_photo;
    }
}
