package com.oculus.horizon.api.social;

import java.util.ArrayList;
import javax.annotation.Nullable;

public class GroupLaunchInfo {
    public Destination destination;
    public String id;
    public boolean only_quest_users;
    @Nullable
    public Room room;
    public String state;
    @Nullable
    public GroupLaunchUsers users;

    public static class Application {
        public String display_name;
        public IconImage icon_image;
        public String id;
        public Boolean is_viewer_entitled;
    }

    public static class Destination {
        public Application application;
        public String deeplink_message_for_deeplink_target;
        public String description;
        public String display_name;
        public String id;
        public String is_external_deeplinkable;
        public int max_capacity;
    }

    public static class GroupLaunchUsers {
        public ArrayList<GroupLaunchUsersEdge> edges;
    }

    public static class GroupLaunchUsersEdge {
        public SocialUser node;
        @Nullable
        public String proposal_response;
    }

    public static class IconImage {
        public String uri;
    }

    public static class Room {
        public String id;
    }
}
