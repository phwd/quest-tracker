package com.oculus.mediaupload.api;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FacebookGetUserIdResponse {
    public final Object data;

    public final String A00() {
        Map map;
        try {
            Map map2 = (Map) this.data;
            if (map2 != null && map2.containsKey("me") && (map = (Map) map2.get("me")) != null && map.containsKey("id")) {
                return (String) map.get("id");
            }
        } catch (ClassCastException unused) {
        }
        return "";
    }
}
