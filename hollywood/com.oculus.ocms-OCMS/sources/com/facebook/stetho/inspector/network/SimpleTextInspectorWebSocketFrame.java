package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.network.NetworkEventReporter;

public class SimpleTextInspectorWebSocketFrame implements NetworkEventReporter.InspectorWebSocketFrame {
    private final String mPayload;
    private final String mRequestId;

    @Override // com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketFrame
    public boolean mask() {
        return false;
    }

    @Override // com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketFrame
    public int opcode() {
        return 1;
    }

    public SimpleTextInspectorWebSocketFrame(String str, String str2) {
        this.mRequestId = str;
        this.mPayload = str2;
    }

    @Override // com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketFrame
    public String requestId() {
        return this.mRequestId;
    }

    @Override // com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketFrame
    public String payloadData() {
        return this.mPayload;
    }
}
