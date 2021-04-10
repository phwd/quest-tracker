package com.oculus.horizon.api.profile;

import java.util.List;

public class PrivacyEdges {
    public List<PrivacyEdge> edges;
    public String identity_in_search_state;

    public static class PrivacyEdge {
        public boolean is_currently_selected;
        public boolean is_default_option;
        public PrivacyAudience node;
    }
}
