package com.oculus.horizon.api.fbconnect;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class FBConnectFetchAccountResponse {
    public LinkedAccounts linked_accounts_info;

    public static class LinkedAccounts {
        public FBConnectAccount facebook_account;
    }

    private boolean hasFBConnectAccount() {
        LinkedAccounts linkedAccounts = this.linked_accounts_info;
        return (linkedAccounts == null || linkedAccounts.facebook_account == null) ? false : true;
    }

    @Nullable
    public String getFacebookID() {
        if (hasFBConnectAccount()) {
            return this.linked_accounts_info.facebook_account.facebook_id;
        }
        return null;
    }

    @Nullable
    public String getFriendPolicy() {
        if (hasFBConnectAccount()) {
            return this.linked_accounts_info.facebook_account.friend_policy;
        }
        return null;
    }
}
