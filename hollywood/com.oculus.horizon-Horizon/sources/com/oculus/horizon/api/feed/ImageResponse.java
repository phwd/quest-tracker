package com.oculus.horizon.api.feed;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class ImageResponse {
    public long height;
    public String name;
    public float scale;
    public String uri;
    public long width;
}
