package com.oculus.horizon.api.feed;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class MediaResponse {
    public long height;
    public String name;
    public String uri;
    public long width;
}
