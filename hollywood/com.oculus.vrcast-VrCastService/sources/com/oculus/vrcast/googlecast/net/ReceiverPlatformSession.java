package com.oculus.vrcast.googlecast.net;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReceiverPlatformSession extends JSONTransactionSession {
    private static final String NS_DEVICEAUTH = "urn:x-cast:com.google.cast.tp.deviceauth";
    private static final String NS_MEDIA = "urn:x-cast:com.google.cast.media";
    private static final String NS_RECEIVER = "urn:x-cast:com.google.cast.receiver";
    private static final String TAG = "ReceiverPlatformSession";

    public static class Application {
        public final String sessionId;
        public final String transportId;

        private Application(String str, String str2) {
            this.sessionId = str;
            this.transportId = str2;
        }
    }

    public ReceiverPlatformSession(CastV2Device castV2Device) throws IOException {
        super(castV2Device, "receiver-0", NS_RECEIVER, "requestId");
    }

    public CompletableFuture<JSONObject> getStatus() throws IOException {
        try {
            return sendRequest(new JSONObject().put("type", "GET_STATUS"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<Application> launch(String str) throws IOException {
        try {
            return sendRequest(new JSONObject().put("type", "LAUNCH").put("appId", str)).thenApply((Function<? super JSONObject, ? extends U>) new Function(str) {
                /* class com.oculus.vrcast.googlecast.net.$$Lambda$ReceiverPlatformSession$1MgRuLk4TmCY_Z4OR9CqgcACFQQ */
                private final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ReceiverPlatformSession.lambda$launch$0(this.f$0, (JSONObject) obj);
                }
            });
        } catch (JSONException e) {
            throw new IllegalArgumentException("Failed to construct JSON", e);
        }
    }

    static /* synthetic */ Application lambda$launch$0(String str, JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONObject("status").getJSONArray("applications");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2.getString("appId").equals(str)) {
                    return new Application(jSONObject2.getString("sessionId"), jSONObject2.getString("transportId"));
                }
            }
            return null;
        } catch (JSONException e) {
            throw new CompletionException(e);
        }
    }
}
