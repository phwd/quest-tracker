package com.oculus.horizon.api.social;

import java.util.ArrayList;
import javax.annotation.Nullable;

public class GroupLaunchInfo {
    public final Destination destination;
    public final String id;
    public final boolean only_quest_users;
    @Nullable
    public Room room;
    public final String state;
    @Nullable
    public final GroupLaunchUsers users;

    public static class Application {
        public final String display_name;
        public final IconImage icon_image;
        public final String id;
        public final Boolean is_viewer_entitled;
    }

    public static class Destination {
        public final Application application;
        public final String deeplink_message_for_deeplink_target;
        public final String description;
        public final String display_name;
        public final String id;
        public final String is_external_deeplinkable;
        public final int max_capacity;
    }

    public static class GroupLaunchUsers {
        public final ArrayList<GroupLaunchUsersEdge> edges;
    }

    public static class GroupLaunchUsersEdge {
        public final SocialUser node;
        @Nullable
        public final String proposal_response;
    }

    public static class IconImage {
        public final String uri;
    }

    public static class Room {
        public String id;
    }
}
