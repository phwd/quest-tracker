package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class AppScoreboardsInfoResponse implements ValidatableApiResponse {
    @Nullable
    public Node node;

    public static class AchievementDefinitions {
        public boolean is_empty;
    }

    public static class Grouping {
        public AchievementDefinitions achievement_definitions;
        public Leaderboards leaderboards;
    }

    public static class Leaderboards {
        public boolean is_empty;
    }

    public static class Node {
        public Grouping grouping;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        Node node2 = this.node;
        if (node2 == null) {
            throw new NullPointerException("AppScoreboardsInfoResponse did not have a node");
        } else if (node2.grouping == null) {
            throw new NullPointerException("node had null for grouping");
        } else if (this.node.grouping.achievement_definitions == null) {
            throw new NullPointerException("grouping had null for achievement_definitions");
        } else if (this.node.grouping.leaderboards == null) {
            throw new NullPointerException("grouping had null for leaderboards");
        }
    }
}
