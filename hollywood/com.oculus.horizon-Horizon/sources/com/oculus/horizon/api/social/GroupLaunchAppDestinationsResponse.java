package com.oculus.horizon.api.social;

import com.oculus.horizon.api.common.PageInfo;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.List;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GroupLaunchAppDestinationsResponse implements ValidatableApiResponse {
    @Nullable
    public final Node node;

    public static class Destination {
        public final String deeplink_message_for_deeplink_target;
        public final String display_name;
        public final String id;
        public final String image;
        public final String is_external_deeplinkable;
        public final int max_group_launch_capacity;
    }

    public static class Destinations {
        public final List<Edge> edges;
        public final PageInfo page_info;
    }

    public static class Edge {
        public final String cursor;
        public final Destination node;
    }

    public static class Grouping {
        public final Destinations destinations;
    }

    public static class Node {
        public final Grouping grouping;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        Node node2 = this.node;
        if (node2 != null) {
            Grouping grouping = node2.grouping;
            if (grouping != null) {
                Destinations destinations = grouping.destinations;
                if (destinations == null) {
                    str = "grouping had null for destinations";
                } else if (destinations.page_info != null) {
                    List<Edge> list = destinations.edges;
                    if (list != null) {
                        for (Edge edge : list) {
                            if (edge.cursor != null) {
                                Destination destination = edge.node;
                                if (destination == null) {
                                    str = "edge had a null node";
                                } else if (destination.id == null) {
                                    str = "destination had a null id";
                                }
                            } else {
                                str = "edge had a null cursor";
                            }
                        }
                        return;
                    }
                    str = "destinations had null for edges";
                } else {
                    str = "destinations had null for page_info";
                }
            } else {
                str = "node had null for grouping";
            }
        } else {
            str = "GroupLaunchAppDestinationsResponse did not have a node";
        }
        throw new NullPointerException(str);
    }
}
