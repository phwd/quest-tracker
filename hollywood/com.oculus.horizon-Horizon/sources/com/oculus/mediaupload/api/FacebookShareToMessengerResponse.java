package com.oculus.mediaupload.api;

import java.util.Map;
import javax.annotation.Nullable;

public class FacebookShareToMessengerResponse {
    public final Object data;

    @Nullable
    public final String A00() {
        Map map;
        try {
            Map map2 = (Map) this.data;
            if (map2 != null && map2.containsKey("oculus_share_to_messenger_message_send") && (map = (Map) map2.get("oculus_share_to_messenger_message_send")) != null && map.containsKey("job_id")) {
                return (String) map.get("job_id");
            }
        } catch (ClassCastException unused) {
        }
        return null;
    }
}
