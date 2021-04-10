package com.oculus.horizon.social.api;

import com.oculus.horizon.api.common.user.User;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class FriendRequestAcceptResponse {
    public User accepted_friend_requester;
}
