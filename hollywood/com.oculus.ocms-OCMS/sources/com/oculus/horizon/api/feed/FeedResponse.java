package com.oculus.horizon.api.feed;

import java.util.ArrayList;

public class FeedResponse {
    public Viewer viewer;

    public static class Viewer {
        public Feed feed;

        public static class Feed {
            public ArrayList<FeedStoryResponse> nodes;
        }
    }
}
