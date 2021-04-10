package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;

@SingleEntryMapResponse
public class BlockedUsersResponse implements ValidatableApiResponse {
    public final BlockedUsers blocked_users;

    public static class BlockedUsers {
        public int count;
        public final ArrayList<BlockedUser> nodes;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        BlockedUsers blockedUsers = this.blocked_users;
        if (blockedUsers == null) {
            throw new NullPointerException("BlockedUsersResponse had no blocked_users");
        } else if (blockedUsers.nodes == null) {
            throw new NullPointerException("blocked_users had no nodes");
        }
    }
}
