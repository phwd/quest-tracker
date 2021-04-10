package com.oculus.horizon.api.fbconnect;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;

@SingleEntryMapResponse
public class FBConnectGetConnectedFacebookFriendsResponse {
    public LinkedAccountsInfo linked_accounts_info;

    public static class ConnectedFacebookFriend {
        public String profile_photo_uri;
    }

    public static class ConnectedFacebookFriends {
        public int count;
        public ArrayList<ConnectedFacebookFriend> nodes;
    }

    public static class LinkedAccountsInfo {
        public ConnectedFacebookFriends facebook_friends_connected;
    }
}
