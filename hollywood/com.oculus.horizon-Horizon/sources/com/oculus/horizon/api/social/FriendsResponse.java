package com.oculus.horizon.api.social;

import com.oculus.horizon.api.common.user.User;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;

@SingleEntryMapResponse
public class FriendsResponse {
    public Friends friends;

    public static class Friends {
        public int count;
        public ArrayList<User> nodes;
    }
}
