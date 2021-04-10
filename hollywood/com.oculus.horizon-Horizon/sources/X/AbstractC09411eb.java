package X;

import com.facebook.FacebookSdk;
import com.oculus.horizon.cast.CastWebSocket;
import com.oculus.horizon.cast.Message;
import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1eb  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09411eb {
    public final List<AnonymousClass1ec> continuousFrames = new LinkedList();
    public AnonymousClass1fE continuousOpCode = null;
    public final AnonymousClass1em handshakeRequest;
    public final AnonymousClass1eX handshakeResponse;
    public final InputStream in;
    public OutputStream out;
    public AnonymousClass1fb state = AnonymousClass1fb.UNCONNECTED;

    public static final synchronized void A01(AbstractC09411eb r6, AnonymousClass1ec r7) throws IOException {
        synchronized (r6) {
            OutputStream outputStream = r6.out;
            byte b = 0;
            if (r7.A01) {
                b = (byte) FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE;
            }
            outputStream.write((byte) (b | (r7.A00.getValue() & 15)));
            int length = r7.A03.length;
            r7.A04 = length;
            if (length <= 125) {
                int i = (byte) length;
                if (AnonymousClass1ec.A01(r7)) {
                    i |= FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE;
                }
                outputStream.write(i);
            } else if (length <= 65535) {
                int i2 = 126;
                if (AnonymousClass1ec.A01(r7)) {
                    i2 = 254;
                }
                outputStream.write(i2);
                outputStream.write(r7.A04 >>> 8);
                outputStream.write(r7.A04);
            } else {
                boolean A01 = AnonymousClass1ec.A01(r7);
                int i3 = Hpack.PREFIX_7_BITS;
                if (A01) {
                    i3 = 255;
                }
                outputStream.write(i3);
                outputStream.write((r7.A04 >>> 56) & 0);
                outputStream.write((r7.A04 >>> 48) & 0);
                outputStream.write((r7.A04 >>> 40) & 0);
                outputStream.write((r7.A04 >>> 32) & 0);
                outputStream.write(r7.A04 >>> 24);
                outputStream.write(r7.A04 >>> 16);
                outputStream.write(r7.A04 >>> 8);
                outputStream.write(r7.A04);
            }
            if (AnonymousClass1ec.A01(r7)) {
                outputStream.write(r7.A02);
                for (int i4 = 0; i4 < r7.A04; i4++) {
                    outputStream.write(r7.A03[i4] ^ r7.A02[i4 % 4]);
                }
            } else {
                outputStream.write(r7.A03);
            }
            outputStream.flush();
        }
    }

    public final void A02(AnonymousClass1ec r8) {
        Message message;
        CastWebSocket castWebSocket = (CastWebSocket) this;
        String A02 = r8.A02();
        try {
            message = Message.A00(new JSONObject(A02));
        } catch (JSONException e) {
            AnonymousClass0NO.A0E(Message.TAG, "Failed to parse message {%s} ", A02, e);
            message = Message.INVALID_MESSAGE;
        }
        switch (message.mType.ordinal()) {
            case 0:
                castWebSocket.mListener.A6x(message.mSessionId, message.mSpec, message.mEnableDataChannel, message.mEnableSendAppInfo);
                return;
            case 1:
            default:
                AnonymousClass0NO.A09(castWebSocket.TAG, "unknown message type");
                return;
            case 2:
                castWebSocket.mListener.A5h(message.mSessionId, message.mData);
                return;
            case 3:
                castWebSocket.mListener.A71(message.mSessionId);
                return;
            case 4:
                if (message.mErrorCode != Message.ErrorCode.UNKNOWN_MESSAGE) {
                    castWebSocket.mListener.A61(message.mSessionId, message.mData);
                    return;
                }
                return;
        }
    }

    public static void A00(AbstractC09411eb r4, AnonymousClass1fR r5, String str, boolean z) {
        if (r4.state != AnonymousClass1fb.CLOSED) {
            InputStream inputStream = r4.in;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    AnonymousClass1eW.LOG.log(Level.FINE, "close failed", (Throwable) e);
                }
            }
            OutputStream outputStream = r4.out;
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e2) {
                    AnonymousClass1eW.LOG.log(Level.FINE, "close failed", (Throwable) e2);
                }
            }
            r4.state = AnonymousClass1fb.CLOSED;
            ((CastWebSocket) r4).mListener.onClose();
        }
    }

    public AbstractC09411eb(AnonymousClass1em r5) {
        AnonymousClass1ed r3 = new AnonymousClass1ed(this, AnonymousClass1Xz.SWITCH_PROTOCOL);
        this.handshakeResponse = r3;
        this.handshakeRequest = r5;
        this.in = r5.A3b();
        List<String> list = r3.A08.A00;
        list.add(AnonymousClass1eW.HEADER_UPGRADE);
        list.add(AnonymousClass1eW.HEADER_UPGRADE_VALUE);
        List<String> list2 = this.handshakeResponse.A08.A00;
        list2.add(AnonymousClass1eW.HEADER_CONNECTION);
        list2.add(AnonymousClass1eW.HEADER_CONNECTION_VALUE);
    }
}
