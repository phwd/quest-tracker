package com.oculus.horizon.api.feed;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;

@SingleEntryMapResponse
public class FeedStoryResponse {
    public ArrayList<ActorResponse> actors;
    public Attachments attachments;
    public String body_text;
    public String deeplink;
    public long display_time;
    public String id;
    public ArrayList<String> render_styles;
    public String subtitle;
    public String title;

    public static class Attachments {
        public ArrayList<FeedAttachmentResponse> nodes;
    }
}
