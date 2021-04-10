package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class AppsScoreboardsInfoResponse implements ValidatableApiResponse {
    @Nullable
    public final ArrayList<Node> nodes;

    public static class AchievementDefinitions {
        public final boolean is_empty;
    }

    public static class Grouping {
        @Nullable
        public final AchievementDefinitions achievement_definitions;
        @Nullable
        public final Leaderboards leaderboards;
    }

    public static class Leaderboards {
        public final boolean is_empty;
    }

    public static class Node {
        @Nullable
        public final Grouping grouping;
        @Nullable
        public final String id;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        ArrayList<Node> arrayList = this.nodes;
        if (arrayList != null) {
            Iterator<Node> it = arrayList.iterator();
            while (it.hasNext()) {
                Node next = it.next();
                if (next.id != null) {
                    Grouping grouping = next.grouping;
                    if (grouping == null) {
                        throw new NullPointerException("node had null for grouping");
                    } else if (grouping.achievement_definitions == null) {
                        throw new NullPointerException("grouping had null for achievement_definitions");
                    } else if (grouping.leaderboards == null) {
                        throw new NullPointerException("grouping had null for leaderboards");
                    }
                } else {
                    throw new NullPointerException("node had null for id");
                }
            }
            return;
        }
        throw new NullPointerException("AppScoreboardsInfoResponse did not have a node");
    }
}
