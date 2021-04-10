package com.oculus.horizon.api.profile;

import java.util.List;

public class PrivacyAudience {
    public String base_state;
    public IncludedUsers included_users;

    public static class IncludedUsers {
        public int count;
        public List<Node> nodes;

        public static class Node {
            public final String id;
        }
    }
}
