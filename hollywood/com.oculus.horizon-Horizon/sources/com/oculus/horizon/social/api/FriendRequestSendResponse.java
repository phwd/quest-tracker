package com.oculus.horizon.social.api;

import com.oculus.horizon.api.common.user.User;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class FriendRequestSendResponse {
    public User sent_friend_requestee;
    public SearchResultUser sent_friend_requestee_search_result;
}
