package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;

@SingleEntryMapResponse
public class SocialFriendRequestsResponse implements ValidatableApiResponse {
    public final FriendRequests friend_requests_received_2;

    public class FriendRequests {
        public int count;
        public final ArrayList<Edge> edges;

        public class Edge {
            public final SocialUser node;
            public String via_source;

            public Edge() {
            }
        }

        public FriendRequests() {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0012  */
    @Override // com.oculus.http.core.base.ValidatableApiResponse
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void validate() throws java.lang.RuntimeException {
        /*
            r3 = this;
            com.oculus.horizon.api.social.SocialFriendRequestsResponse$FriendRequests r0 = r3.friend_requests_received_2
            if (r0 == 0) goto L_0x003c
            java.util.ArrayList<com.oculus.horizon.api.social.SocialFriendRequestsResponse$FriendRequests$Edge> r0 = r0.edges
            if (r0 == 0) goto L_0x0039
            java.util.Iterator r2 = r0.iterator()
        L_0x000c:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0038
            java.lang.Object r1 = r2.next()
            com.oculus.horizon.api.social.SocialFriendRequestsResponse$FriendRequests$Edge r1 = (com.oculus.horizon.api.social.SocialFriendRequestsResponse.FriendRequests.Edge) r1
            com.oculus.horizon.api.social.SocialUser r0 = r1.node
            if (r0 == 0) goto L_0x003f
            java.lang.String r0 = r0.id
            if (r0 == 0) goto L_0x0026
            int r0 = r0.length()
            if (r0 != 0) goto L_0x000c
        L_0x0026:
            java.lang.String r2 = "Friend request for user ["
            com.oculus.horizon.api.social.SocialUser r0 = r1.node
            java.lang.String r1 = r0.id
            java.lang.String r0 = "] had no user_id"
            java.lang.String r0 = X.AnonymousClass006.A07(r2, r1, r0)
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x0038:
            return
        L_0x0039:
            java.lang.String r0 = "friend_requests_received_2 had null for edges"
            goto L_0x0041
        L_0x003c:
            java.lang.String r0 = "FriendRequestsResponse did not have a friend_requests_received_2"
            goto L_0x0041
        L_0x003f:
            java.lang.String r0 = "friend_requests_received_2 had a null edge"
        L_0x0041:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.api.social.SocialFriendRequestsResponse.validate():void");
    }
}
