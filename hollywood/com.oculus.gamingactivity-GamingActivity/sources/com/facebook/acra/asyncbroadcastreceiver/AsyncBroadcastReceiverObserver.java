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

    public static void goAsync(BroadcastReceiver receiver, BroadcastReceiver.PendingResult result) {
        sActiveReceivers.put(result, blame(receiver, result));
    }

    public static void finish(BroadcastReceiver.PendingResult result) {
        sActiveReceivers.remove(result);
    }

    @Nullable
    public static String blameActiveReceivers() {
        if (sActiveReceivers.isEmpty()) {
            return null;
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put(CURRENT_UPTIME_MS, SystemClock.uptimeMillis());
            obj.put(ACTIVE_RECEIVERS_COUNT, sActiveReceivers.size());
            JSONArray activeReceivers = new JSONArray();
            for (JSONObject blame : sActiveReceivers.values()) {
                activeReceivers.put(blame);
            }
            obj.put(ACTIVE_RECEIVERS, activeReceivers);
            return obj.toString();
        } catch (JSONException je) {
            return je.toString();
        }
    }

    static void clear() {
        sActiveReceivers.clear();
    }

    private static JSONObject blame(BroadcastReceiver receiver, BroadcastReceiver.PendingResult result) {
        JSONObject obj = new JSONObject();
        try {
            obj.put(BROADCAST_UPTIME_MS, SystemClock.uptimeMillis());
            obj.put(RECEIVER_CLASS, receiver.getClass());
            obj.put(RECEIVER, receiver.toString());
            obj.put(PENDING_RESULT, result.toString());
        } catch (JSONException je) {
            try {
                obj.put("error", je.toString());
            } catch (JSONException impossible) {
                throw new RuntimeException(impossible);
            }
        }
        return obj;
    }
}
