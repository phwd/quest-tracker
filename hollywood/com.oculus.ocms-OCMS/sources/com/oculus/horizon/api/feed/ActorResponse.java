package com.oculus.horizon.api.feed;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class ActorResponse {
    public String deeplink;
    public StoryMediaResponse icon;
    public String id;
    public String name;
}
