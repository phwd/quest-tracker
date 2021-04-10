package com.oculus.horizon.api.common.user;

import java.util.ArrayList;

public class FriendRequestsReceived {
    public int count;
    public ArrayList<Edge> edges;

    public static class Edge {
        public FriendRequestUser node;
        public String via_source;
    }
}
