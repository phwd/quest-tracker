package com.oculus.horizon.social.api;

import com.oculus.horizon.api.common.user.User;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class FriendRequestCancelResponse {
    public User canceled_friend_requestee;
}
