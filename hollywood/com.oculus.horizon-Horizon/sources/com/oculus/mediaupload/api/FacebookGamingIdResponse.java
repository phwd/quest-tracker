package com.oculus.mediaupload.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class FacebookGamingIdResponse {
    public final Node node;

    public static class Grouping {
        @Nullable
        public final String facebook_game_id;
    }

    public static class Node {
        public final Grouping grouping;
    }
}
