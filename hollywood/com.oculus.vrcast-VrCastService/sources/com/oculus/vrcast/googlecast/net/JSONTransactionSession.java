package com.oculus.vrcast.googlecast.net;

import android.util.Log;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONTransactionSession extends CastV2Session {
    private static final String TAG = "JSONTransactionSession";
    private int lastRequestId = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
    private final String mIdKey;
    private final String mNamespace;
    final Map<Integer, CompletableFuture<JSONObject>> mRequests = new ConcurrentHashMap();

    public JSONTransactionSession(CastV2Device castV2Device, String str, String str2, String str3) throws IOException {
        super(castV2Device, str);
        this.mNamespace = str2;
        this.mIdKey = str3;
    }

    @Override // com.oculus.vrcast.googlecast.net.CastV2Session
    public boolean onMessage(String str, String str2) {
        if (!str.equals(this.mNamespace)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            int i = jSONObject.getInt(this.mIdKey);
            CompletableFuture<JSONObject> completableFuture = this.mRequests.get(Integer.valueOf(i));
            if (completableFuture == null) {
                Log.w(TAG, "Discarding response with unknown request ID " + i);
                return true;
            }
            completableFuture.complete(jSONObject);
            return true;
        } catch (JSONException e) {
            Log.e(TAG, "Got malformed JSON on session " + this.localId, e);
            return true;
        }
    }

    public CompletableFuture<JSONObject> sendRequest(JSONObject jSONObject) throws IOException {
        int requestId = getRequestId();
        try {
            jSONObject.put(this.mIdKey, requestId);
            sendMessage(this.mNamespace, jSONObject.toString());
            CompletableFuture<JSONObject> completableFuture = new CompletableFuture<>();
            this.mRequests.put(Integer.valueOf(requestId), completableFuture);
            return completableFuture;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private int getRequestId() {
        int i = this.lastRequestId;
        if (i == Integer.MAX_VALUE) {
            this.lastRequestId = 0;
        } else {
            this.lastRequestId = i + 1;
        }
        return this.lastRequestId;
    }
}
