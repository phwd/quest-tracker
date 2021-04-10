package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.PendingRequestCallback;
import java.nio.channels.NotYetConnectedException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class ChromePeerManager {
    @GuardedBy("this")
    private final Map<JsonRpcPeer, Object> mReceivingPeers = new HashMap();
    @GuardedBy("this")
    private JsonRpcPeer[] mReceivingPeersSnapshot;

    public synchronized boolean hasRegisteredPeers() {
        return !this.mReceivingPeers.isEmpty();
    }

    private synchronized JsonRpcPeer[] getReceivingPeersSnapshot() {
        if (this.mReceivingPeersSnapshot == null) {
            this.mReceivingPeersSnapshot = (JsonRpcPeer[]) this.mReceivingPeers.keySet().toArray(new JsonRpcPeer[this.mReceivingPeers.size()]);
        }
        return this.mReceivingPeersSnapshot;
    }

    public void sendNotificationToPeers(String str, Object obj) {
        sendMessageToPeers(str, obj, null);
    }

    private void sendMessageToPeers(String str, Object obj, @Nullable PendingRequestCallback pendingRequestCallback) {
        for (JsonRpcPeer jsonRpcPeer : getReceivingPeersSnapshot()) {
            try {
                jsonRpcPeer.invokeMethod(str, obj, pendingRequestCallback);
            } catch (NotYetConnectedException e) {
                LogRedirector.e("ChromePeerManager", "Error delivering data to Chrome", e);
            }
        }
    }
}
