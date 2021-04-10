package com.oculus.companion.server.connection;

import android.content.Context;
import android.os.UserManager;
import com.google.protobuf.ByteString;
import com.oculus.companion.authentication.Authentication;
import com.oculus.companion.server.Protocol$HelloResponse;
import com.oculus.companion.server.Protocol$HelloSignedData;
import com.oculus.companion.server.Protocol$Request;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;

public class SecureConnection {
    private static final String TAG = "SecureConnection";
    private final Authentication mAuthenticationServer;
    private final CertificateModule mCertificateModule;
    private final ConnectionState mConnectionState;
    private final Context mContext;
    private final Logger mLogger;
    private final SequenceRecorder mSequenceRecorder = new SequenceRecorder();

    public interface Logger {
        void logEvent(String str, String str2, int i, boolean z);
    }

    public interface StateProvider {
    }

    public SecureConnection(Authentication authentication, Logger logger, Context context, CertificateModule certificateModule, ConnectionState connectionState) {
        this.mAuthenticationServer = authentication;
        this.mLogger = logger;
        this.mContext = context;
        this.mCertificateModule = certificateModule;
        this.mConnectionState = connectionState;
    }

    public Authentication getAuthenticationServer() {
        return this.mAuthenticationServer;
    }

    public Protocol$HelloResponse.Builder handleHello(byte[] bArr, byte[] bArr2, byte[] bArr3) throws InvalidAlgorithmParameterException, IOException {
        Protocol$HelloResponse.Builder newBuilder = Protocol$HelloResponse.newBuilder();
        boolean z = false;
        this.mConnectionState.secureConnection.set(false);
        this.mAuthenticationServer.generateECDHKeysLS();
        this.mAuthenticationServer.keyExchangeLS(bArr);
        Protocol$HelloSignedData.Builder newBuilder2 = Protocol$HelloSignedData.newBuilder();
        newBuilder2.setServerPublicKey(ByteString.copyFrom(this.mAuthenticationServer.getPublickeyLS()));
        if (this.mConnectionState.isClaimedByUser) {
            newBuilder2.setAuthenticationChallenge(ByteString.copyFrom(this.mAuthenticationServer.generateAuthenticationSession()));
        }
        if (requireUserPin(this.mContext)) {
            newBuilder2.setDeviceNeedsToBeUnlocked(true);
        }
        if (bArr3 == null || !Arrays.equals(bArr3, this.mCertificateModule.getCertificateHash())) {
            z = true;
        }
        if (z) {
            newBuilder.setServerCertificate(ByteString.copyFrom(this.mCertificateModule.loadCertificate()));
        }
        Protocol$HelloSignedData protocol$HelloSignedData = (Protocol$HelloSignedData) newBuilder2.build();
        byte[] byteArray = protocol$HelloSignedData.toByteArray();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(byteArray);
        byteArrayOutputStream.write(bArr2);
        byte[] sign = this.mCertificateModule.sign(byteArrayOutputStream.toByteArray());
        newBuilder.setSignedData(protocol$HelloSignedData.toByteString());
        newBuilder.setSignature(ByteString.copyFrom(sign));
        return newBuilder;
    }

    public boolean handleAuthenticate(byte[] bArr, byte[] bArr2) {
        boolean verifyAuthenticationSession = this.mAuthenticationServer.verifyAuthenticationSession(bArr, bArr2);
        this.mConnectionState.isAuthenticated.set(verifyAuthenticationSession);
        return verifyAuthenticationSession;
    }

    public static class SecureConnectionException extends Exception {
        private final boolean mDropConnection;
        private final Protocol$Request mRequest;

        public SecureConnectionException(String str, Protocol$Request protocol$Request, boolean z, Throwable th) {
            super(str, th);
            this.mRequest = protocol$Request;
            this.mDropConnection = z;
        }

        public Protocol$Request getRequest() {
            return this.mRequest;
        }

        public boolean shouldDropConnection() {
            return this.mDropConnection;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044 A[SYNTHETIC, Splitter:B:18:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0061 A[Catch:{ InvalidProtocolBufferException -> 0x00ca, IllegalArgumentException -> 0x00b5, all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0077 A[Catch:{ InvalidProtocolBufferException -> 0x00ca, IllegalArgumentException -> 0x00b5, all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x008a A[Catch:{ InvalidProtocolBufferException -> 0x00ca, IllegalArgumentException -> 0x00b5, all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ab A[Catch:{ InvalidProtocolBufferException -> 0x00ca, IllegalArgumentException -> 0x00b5, all -> 0x00b3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.companion.server.Protocol$Request processRequest(byte[] r9) throws com.oculus.companion.server.connection.SecureConnection.SecureConnectionException {
        /*
        // Method dump skipped, instructions count: 221
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.connection.SecureConnection.processRequest(byte[]):com.oculus.companion.server.Protocol$Request");
    }

    public void resetSequences() {
        this.mSequenceRecorder.requestSequence();
    }

    public boolean isCurrentConnectionSecure() {
        return this.mConnectionState.secureConnection.get();
    }

    public void resetCurrentSecureConnection() {
        synchronized (ConnectionState.secureLock) {
            this.mConnectionState.secureConnection.set(false);
        }
    }

    private static boolean requireUserPin(Context context) {
        return !((UserManager) context.getSystemService("user")).isUserUnlocked();
    }
}
