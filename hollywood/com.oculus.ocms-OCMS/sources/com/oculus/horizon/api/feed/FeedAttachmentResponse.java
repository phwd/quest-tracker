package com.oculus.horizon.api.feed;

import java.util.ArrayList;

public class FeedAttachmentResponse {
    public Actors actor;
    public String id;
    public MediaConnection media;
    public String title;

    public static class Actors {
        public ArrayList<ActorResponse> nodes;
    }

    public static class MediaConnection {
        public ArrayList<StoryMediaResponse> nodes;
    }
}
