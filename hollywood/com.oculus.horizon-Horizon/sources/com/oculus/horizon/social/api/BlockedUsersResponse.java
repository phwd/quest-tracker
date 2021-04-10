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
        String str;
        BlockedUsers blockedUsers = this.blocked_users;
        if (blockedUsers == null) {
            str = "BlockedUsersResponse had no blocked_users";
        } else if (blockedUsers.nodes == null) {
            str = "blocked_users had no nodes";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
