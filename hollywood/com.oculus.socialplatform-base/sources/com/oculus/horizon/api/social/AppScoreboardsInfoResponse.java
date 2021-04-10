package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class AppScoreboardsInfoResponse implements ValidatableApiResponse {
    @Nullable
    public final Node node;

    public static class AchievementDefinitions {
        public final boolean is_empty;
    }

    public static class Grouping {
        public final AchievementDefinitions achievement_definitions;
        public final Leaderboards leaderboards;
    }

    public static class Leaderboards {
        public final boolean is_empty;
    }

    public static class Node {
        public final Grouping grouping;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        Node node2 = this.node;
        if (node2 != null) {
            Grouping grouping = node2.grouping;
            if (grouping == null) {
                throw new NullPointerException("node had null for grouping");
            } else if (grouping.achievement_definitions == null) {
                throw new NullPointerException("grouping had null for achievement_definitions");
            } else if (grouping.leaderboards == null) {
                throw new NullPointerException("grouping had null for leaderboards");
            }
        } else {
            throw new NullPointerException("AppScoreboardsInfoResponse did not have a node");
        }
    }
}
