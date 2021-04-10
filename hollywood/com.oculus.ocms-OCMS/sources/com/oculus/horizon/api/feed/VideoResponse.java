package com.oculus.horizon.api.feed;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class VideoResponse {
    public int bitrate;
    public int duration;
    public long height;
    public String name;
    public String uri;
    public long width;
}
