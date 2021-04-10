package com.oculus.vrcast.googlecast.net;

import android.util.Log;
import com.google.protobuf.ByteString;
import com.oculus.vrcast.googlecast.net.CastProtocol;
import java.io.Closeable;
import java.io.IOException;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class CastV2Session implements Closeable {
    private static final String NS_CONNECTION = "urn:x-cast:com.google.cast.tp.connection";
    private static final String TAG = "CastV2Session";
    public final String localId;
    private boolean mClosed;
    private final CastV2Device mDevice;
    public final String remoteId;

    public boolean onMessage(String str, ByteString byteString) {
        return false;
    }

    public boolean onMessage(String str, String str2) {
        return false;
    }

    public void onRemoteClose() {
    }

    public CastV2Session(CastV2Device castV2Device, String str, String str2) throws IOException {
        this.mClosed = false;
        this.mDevice = castV2Device;
        if (str2 != null) {
            this.localId = str2;
        } else {
            this.localId = UUID.randomUUID().toString();
        }
        this.remoteId = str;
        sendMessage(NS_CONNECTION, "{\"type\": \"CONNECT\"}");
        if (this.mDevice.mSessions.putIfAbsent(this.localId, this) != null) {
            throw new IllegalArgumentException("Session already exists for given ID");
        }
    }

    public CastV2Session(CastV2Device castV2Device, String str) throws IOException {
        this(castV2Device, str, null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (isClosed()) {
            Log.d(TAG, "Attempted to close closed session");
            return;
        }
        try {
            sendMessage(NS_CONNECTION, "{\"type\": \"CLOSE\"}");
        } catch (IOException unused) {
        }
        this.mDevice.mSessions.remove(this.localId);
        this.mClosed = true;
    }

    public final boolean isClosed() {
        return this.mClosed;
    }

    public final void sendMessage(String str, String str2) throws IOException {
        if (!this.mClosed) {
            this.mDevice.sendCastMessage((CastProtocol.CastMessage) baseMsg().setNamespace(str).setPayloadType(CastProtocol.CastMessage.PayloadType.STRING).setPayloadUtf8(str2).build());
            return;
        }
        throw new IOException("Attempted to send message on closed session");
    }

    public final void sendMessage(String str, ByteString byteString) throws IOException {
        if (!this.mClosed) {
            this.mDevice.sendCastMessage((CastProtocol.CastMessage) baseMsg().setNamespace(str).setPayloadType(CastProtocol.CastMessage.PayloadType.BINARY).setPayloadBinary(byteString).build());
            return;
        }
        throw new IOException("Attempted to send message on closed session");
    }

    private CastProtocol.CastMessage.Builder baseMsg() {
        return CastProtocol.CastMessage.newBuilder().setProtocolVersion(CastProtocol.CastMessage.ProtocolVersion.CASTV2_1_0).setSourceId(this.localId).setDestinationId(this.remoteId);
    }

    public final boolean onMessagePriv(String str, String str2) {
        if (!str.equals(NS_CONNECTION)) {
            return onMessage(str, str2);
        }
        try {
            String string = new JSONObject(str2).getString("type");
            if (string.equals("CLOSE")) {
                Log.d(TAG, "Peer gracefully closed session " + this.localId);
                onRemoteClose();
            } else {
                Log.w(TAG, "Got unknown control message for session " + this.localId + ": " + string);
            }
            return true;
        } catch (JSONException e) {
            Log.e(TAG, "Got malformed control message for session " + this.localId, e);
            return true;
        }
    }
}
