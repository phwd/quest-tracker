package com.facebook.acra.asyncbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class AsyncBroadcastReceiverObserver {
    private static final ConcurrentHashMap<BroadcastReceiver.PendingResult, JSONObject> sActiveReceivers = new ConcurrentHashMap<>();

    public static String blameActiveReceivers() {
        if (sActiveReceivers.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("current_uptime_ms", SystemClock.uptimeMillis());
            jSONObject.put("active_receivers_count", sActiveReceivers.size());
            JSONArray jSONArray = new JSONArray();
            for (JSONObject jSONObject2 : sActiveReceivers.values()) {
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("active_receivers", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            return e.toString();
        }
    }
}
