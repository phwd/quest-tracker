package com.oculus.vrcast.googlecast.net;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.google.protobuf.InvalidProtocolBufferException;
import com.oculus.vrcast.googlecast.net.CastProtocol;
import com.oculus.vrcast.googlecast.net.CastV2Device;
import com.oculus.vrcast.googlecast.net.auth.AuthContext;
import com.oculus.vrcast.googlecast.net.auth.AuthException;
import com.oculus.vrcast.googlecast.net.auth.GoogleCastCA;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import oculus.internal.Gatekeeper;

public class CastV2Device implements Closeable {
    private static final int DEVICE_AUTH_TIMEOUT_SEC = 5;
    private static final int INCREASED_PING_FAILURES_TO_ERROR = 3;
    private static final int LONGER_PING_INTERVAL_SEC = 6;
    private static final int MAX_BODY_SIZE = 65536;
    private static final int PING_FAILURES_TO_ERROR = 1;
    private static final int PING_INTERVAL_SEC = 3;
    private static final int SOCKET_CONNECT_TIMEOUT_SEC = 5;
    private static final String TAG = "CastV2Device";
    private final AuthContext mAuthContext;
    private final AuthSession mAuthSession;
    private int mCurrentPingFailures;
    private final Handler mEventHandler;
    private final HandlerThread mEventThread;
    private final HeartbeatSession mHeartbeatSession;
    private final int mMaxPingFailuresToError;
    private final Thread mMessageThread;
    private final int mPingIntervalSec;
    final Map<String, CastV2Session> mSessions;
    private final SSLSocket mSocket;

    public void onPingFailed() {
    }

    public CastV2Device(InetSocketAddress inetSocketAddress, GoogleCastCA googleCastCA, Looper looper) throws IOException, AuthException {
        this.mSessions = new ConcurrentHashMap();
        this.mCurrentPingFailures = 0;
        Log.i(TAG, "Connecting to Google Cast device at " + inetSocketAddress);
        if (new Gatekeeper(19).isEnabled()) {
            this.mPingIntervalSec = 6;
        } else {
            this.mPingIntervalSec = 3;
        }
        Log.i(TAG, "Use ping interval(sec): " + this.mPingIntervalSec);
        if (new Gatekeeper(28).isEnabled()) {
            this.mMaxPingFailuresToError = 3;
        } else {
            this.mMaxPingFailuresToError = 1;
        }
        Log.i(TAG, "Using number of ping failures until error: " + this.mMaxPingFailuresToError);
        boolean isEnabled = new Gatekeeper(29).isEnabled();
        Log.i(TAG, "Remove ping: " + isEnabled);
        try {
            SSLContext instance = SSLContext.getInstance("TLSv1.2");
            instance.init(null, new TrustManager[]{new DummyTrustAllManager()}, null);
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            Socket socket = new Socket();
            socket.connect(inetSocketAddress, (int) TimeUnit.SECONDS.toMillis(5));
            this.mSocket = (SSLSocket) socketFactory.createSocket(socket, inetSocketAddress.getHostString(), inetSocketAddress.getPort(), true);
            this.mSocket.startHandshake();
            this.mAuthContext = new AuthContext(this.mSocket.getSession().getPeerCertificates()[0], googleCastCA, CastProtocol.HashAlgorithm.SHA256);
            try {
                this.mMessageThread = new Thread(new MessageDispatcher(this.mSocket.getInputStream()));
                if (looper == null) {
                    this.mEventThread = new HandlerThread("cast-device-events");
                    this.mEventThread.start();
                    looper = this.mEventThread.getLooper();
                } else {
                    this.mEventThread = null;
                }
                this.mEventHandler = new Handler(looper);
                try {
                    this.mHeartbeatSession = new HeartbeatSession(this);
                    this.mMessageThread.start();
                    this.mAuthSession = new AuthSession(this, this.mAuthContext);
                    try {
                        AuthContext.AuthResult authResult = this.mAuthSession.sendAuthChallenge().get(5, TimeUnit.SECONDS);
                        if (AuthContext.AuthResult.isError(authResult)) {
                            this.mSocket.close();
                            throw new AuthException(authResult);
                        } else if (!isEnabled) {
                            lambda$sendPing$0$CastV2Device();
                        }
                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
                        e.printStackTrace();
                        this.mSocket.close();
                        throw new AuthException(AuthContext.AuthResult.AuthErrorGeneric);
                    }
                } catch (IOException e2) {
                    this.mSocket.close();
                    throw e2;
                }
            } catch (IOException e3) {
                this.mSocket.close();
                throw e3;
            }
        } catch (KeyManagementException | NoSuchAlgorithmException e4) {
            throw new RuntimeException("Failed to setup SSL context", e4);
        }
    }

    public CastV2Device(InetSocketAddress inetSocketAddress, GoogleCastCA googleCastCA) throws IOException, AuthException {
        this(inetSocketAddress, googleCastCA, null);
    }

    public InetSocketAddress getAddress() {
        return (InetSocketAddress) this.mSocket.getRemoteSocketAddress();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (isClosed()) {
            Log.d(TAG, "Attempted to close closed device");
            return;
        }
        Log.i(TAG, "Disconnecting from Google Cast device at " + getAddress());
        for (CastV2Session castV2Session : this.mSessions.values()) {
            castV2Session.close();
        }
        try {
            this.mSocket.close();
        } finally {
            HandlerThread handlerThread = this.mEventThread;
            if (handlerThread != null) {
                handlerThread.quitSafely();
            }
        }
    }

    public boolean isClosed() {
        return this.mSocket.isClosed();
    }

    /* access modifiers changed from: package-private */
    public synchronized void sendCastMessage(CastProtocol.CastMessage castMessage) throws IOException {
        OutputStream outputStream = this.mSocket.getOutputStream();
        outputStream.write(Util.intToBytes(castMessage.getSerializedSize()));
        castMessage.writeTo(outputStream);
    }

    /* access modifiers changed from: private */
    /* renamed from: sendPing */
    public void lambda$sendPing$0$CastV2Device() {
        if (!this.mHeartbeatSession.checkAndPing()) {
            Log.e(TAG, "Peer failed to pong our ping");
            this.mCurrentPingFailures++;
            Log.e(TAG, String.format("Encountered %s failures out of %s max.", Integer.valueOf(this.mCurrentPingFailures), Integer.valueOf(this.mMaxPingFailuresToError)));
            if (!isClosed() && this.mCurrentPingFailures >= this.mMaxPingFailuresToError) {
                onPingFailed();
                try {
                    close();
                    return;
                } catch (IOException e) {
                    Log.e(TAG, "Failed to close device", e);
                    return;
                }
            }
        } else {
            this.mCurrentPingFailures = 0;
        }
        this.mEventHandler.postDelayed(new Runnable() {
            /* class com.oculus.vrcast.googlecast.net.$$Lambda$CastV2Device$tR4JhKDdQxMgqbEBad1MVFisZhs */

            public final void run() {
                CastV2Device.this.lambda$sendPing$0$CastV2Device();
            }
        }, (long) (this.mPingIntervalSec * 1000));
    }

    /* access modifiers changed from: private */
    public class MessageDispatcher implements Runnable {
        private final InputStream mInput;

        public MessageDispatcher(InputStream inputStream) {
            this.mInput = inputStream;
        }

        public void run() {
            while (true) {
                try {
                    CastProtocol.CastMessage readOneCastMessage = readOneCastMessage();
                    CastProtocol.CastMessage.ProtocolVersion protocolVersion = readOneCastMessage.getProtocolVersion();
                    if (protocolVersion != CastProtocol.CastMessage.ProtocolVersion.CASTV2_1_0) {
                        Log.i(CastV2Device.TAG, "Dropping message with unknown version " + protocolVersion);
                    }
                    String destinationId = readOneCastMessage.getDestinationId();
                    if (destinationId.equals("*")) {
                        for (CastV2Session castV2Session : CastV2Device.this.mSessions.values()) {
                            dispatchMessage(readOneCastMessage, castV2Session, false);
                        }
                    } else {
                        CastV2Session castV2Session2 = CastV2Device.this.mSessions.get(destinationId);
                        if (castV2Session2 == null) {
                            Log.w(CastV2Device.TAG, "Dropping message for nonexistent destId " + destinationId + "; srcId=" + readOneCastMessage.getSourceId() + ", ns=" + readOneCastMessage.getNamespace());
                        } else {
                            dispatchMessage(readOneCastMessage, castV2Session2, true);
                        }
                    }
                } catch (InvalidProtocolBufferException e) {
                    Log.e(CastV2Device.TAG, "Failed to parse protobuf from peer", e);
                } catch (IOException unused) {
                    Log.d(CastV2Device.TAG, "Socket is closed; stopping message dispatch thread");
                    return;
                }
            }
        }

        private void maybeLogUnhandled(boolean z, boolean z2, String str) {
            if (!z && z2) {
                Log.d(CastV2Device.TAG, "Targeted message with namespace " + str + " has Session but was not handled");
            }
        }

        private void dispatchMessage(CastProtocol.CastMessage castMessage, CastV2Session castV2Session, boolean z) {
            String namespace = castMessage.getNamespace();
            if (castMessage.getPayloadType() == CastProtocol.CastMessage.PayloadType.STRING) {
                CastV2Device.this.mEventHandler.post(new Runnable(castV2Session, namespace, castMessage, z) {
                    /* class com.oculus.vrcast.googlecast.net.$$Lambda$CastV2Device$MessageDispatcher$szLhhhbka8F77_8BKfCb7Yqt1qg */
                    private final /* synthetic */ CastV2Session f$1;
                    private final /* synthetic */ String f$2;
                    private final /* synthetic */ CastProtocol.CastMessage f$3;
                    private final /* synthetic */ boolean f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void run() {
                        CastV2Device.MessageDispatcher.this.lambda$dispatchMessage$0$CastV2Device$MessageDispatcher(this.f$1, this.f$2, this.f$3, this.f$4);
                    }
                });
            } else {
                CastV2Device.this.mEventHandler.post(new Runnable(castV2Session, namespace, castMessage, z) {
                    /* class com.oculus.vrcast.googlecast.net.$$Lambda$CastV2Device$MessageDispatcher$TdVT5CyqIyQJsy7vXSze8iDD_rc */
                    private final /* synthetic */ CastV2Session f$1;
                    private final /* synthetic */ String f$2;
                    private final /* synthetic */ CastProtocol.CastMessage f$3;
                    private final /* synthetic */ boolean f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void run() {
                        CastV2Device.MessageDispatcher.this.lambda$dispatchMessage$1$CastV2Device$MessageDispatcher(this.f$1, this.f$2, this.f$3, this.f$4);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$dispatchMessage$0$CastV2Device$MessageDispatcher(CastV2Session castV2Session, String str, CastProtocol.CastMessage castMessage, boolean z) {
            maybeLogUnhandled(castV2Session.onMessagePriv(str, castMessage.getPayloadUtf8()), z, str);
        }

        public /* synthetic */ void lambda$dispatchMessage$1$CastV2Device$MessageDispatcher(CastV2Session castV2Session, String str, CastProtocol.CastMessage castMessage, boolean z) {
            maybeLogUnhandled(castV2Session.onMessage(str, castMessage.getPayloadBinary()), z, str);
        }

        private CastProtocol.CastMessage readOneCastMessage() throws IOException {
            byte[] bArr = new byte[4];
            Util.readExact(this.mInput, bArr, 4);
            int bytesToInt = Util.bytesToInt(bArr);
            if (bytesToInt < 0 || bytesToInt > CastV2Device.MAX_BODY_SIZE) {
                throw new IOException("Invalid message length");
            }
            byte[] bArr2 = new byte[bytesToInt];
            Util.readExact(this.mInput, bArr2, bytesToInt);
            return CastProtocol.CastMessage.parseFrom(bArr2);
        }
    }
}
