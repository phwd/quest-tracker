package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class SocialFriendsResponse {
    @Nullable
    public Viewer viewer;

    public static class Friends {
        public int count;
        @Nullable
        public ArrayList<SocialUser> nodes;
    }

    public static class User {
        @Nullable
        public Friends friends;
    }

    public static class Viewer {
        @Nullable
        public User user;
    }
}
