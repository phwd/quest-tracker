package com.oculus.horizon.api.social;

import com.facebook.common.string.StringUtil;
import com.oculus.horizon.api.common.user.FriendRequestsReceived;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.Iterator;

@SingleEntryMapResponse
public class FriendRequestsResponse implements ValidatableApiResponse {
    public FriendRequestsReceived friend_requests_received_2;

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        FriendRequestsReceived friendRequestsReceived = this.friend_requests_received_2;
        if (friendRequestsReceived == null) {
            throw new NullPointerException("FriendRequestsResponse did not have a friend_requests_received_2");
        } else if (friendRequestsReceived.edges != null) {
            Iterator<FriendRequestsReceived.Edge> it = this.friend_requests_received_2.edges.iterator();
            while (it.hasNext()) {
                FriendRequestsReceived.Edge next = it.next();
                if (next.node == null) {
                    throw new NullPointerException("friend_requests_received_2 had a null edge");
                } else if (StringUtil.isEmptyOrNull(next.node.user_id)) {
                    throw new RuntimeException("Friend request for user [" + next.node.id + "] had no user_id");
                }
            }
        } else {
            throw new NullPointerException("friend_requests_received_2 had null for edges");
        }
    }
}
