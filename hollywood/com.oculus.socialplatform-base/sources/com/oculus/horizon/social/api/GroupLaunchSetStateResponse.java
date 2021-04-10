package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class GroupLaunchSetStateResponse {
    public GroupLaunch group_launch;

    public static class GroupLaunch {
        public String id;
    }
}
