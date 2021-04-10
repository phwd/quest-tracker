package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class X7 extends BroadcastReceiver {
    public static final X6 Companion = new X6();
    public static final boolean DEBUG_LIFECYCLE_EVENTS = true;
    public static final String TAG = "AssistantUILifecycle";

    public void onLayerEvent(String str, String str2) {
    }

    public void onSurfaceCloseEvent(String str, String str2, String str3, String str4) {
    }

    public void onSurfaceEvent(String str, String str2, String str3) {
    }

    public void onReceive(Context context, Intent intent) {
        C0514bB.A02(context, "context");
        C0514bB.A02(intent, "intent");
        String stringExtra = intent.getStringExtra("EXTRA_EVENT_ID");
        if (intent.hasExtra("EXTRA_SURFACE_ID")) {
            String stringExtra2 = intent.getStringExtra("EXTRA_SURFACE_ID");
            String stringExtra3 = intent.getStringExtra("EXTRA_SURFACE_TYPE");
            if ("ATTENTION_SYSTEM_ON_CLOSE".equals(stringExtra) || "MAIN_LAYER_ON_CLOSE".equals(stringExtra)) {
                String parseCloseSource = parseCloseSource(intent);
                onSurfaceCloseEvent(stringExtra, stringExtra2, stringExtra3, parseCloseSource);
                onLayerEvent(stringExtra, parseCloseSource);
                C0139Dd.A0K(TAG, "Surface Close Event: %s on %s (%s). Closed by %s", stringExtra, stringExtra2, stringExtra3, parseCloseSource);
                return;
            }
            if (stringExtra2 == null) {
                onLayerEvent(stringExtra, parseCloseSource(intent));
            }
            C0139Dd.A0J(TAG, "Surface Event: %s on %s (%s)", stringExtra, stringExtra2, stringExtra3);
            return;
        }
        onLayerEvent(stringExtra, parseCloseSource(intent));
        C0139Dd.A0H(TAG, "Layer Event: %s, event data: %s", stringExtra, intent.getStringExtra("EXTRA_EVENT_DATA_SOURCE"));
    }

    public final String parseCloseSource(Intent intent) {
        C0514bB.A02(intent, "intent");
        String stringExtra = intent.getStringExtra("EXTRA_EVENT_DATA_SOURCE");
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                return new JSONObject(stringExtra).optString("source", null);
            } catch (JSONException unused) {
                C0139Dd.A0P(TAG, "Closed with unknown data: %s", stringExtra);
            }
        }
        return null;
    }
}
