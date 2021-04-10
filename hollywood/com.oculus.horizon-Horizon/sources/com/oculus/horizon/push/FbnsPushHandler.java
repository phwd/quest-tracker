package com.oculus.horizon.push;

import java.util.Set;
import org.json.JSONObject;

public interface FbnsPushHandler {
    Set<String> A3T();

    void A6M(String str, JSONObject jSONObject);
}
