package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.inspector.network.NetworkEventReporter;
import java.io.UnsupportedEncodingException;

public class SimpleBinaryInspectorWebSocketFrame implements NetworkEventReporter.InspectorWebSocketFrame {
    private final byte[] mPayload;
    private final String mRequestId;

    @Override // com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketFrame
    public boolean mask() {
        return false;
    }

    @Override // com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketFrame
    public int opcode() {
        return 2;
    }

    public SimpleBinaryInspectorWebSocketFrame(String str, byte[] bArr) {
        this.mRequestId = str;
        this.mPayload = bArr;
    }

    @Override // com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketFrame
    public String requestId() {
        return this.mRequestId;
    }

    @Override // com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorWebSocketFrame
    public String payloadData() {
        try {
            return new String(this.mPayload, Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
