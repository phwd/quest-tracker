package com.oculus.horizon.api.social;

import com.oculus.horizon.api.common.PageInfo;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.List;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GroupLaunchAppDestinationsResponse implements ValidatableApiResponse {
    @Nullable
    public Node node;

    public static class Destination {
        public String deeplink_message_for_deeplink_target;
        public String display_name;
        public String id;
        public String image;
        public String is_external_deeplinkable;
        public int max_group_launch_capacity;
    }

    public static class Destinations {
        public List<Edge> edges;
        public PageInfo page_info;
    }

    public static class Edge {
        public String cursor;
        public Destination node;
    }

    public static class Grouping {
        public Destinations destinations;
    }

    public static class Node {
        public Grouping grouping;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        Node node2 = this.node;
        if (node2 == null) {
            throw new NullPointerException("GroupLaunchAppDestinationsResponse did not have a node");
        } else if (node2.grouping == null) {
            throw new NullPointerException("node had null for grouping");
        } else if (this.node.grouping.destinations == null) {
            throw new NullPointerException("grouping had null for destinations");
        } else if (this.node.grouping.destinations.page_info == null) {
            throw new NullPointerException("destinations had null for page_info");
        } else if (this.node.grouping.destinations.edges != null) {
            for (Edge edge : this.node.grouping.destinations.edges) {
                if (edge.cursor == null) {
                    throw new NullPointerException("edge had a null cursor");
                } else if (edge.node == null) {
                    throw new NullPointerException("edge had a null node");
                } else if (edge.node.id == null) {
                    throw new NullPointerException("destination had a null id");
                }
            }
        } else {
            throw new NullPointerException("destinations had null for edges");
        }
    }
}
