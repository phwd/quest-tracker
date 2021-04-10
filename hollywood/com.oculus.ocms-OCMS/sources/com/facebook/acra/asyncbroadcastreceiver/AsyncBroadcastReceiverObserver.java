package com.facebook.acra.asyncbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
@ThreadSafe
public abstract class AsyncBroadcastReceiverObserver {
    static final String ACTIVE_RECEIVERS = "active_receivers";
    static final String ACTIVE_RECEIVERS_COUNT = "active_receivers_count";
    static final String BROADCAST_UPTIME_MS = "broadcast_uptime_ms";
    static final String CURRENT_UPTIME_MS = "current_uptime_ms";
    static final String PENDING_RESULT = "pending_result";
    static final String RECEIVER = "receiver";
    static final String RECEIVER_CLASS = "receiver_class";
    private static final ConcurrentHashMap<BroadcastReceiver.PendingResult, JSONObject> sActiveReceivers = new ConcurrentHashMap<>();

    public static void goAsync(BroadcastReceiver broadcastReceiver, BroadcastReceiver.PendingResult pendingResult) {
        sActiveReceivers.put(pendingResult, blame(broadcastReceiver, pendingResult));
    }

    public static void finish(BroadcastReceiver.PendingResult pendingResult) {
        sActiveReceivers.remove(pendingResult);
    }

    @Nullable
    public static String blameActiveReceivers() {
        if (sActiveReceivers.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CURRENT_UPTIME_MS, SystemClock.uptimeMillis());
            jSONObject.put(ACTIVE_RECEIVERS_COUNT, sActiveReceivers.size());
            JSONArray jSONArray = new JSONArray();
            for (JSONObject jSONObject2 : sActiveReceivers.values()) {
                jSONArray.put(jSONObject2);
            }
            jSONObject.put(ACTIVE_RECEIVERS, jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            return e.toString();
        }
    }

    static void clear() {
        sActiveReceivers.clear();
    }

    private static JSONObject blame(BroadcastReceiver broadcastReceiver, BroadcastReceiver.PendingResult pendingResult) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BROADCAST_UPTIME_MS, SystemClock.uptimeMillis());
            jSONObject.put(RECEIVER_CLASS, broadcastReceiver.getClass());
            jSONObject.put("receiver", broadcastReceiver.toString());
            jSONObject.put(PENDING_RESULT, pendingResult.toString());
        } catch (JSONException e) {
            try {
                jSONObject.put("error", e.toString());
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }
        return jSONObject;
    }
}
