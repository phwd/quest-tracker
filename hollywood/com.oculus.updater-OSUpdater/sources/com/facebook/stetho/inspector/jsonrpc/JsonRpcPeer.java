package com.facebook.stetho.inspector.jsonrpc;

import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcRequest;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.websocket.SimpleSession;
import java.nio.channels.NotYetConnectedException;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import org.json.JSONObject;

@ThreadSafe
public class JsonRpcPeer {
    @GuardedBy("this")
    private long mNextRequestId;
    private final ObjectMapper mObjectMapper;
    private final SimpleSession mPeer;
    @GuardedBy("this")
    private final Map<Long, PendingRequest> mPendingRequests;

    public void invokeMethod(String str, Object obj, @Nullable PendingRequestCallback pendingRequestCallback) throws NotYetConnectedException {
        Util.throwIfNull(str);
        this.mPeer.sendText(((JSONObject) this.mObjectMapper.convertValue(new JsonRpcRequest(pendingRequestCallback != null ? Long.valueOf(preparePendingRequest(pendingRequestCallback)) : null, str, (JSONObject) this.mObjectMapper.convertValue(obj, JSONObject.class)), JSONObject.class)).toString());
    }

    private synchronized long preparePendingRequest(PendingRequestCallback pendingRequestCallback) {
        long j;
        j = this.mNextRequestId;
        this.mNextRequestId = 1 + j;
        this.mPendingRequests.put(Long.valueOf(j), new PendingRequest(j, pendingRequestCallback));
        return j;
    }
}
