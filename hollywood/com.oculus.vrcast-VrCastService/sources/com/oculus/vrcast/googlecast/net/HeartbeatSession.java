package com.oculus.vrcast.googlecast.net;

import android.util.Log;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class HeartbeatSession extends CastV2Session {
    private static final String HEARTBEAT_SESSION = "Tr@n$p0rt-0";
    private static final String NS_HEARTBEAT = "urn:x-cast:com.google.cast.tp.heartbeat";
    private static final String TAG = "HeartbeatSession";
    private boolean mSeenPong = true;

    public HeartbeatSession(CastV2Device castV2Device) throws IOException {
        super(castV2Device, HEARTBEAT_SESSION, HEARTBEAT_SESSION);
    }

    @Override // com.oculus.vrcast.googlecast.net.CastV2Session
    public boolean onMessage(String str, String str2) {
        if (!str.equals(NS_HEARTBEAT)) {
            return false;
        }
        try {
            String string = new JSONObject(str2).getString("type");
            if (string.equals("PING")) {
                try {
                    sendMessage(NS_HEARTBEAT, "{\"type\": \"PONG\"}");
                } catch (IOException e) {
                    Log.e(TAG, "Failed to pong!", e);
                }
            } else if (string.equals("PONG")) {
                this.mSeenPong = true;
            } else {
                Log.w(TAG, "Got unknown heartbeat message: " + string);
            }
            return true;
        } catch (JSONException e2) {
            Log.e(TAG, "Got malformed heartbeat message", e2);
            return true;
        }
    }

    public boolean checkAndPing() {
        boolean z = this.mSeenPong;
        try {
            sendMessage(NS_HEARTBEAT, "{\"type\": \"PING\"}");
        } catch (IOException e) {
            Log.e(TAG, "Failed to send ping!", e);
        }
        this.mSeenPong = false;
        return z;
    }
}
