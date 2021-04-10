package com.oculus.horizon.api.common;

import com.oculus.horizon.api.common.user.User;
import java.util.List;

public class Party {
    public String id;
    public PartyGroupLaunch party_group_launch;
    public Room party_room;
    public PartyUsers party_users;

    public static class PartyGroupLaunch {
        public String id;
    }

    public static class PartyUsers {
        public final List<User> nodes;

        public int size() {
            return this.nodes.size();
        }
    }
}
