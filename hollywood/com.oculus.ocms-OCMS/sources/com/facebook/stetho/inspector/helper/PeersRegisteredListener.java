package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PeersRegisteredListener implements PeerRegistrationListener {
    private AtomicInteger mPeers = new AtomicInteger(0);

    /* access modifiers changed from: protected */
    public abstract void onFirstPeerRegistered();

    /* access modifiers changed from: protected */
    public abstract void onLastPeerUnregistered();

    /* access modifiers changed from: protected */
    public void onPeerAdded(JsonRpcPeer jsonRpcPeer) {
    }

    /* access modifiers changed from: protected */
    public void onPeerRemoved(JsonRpcPeer jsonRpcPeer) {
    }

    @Override // com.facebook.stetho.inspector.helper.PeerRegistrationListener
    public final void onPeerRegistered(JsonRpcPeer jsonRpcPeer) {
        if (this.mPeers.incrementAndGet() == 1) {
            onFirstPeerRegistered();
        }
        onPeerAdded(jsonRpcPeer);
    }

    @Override // com.facebook.stetho.inspector.helper.PeerRegistrationListener
    public final void onPeerUnregistered(JsonRpcPeer jsonRpcPeer) {
        if (this.mPeers.decrementAndGet() == 0) {
            onLastPeerUnregistered();
        }
        onPeerRemoved(jsonRpcPeer);
    }
}
