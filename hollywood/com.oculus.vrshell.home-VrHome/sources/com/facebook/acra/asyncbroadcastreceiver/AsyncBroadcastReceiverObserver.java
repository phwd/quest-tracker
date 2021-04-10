package com.facebook.acra.asyncbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.os.SystemClock;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AsyncBroadcastReceiverObserver {
    private static final ConcurrentHashMap<BroadcastReceiver.PendingResult, JSONObject> sActiveReceivers = new ConcurrentHashMap<>();

    @Nullable
    public static String blameActiveReceivers() {
        if (sActiveReceivers.isEmpty()) {
            return null;
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put("current_uptime_ms", SystemClock.uptimeMillis());
            obj.put("active_receivers_count", sActiveReceivers.size());
            JSONArray activeReceivers = new JSONArray();
            for (JSONObject blame : sActiveReceivers.values()) {
                activeReceivers.put(blame);
            }
            obj.put("active_receivers", activeReceivers);
            return obj.toString();
        } catch (JSONException je) {
            return je.toString();
        }
    }
}
