package com.oculus.vrcast.googlecast.net;

import android.util.Log;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.oculus.vrcast.googlecast.net.CastProtocol;
import com.oculus.vrcast.googlecast.net.auth.AuthContext;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class AuthSession extends CastV2Session {
    private static final String NS_DEVICEAUTH = "urn:x-cast:com.google.cast.tp.deviceauth";
    private static final String TAG = "AuthSession";
    private final AuthContext mAuthContext;
    private volatile CompletableFuture<AuthContext.AuthResult> mResult;

    public AuthSession(CastV2Device castV2Device, AuthContext authContext) throws IOException {
        super(castV2Device, "receiver-0");
        this.mAuthContext = authContext;
    }

    public CompletableFuture<AuthContext.AuthResult> sendAuthChallenge() throws IOException {
        synchronized (this) {
            if (this.mResult == null) {
                this.mResult = new CompletableFuture<>();
            } else {
                throw new IllegalStateException("Should call this method only once.");
            }
        }
        CastProtocol.DeviceAuthMessage.Builder newBuilder = CastProtocol.DeviceAuthMessage.newBuilder();
        CastProtocol.AuthChallenge.Builder newBuilder2 = CastProtocol.AuthChallenge.newBuilder();
        newBuilder2.setHashAlgorithm(this.mAuthContext.getHashAlgorithm()).setSenderNonce(this.mAuthContext.getNonce());
        sendMessage(NS_DEVICEAUTH, ((CastProtocol.DeviceAuthMessage) newBuilder.setChallenge(newBuilder2).build()).toByteString());
        return this.mResult;
    }

    @Override // com.oculus.vrcast.googlecast.net.CastV2Session
    public boolean onMessage(String str, ByteString byteString) {
        if (!str.equals(NS_DEVICEAUTH)) {
            return false;
        }
        try {
            CastProtocol.DeviceAuthMessage parseFrom = CastProtocol.DeviceAuthMessage.parseFrom(byteString);
            if (parseFrom.hasError()) {
                String str2 = TAG;
                Log.e(str2, "DeviceAuthMessage has error " + parseFrom.getError());
                this.mResult.complete(AuthContext.AuthResult.AuthErrorProtocol);
                return true;
            } else if (!parseFrom.hasResponse()) {
                Log.e(TAG, "DeviceAuthMessage has missing response");
                this.mResult.complete(AuthContext.AuthResult.AuthErrorProtocol);
                return true;
            } else {
                this.mResult.complete(this.mAuthContext.verifyResponse(parseFrom.getResponse()));
                return true;
            }
        } catch (InvalidProtocolBufferException e) {
            Log.e(TAG, "Could not parse device auth message", e);
            this.mResult.complete(AuthContext.AuthResult.AuthErrorProtocol);
            return true;
        }
    }
}
