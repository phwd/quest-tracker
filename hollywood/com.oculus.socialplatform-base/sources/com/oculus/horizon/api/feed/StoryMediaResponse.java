package com.oculus.horizon.api.feed;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class StoryMediaResponse {
    public MediaResponse content_media;
    public String deeplink;
    public String id;
    public ImageResponse thumbnail_image;
}
