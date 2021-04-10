package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class SocialFriendsResponse {
    @Nullable
    public final Viewer viewer;

    public static class Friends {
        public int count;
        @Nullable
        public final ArrayList<SocialUser> nodes;
    }

    public static class User {
        @Nullable
        public final Friends friends;
    }

    public static class Viewer {
        @Nullable
        public final User user;
    }
}
