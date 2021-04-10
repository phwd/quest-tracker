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
        Node node2 = this.node;
        if (node2 != null) {
            Grouping grouping = node2.grouping;
            if (grouping != null) {
                Destinations destinations = grouping.destinations;
                if (destinations == null) {
                    throw new NullPointerException("grouping had null for destinations");
                } else if (destinations.page_info != null) {
                    List<Edge> list = destinations.edges;
                    if (list != null) {
                        for (Edge edge : list) {
                            if (edge.cursor != null) {
                                Destination destination = edge.node;
                                if (destination == null) {
                                    throw new NullPointerException("edge had a null node");
                                } else if (destination.id == null) {
                                    throw new NullPointerException("destination had a null id");
                                }
                            } else {
                                throw new NullPointerException("edge had a null cursor");
                            }
                        }
                        return;
                    }
                    throw new NullPointerException("destinations had null for edges");
                } else {
                    throw new NullPointerException("destinations had null for page_info");
                }
            } else {
                throw new NullPointerException("node had null for grouping");
            }
        } else {
            throw new NullPointerException("GroupLaunchAppDestinationsResponse did not have a node");
        }
    }
}
