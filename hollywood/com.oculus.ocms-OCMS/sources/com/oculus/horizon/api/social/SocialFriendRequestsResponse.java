package com.oculus.horizon.api.social;

import com.facebook.common.string.StringUtil;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;
import java.util.Iterator;

@SingleEntryMapResponse
public class SocialFriendRequestsResponse implements ValidatableApiResponse {
    public FriendRequests friend_requests_received_2;

    public class FriendRequests {
        public int count;
        public ArrayList<Edge> edges;

        public FriendRequests() {
        }

        public class Edge {
            public SocialUser node;
            public String via_source;

            public Edge() {
            }
        }
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        FriendRequests friendRequests = this.friend_requests_received_2;
        if (friendRequests == null) {
            throw new NullPointerException("FriendRequestsResponse did not have a friend_requests_received_2");
        } else if (friendRequests.edges != null) {
            Iterator<FriendRequests.Edge> it = this.friend_requests_received_2.edges.iterator();
            while (it.hasNext()) {
                FriendRequests.Edge next = it.next();
                if (next.node == null) {
                    throw new NullPointerException("friend_requests_received_2 had a null edge");
                } else if (StringUtil.isEmptyOrNull(next.node.id)) {
                    throw new RuntimeException("Friend request for user [" + next.node.id + "] had no user_id");
                }
            }
        } else {
            throw new NullPointerException("friend_requests_received_2 had null for edges");
        }
    }
}
