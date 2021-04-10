package X;

import com.facebook.GraphRequest;
import com.oculus.aidl.OVRServiceInterface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* renamed from: X.1vC  reason: invalid class name */
public class AnonymousClass1vC extends SSLSocket implements AnonymousClass1mC {
    public int A00;
    public AnonymousClass1mD A01;
    public AnonymousClass1mB A02;
    public AnonymousClass1vF A03;
    public AbstractC10791ve A04;
    public AnonymousClass1ur A05;
    public AnonymousClass1ur A06;
    public AnonymousClass1vJ A07;
    public InputStream A08;
    public OutputStream A09;
    public String A0A;
    public long A0B;
    public Set<HandshakeCompletedListener> A0C = new HashSet();
    public boolean A0D = false;
    public boolean A0E = false;
    public boolean A0F = false;

    public static String A01(byte b) {
        if (b == 0) {
            return "close_notify";
        }
        if (b == 10) {
            return "unexpected_message";
        }
        if (b == 20) {
            return "bad_record_mac";
        }
        if (b == 22) {
            return "record_overflow";
        }
        if (b == 40) {
            return "handshake_failure";
        }
        if (b == 80) {
            return "internal_error";
        }
        if (b == 86) {
            return "inappropriate_fallback";
        }
        if (b == 90) {
            return "user_cancelled";
        }
        if (b == 120) {
            return "no_application_protocol";
        }
        if (b == 70) {
            return "protocol_version";
        }
        if (b == 71) {
            return "insufficient_security";
        }
        if (b == 109) {
            return "missing_extension";
        }
        if (b == 110) {
            return "unsupported_version";
        }
        if (b == 112) {
            return "unrecognized_name";
        }
        if (b == 113) {
            return "bad_certificate_status_response";
        }
        if (b == 115) {
            return "unknown_psk_identity";
        }
        if (b == 116) {
            return "certificate_required";
        }
        switch (b) {
            case OVRServiceInterface.Stub.TRANSACTION_setRichPresence /*{ENCODED_INT: 42}*/:
                return "bad_certificate";
            case OVRServiceInterface.Stub.TRANSACTION_registerProcessToken /*{ENCODED_INT: 43}*/:
                return "unsupported_certificate";
            case OVRServiceInterface.Stub.TRANSACTION_getViewerPurchasesDurableCacheJSON /*{ENCODED_INT: 44}*/:
                return "certificate_revoked";
            case OVRServiceInterface.Stub.TRANSACTION_getCurrentMapUuid /*{ENCODED_INT: 45}*/:
                return "certificate_expired";
            case OVRServiceInterface.Stub.TRANSACTION_shareMap /*{ENCODED_INT: 46}*/:
                return "certificate_unknown";
            case OVRServiceInterface.Stub.TRANSACTION_requestMap /*{ENCODED_INT: 47}*/:
                return "illegal_parameter";
            case OVRServiceInterface.Stub.TRANSACTION_getDeviceScopedAccessToken /*{ENCODED_INT: 48}*/:
                return "unknown_ca";
            case 49:
                return "access_denied";
            case GraphRequest.MAXIMUM_BATCH_SIZE /*{ENCODED_INT: 50}*/:
                return "decode_error";
            case 51:
                return "decrypt_error";
            default:
                return "invalid description";
        }
    }

    private synchronized void A03() throws IOException {
        this.A0F = true;
        if (this.A0E) {
            this.A01.close();
            this.A02.close();
        }
        if (!(this instanceof AnonymousClass1mA)) {
            super.close();
            this.A08.close();
            this.A09.close();
        }
    }

    private synchronized void A04(byte b, byte b2, boolean z, SSLException sSLException) throws IOException {
        String str;
        String str2;
        if (z) {
            Throwable cause = sSLException.getCause();
            IOException iOException = sSLException;
            if (cause != null) {
                iOException = sSLException.getCause();
            }
            throw iOException;
        }
        if (!this.A0F) {
            Integer num = AnonymousClass007.A01;
            StringBuilder sb = new StringBuilder();
            sb.append("Sending Alert : type : ");
            if (b == 2) {
                str = "FATAL";
            } else {
                str = "WARNING";
            }
            sb.append(str);
            sb.append(" description : ");
            sb.append(A01(b2));
            sb.append("(");
            sb.append((int) b2);
            sb.append(") exception : ");
            if (sSLException == null) {
                str2 = "";
            } else {
                str2 = sSLException.toString();
            }
            sb.append(str2);
            String obj = sb.toString();
            String obj2 = Thread.currentThread().getStackTrace()[2].toString();
            PrintStream printStream = System.err;
            printStream.println(AnonymousClass006.A09(AnonymousClass1kD.A00(num), ": ", obj2, " : ", obj));
            if (sSLException != null) {
                sSLException.printStackTrace(printStream);
            }
            try {
                this.A03.A0L.A01((byte) 21, new byte[]{b, b2}, 0, 2);
            } catch (Exception e) {
                Integer num2 = AnonymousClass007.A0E;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Encountered exception. Nothing much can be done here. ");
                sb2.append(e);
                AnonymousClass1lH.A00(num2, sb2.toString());
            }
            A03();
        }
        if (b == 2) {
            String A052 = AnonymousClass006.A05("WATLS Exception\n", A00());
            Throwable th = sSLException;
            if (sSLException != null) {
                Throwable cause2 = sSLException.getCause();
                th = sSLException;
                if (cause2 != null) {
                    th = sSLException.getCause();
                }
            }
            throw new IOException(A052, th);
        }
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (!this.A0F) {
            if (this.A0E) {
                A04((byte) 1, (byte) 0, false, null);
            } else {
                A03();
            }
        }
    }

    public final boolean getEnableSessionCreation() {
        return true;
    }

    public final String[] getEnabledCipherSuites() {
        return new String[]{"TLS_AES_128_GCM_SHA256", "use default"};
    }

    public final String[] getEnabledProtocols() {
        return new String[]{"TLSv1.3", "TLSv1.2"};
    }

    public final String[] getSupportedCipherSuites() {
        return new String[]{"TLS_AES_128_GCM_SHA256", "use default"};
    }

    public final String[] getSupportedProtocols() {
        return new String[]{"TLSv1.3", "TLSv1.2"};
    }

    public final boolean getUseClientMode() {
        return true;
    }

    public final void setEnableSessionCreation(boolean z) {
    }

    public final void setUseClientMode(boolean z) {
    }

    @Override // javax.net.ssl.SSLSocket
    public final void startHandshake() throws IOException {
        try {
            AnonymousClass1lH.A00(AnonymousClass007.A0C, "Start handshake.");
            if (!this.A0E) {
                this.A0B = System.currentTimeMillis();
                this.A0E = true;
                AbstractC10791ve r3 = this.A04;
                this.A03.A0I = new AnonymousClass1mE();
                AnonymousClass1vF r7 = this.A03;
                r7.A0A = r3.A02();
                r7.A0S = "SHA-256";
                r7.A07 = 32;
                r7.A0J = new AnonymousClass1nA();
                r7.A0C = new AnonymousClass0XU();
                r7.A0B = AnonymousClass0XS.A00;
                r7.A0D = r3.A03();
                AnonymousClass0XX r1 = new AnonymousClass0XX();
                r7.A0E = r1;
                r1.A00 = r3.A09();
                r7.A0R = r3.A08();
                r7.A0G = r3.A04();
                r7.A0K = new AnonymousClass1vN(this.A08, r7.A0I);
                AnonymousClass1vF r2 = this.A03;
                r2.A0L = new AnonymousClass1v2(this.A09);
                r2.A0F = new AnonymousClass0XY();
                AnonymousClass1vF r22 = this.A03;
                r22.A05 = r3.A00();
                if (r3.A06() != null) {
                    throw null;
                }
                r22.A0T = this.A0A;
                r22.A0X = new HashMap();
                r22.A0P = this.A08;
                r22.A0Q = this.A09;
                r22.A0H = this.A01.A00;
                r22.A0M = r3.A07();
                r22.A0c = false;
                boolean A0B2 = r3.A0B();
                r22.A0e = A0B2;
                byte[] bArr = new byte[32];
                r22.A0m = bArr;
                if (A0B2) {
                    r22.A0F.A00.nextBytes(bArr);
                }
                AnonymousClass1vF r0 = this.A03;
                AnonymousClass1ur r72 = (AnonymousClass1ur) r0.A0M.getSession(AnonymousClass1ut.A07(r0.A0T, this.A00, "TLS_AES_128_GCM_SHA256"));
                if (r72 == null) {
                    AnonymousClass1vF r02 = this.A03;
                    r72 = new AnonymousClass1ur(r02.A0M, r02.A0T, this.A00, "TLS_AES_128_GCM_SHA256");
                }
                this.A05 = r72;
                AnonymousClass1vF r03 = this.A03;
                r03.A0N = r72;
                this.A06 = r72;
                byte[] bArr2 = new byte[32];
                r03.A0k = bArr2;
                r03.A0F.A00.nextBytes(bArr2);
                AnonymousClass0XU r73 = this.A03.A0C;
                try {
                    KeyPairGenerator instance = KeyPairGenerator.getInstance("EC");
                    instance.initialize(new ECGenParameterSpec("secp256r1"));
                    r73.A00 = instance.generateKeyPair();
                } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException unused) {
                }
                AnonymousClass1vF r74 = this.A03;
                AnonymousClass0XU r12 = r74.A0C;
                BigInteger affineX = ((ECPublicKey) r12.A00.getPublic()).getW().getAffineX();
                BigInteger affineY = ((ECPublicKey) r12.A00.getPublic()).getW().getAffineY();
                ByteBuffer allocate = ByteBuffer.allocate(65);
                allocate.put((byte) 4);
                allocate.put(AnonymousClass0XU.A00(affineX));
                allocate.put(AnonymousClass0XU.A00(affineY));
                r74.A0j = allocate.array();
                AnonymousClass1vF r13 = this.A03;
                r13.A0i = r13.A0C.A00.getPrivate().getEncoded();
                AnonymousClass1vF r23 = this.A03;
                r23.A0O = new C10771vc(r23.A0S);
                AnonymousClass1vF r24 = this.A03;
                r24.A0f = false;
                r24.A0a = false;
                r24.A0b = r3.A0A();
                r24.A0U = new ArrayList();
                r24.A0V = new ArrayList();
                r24.A09 = r3.A01();
                byte[] A002 = AnonymousClass1uv.A00((byte) 1, A06(r24));
                this.A03.A0L.A01((byte) 22, A002, 0, A002.length);
                AnonymousClass1vF r25 = this.A03;
                AnonymousClass1vF r04 = r25;
                if (!r25.A0b || r25.A0N.A01 == null) {
                    this.A07.A00(new AnonymousClass1w3(A002));
                    A02();
                    return;
                }
                if (r25.A0e) {
                    r25.A0L.A01((byte) 20, new byte[]{1}, 0, 1);
                    r04 = this.A03;
                    r04.A0f = true;
                }
                r04.A03 = true;
                this.A07.A00(new AnonymousClass1w2(A002));
            }
        } catch (IOException e) {
            throw e;
        } catch (AnonymousClass1v5 e2) {
            A04((byte) 2, e2.description, e2.errorTransient, e2.ex);
        } catch (Exception e3) {
            e = e3;
            String message = e.getMessage();
            if (e.getCause() != null) {
                e = e.getCause();
            }
            A04((byte) 2, (byte) 80, false, new SSLException(message, e));
        }
    }

    private String A00() {
        StringBuilder sb = new StringBuilder("host=");
        AnonymousClass1vF r2 = this.A03;
        sb.append(r2.A0T);
        sb.append(" hrr=");
        sb.append(r2.A0d);
        sb.append(" r=");
        sb.append(r2.A0h);
        sb.append(" ed=");
        sb.append(r2.A0a);
        sb.append(" eda=");
        sb.append(r2.A0g);
        sb.append(" s=");
        sb.append(this.A07.A00.A00.A03);
        return sb.toString();
    }

    private final void A02() throws IOException, AnonymousClass1v5 {
        AnonymousClass1w6 A002;
        while (!this.A07.A00.A00.equals(AnonymousClass1vY.A08)) {
            AnonymousClass1vI r2 = this.A03.A0K;
            synchronized (r2) {
                A002 = r2.A00();
            }
            if (!(A002 instanceof AnonymousClass1w7)) {
                if (A002 instanceof AnonymousClass1w5) {
                    A05(A002);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                this.A07.A00(A002);
                if (A002 instanceof C10961vv) {
                    byte[] A003 = AnonymousClass1uv.A00((byte) 1, A06(this.A03));
                    this.A03.A0L.A01((byte) 22, A003, 0, A003.length);
                    this.A03.A0O.A00(A003);
                }
            }
        }
        if (!this.A03.A0c) {
            A04((byte) 2, (byte) 116, false, new SSLException("Server must either choose a PSK or send certificates."));
        }
        if (this.A03.A0g) {
            byte[] A004 = AnonymousClass1uv.A00((byte) 5, new byte[0]);
            this.A03.A0L.A01((byte) 22, A004, 0, A004.length);
            this.A03.A0O.A00(A004);
        }
        AnonymousClass1vF r1 = this.A03;
        if (r1.A0e && !r1.A0f) {
            r1.A0L.A01((byte) 20, new byte[]{1}, 0, 1);
        }
        AnonymousClass1vF r0 = this.A03;
        AnonymousClass0XR r6 = new AnonymousClass0XR();
        r6.A01(r0.A0X.get("client_hs_key"), this.A03.A0X.get("client_hs_iv"));
        AnonymousClass1vF r22 = this.A03;
        r22.A0L = new AnonymousClass1vM(r22.A0Q, r6);
        if (r22.A0Z) {
            byte[] A005 = AnonymousClass1uv.A00((byte) 11, new byte[4]);
            this.A03.A0L.A01((byte) 22, A005, 0, A005.length);
            this.A03.A0O.A00(A005);
        }
        AnonymousClass1vF r62 = this.A03;
        if (r62 != null) {
            byte[] A006 = AnonymousClass1uv.A00((byte) 20, AnonymousClass1ut.A09(r62.A0X.get("client_finished"), r62.A0O.A01(), r62.A0S));
            this.A03.A0L.A01((byte) 22, A006, 0, A006.length);
            this.A07.A00(new AnonymousClass1w4(A006));
            long currentTimeMillis = System.currentTimeMillis();
            this.A0D = true;
            Integer num = AnonymousClass007.A0C;
            StringBuilder sb = new StringBuilder("Handshake complete : session_resumed ");
            AnonymousClass1vF r12 = this.A03;
            sb.append(r12.A0h);
            sb.append(" early_data_sent ");
            sb.append(r12.A0a);
            sb.append(" early_data_accepted ");
            sb.append(r12.A0g);
            sb.append(" client_cert_requested ");
            sb.append(r12.A0Z);
            sb.append(" time_ms ");
            sb.append(currentTimeMillis - this.A0B);
            AnonymousClass1lH.A00(num, sb.toString());
            HandshakeCompletedEvent handshakeCompletedEvent = new HandshakeCompletedEvent(this, this.A06);
            for (HandshakeCompletedListener handshakeCompletedListener : this.A0C) {
                handshakeCompletedListener.handshakeCompleted(handshakeCompletedEvent);
            }
            return;
        }
        throw new AnonymousClass1v5((byte) 80, new SSLException("Illegal argument. Context cannot be null."));
    }

    private void A05(AnonymousClass1w6 r7) throws IOException {
        byte[] bArr = (byte[]) r7.A00;
        Integer num = AnonymousClass007.A01;
        StringBuilder sb = new StringBuilder("Received Alert: Level ");
        sb.append((int) bArr[0]);
        sb.append(" Description ");
        byte b = bArr[1];
        sb.append(A01(b));
        sb.append("(");
        sb.append((int) b);
        sb.append(")");
        AnonymousClass1lH.A00(num, sb.toString());
        A03();
        byte b2 = bArr[1];
        if (b2 == 0 || b2 == 50) {
            throw new IOException(new SSLException(AnonymousClass006.A01("Received alert ", b2)));
        }
        throw new IOException(AnonymousClass006.A05("WATLS Exception\n", A00()), new SSLException(AnonymousClass006.A01("Received alert ", b2)));
    }

    public static byte[] A06(AnonymousClass1vF r12) throws AnonymousClass1v5 {
        String str;
        ByteBuffer allocate;
        long currentTimeMillis;
        short s;
        byte[] bArr;
        if (r12 != null) {
            byte[] bArr2 = r12.A0k;
            if (bArr2 == null || bArr2.length != 32) {
                str = "Client random is not correctly initialized.";
            } else if (r12.A0m != null) {
                AnonymousClass1vA r3 = new AnonymousClass1vA();
                try {
                    String str2 = r12.A0R;
                    if (str2 != null && !str2.isEmpty()) {
                        byte[] bytes = str2.getBytes("UTF-8");
                        int length = bytes.length;
                        ByteBuffer allocate2 = ByteBuffer.allocate(length + 3);
                        allocate2.put(AnonymousClass1ut.A06(length + 1));
                        allocate2.put((byte) length);
                        allocate2.put(bytes);
                        r3.A01(new C10851vk(16, allocate2.array()));
                    }
                    ByteBuffer allocate3 = ByteBuffer.allocate(4);
                    allocate3.putShort(2);
                    allocate3.putShort(1027);
                    r3.A01(new C10851vk(13, allocate3.array()));
                    ByteBuffer allocate4 = ByteBuffer.allocate(4);
                    allocate4.putShort(2);
                    allocate4.putShort(23);
                    r3.A01(new C10851vk(10, allocate4.array()));
                    ByteBuffer allocate5 = ByteBuffer.allocate(2);
                    allocate5.put((byte) 1);
                    allocate5.put(r12.A05);
                    r3.A01(new C10851vk(45, allocate5.array()));
                    ByteBuffer allocate6 = ByteBuffer.allocate(5);
                    allocate6.put((byte) 4);
                    allocate6.putShort(772);
                    allocate6.putShort(-1254);
                    r3.A01(new C10851vk(43, allocate6.array()));
                    ByteBuffer allocate7 = ByteBuffer.allocate(4);
                    allocate7.putShort(2);
                    allocate7.putShort(1027);
                    r3.A01(new C10851vk(50, allocate7.array()));
                    if (r12.A0b && r12.A0N.A01 != null && !r12.A0d) {
                        r3.A01(new C10851vk(42, new byte[0]));
                    }
                    byte[] bytes2 = r12.A0T.getBytes("UTF-8");
                    int length2 = bytes2.length;
                    ByteBuffer allocate8 = ByteBuffer.allocate(length2 + 5);
                    allocate8.put(AnonymousClass1ut.A06(length2 + 3));
                    allocate8.put(AnonymousClass1ut.A05(length2));
                    allocate8.put(bytes2);
                    r3.A01(new C10851vk(0, allocate8.array()));
                    if (r12.A0d && (bArr = r12.A0l) != null) {
                        ByteBuffer allocate9 = ByteBuffer.allocate(bArr.length + 2);
                        allocate9.put(AnonymousClass1ut.A06(bArr.length));
                        allocate9.put(bArr);
                        r3.A01(new C10851vk(44, allocate9.array()));
                    }
                    if (!r12.A0d || (s = r12.A0Y) == 23) {
                        ByteBuffer allocate10 = ByteBuffer.allocate(71);
                        allocate10.put(AnonymousClass1ut.A06(69));
                        allocate10.putShort(23);
                        allocate10.put(AnonymousClass1ut.A06(65));
                        allocate10.put(r12.A0j);
                        r3.A01(new C10851vk(51, allocate10.array()));
                        ByteBuffer allocate11 = ByteBuffer.allocate(r3.A00);
                        Iterator<C10851vk> it = r3.A01.iterator();
                        while (it.hasNext()) {
                            C10851vk next = it.next();
                            ByteBuffer allocate12 = ByteBuffer.allocate(next.A01.length + 4);
                            allocate12.putShort(next.A00);
                            allocate12.put(AnonymousClass1ut.A06(next.A01.length));
                            allocate12.put(next.A01);
                            allocate11.put(allocate12.array());
                        }
                        byte[] array = allocate11.array();
                        C10711us r0 = r12.A0N.A01;
                        if (r0 == null) {
                            allocate = ByteBuffer.allocate(0);
                        } else {
                            allocate = ByteBuffer.allocate(r0.ticket.length + 6 + 6 + r12.A07 + 1 + 2);
                            byte[] bArr3 = r12.A0N.A01.ticket;
                            allocate.putShort(41);
                            allocate.put(AnonymousClass1ut.A06(allocate.capacity() - 4));
                            int length3 = bArr3.length;
                            allocate.put(AnonymousClass1ut.A06(length3 + 6));
                            allocate.put(AnonymousClass1ut.A06(length3));
                            allocate.put(bArr3);
                            C10711us r7 = r12.A0N.A01;
                            if (r7.useTestTime) {
                                currentTimeMillis = 3600000;
                            } else {
                                currentTimeMillis = System.currentTimeMillis();
                            }
                            long j = currentTimeMillis - r7.ticketIssuedTime;
                            if (j < 0) {
                                j = 0;
                            }
                            long j2 = (j + r7.ticketAgeAdd) % 4294967296L;
                            if (j2 < 0) {
                                j2 += 4294967296L;
                            }
                            if (j2 < 0 || j2 >= 4294967296L) {
                                StringBuilder sb = new StringBuilder("Invalid argument. The supplied long value = ");
                                sb.append(j2);
                                sb.append(" does not  fit in 4 bytes.");
                                throw new AnonymousClass1v5((byte) 80, new SSLException(sb.toString()));
                            }
                            allocate.put(new byte[]{(byte) ((int) ((j2 >> 24) & 255)), (byte) ((int) ((j2 >> 16) & 255)), (byte) ((int) ((j2 >> 8) & 255)), (byte) ((int) (j2 & 255))});
                        }
                        int length4 = array.length + allocate.capacity();
                        ByteBuffer allocate13 = ByteBuffer.allocate(r12.A0m.length + 35 + 2 + 2 + 1 + 1 + 2 + length4);
                        allocate13.putShort(771);
                        allocate13.put(r12.A0k);
                        allocate13.put((byte) r12.A0m.length);
                        allocate13.put(r12.A0m);
                        allocate13.putShort(2);
                        allocate13.putShort(4865);
                        allocate13.put((byte) 1);
                        allocate13.put((byte) 0);
                        allocate13.put(AnonymousClass1ut.A06(length4));
                        allocate13.put(array);
                        if (r12.A0N.A01 != null) {
                            try {
                                MessageDigest messageDigest = (MessageDigest) r12.A0O.A00.clone();
                                byte[] copyOfRange = Arrays.copyOfRange(allocate13.array(), 0, allocate13.position());
                                byte[] copyOfRange2 = Arrays.copyOfRange(allocate.array(), 0, allocate.position());
                                messageDigest.update((byte) 1);
                                messageDigest.update(AnonymousClass1ut.A05(allocate13.capacity()));
                                messageDigest.update(copyOfRange);
                                messageDigest.update(copyOfRange2);
                                byte[] digest = messageDigest.digest();
                                int i = r12.A07 + 1;
                                ByteBuffer allocate14 = ByteBuffer.allocate(i + 2);
                                allocate14.put(AnonymousClass1ut.A06(i));
                                byte[] A092 = AnonymousClass1ut.A09(r12.A0J.A01(r12.A0J.A01(r12.A0J.A00(new byte[r12.A07], r12.A0N.A01.pskVal), AnonymousClass1ut.A08("res binder", MessageDigest.getInstance(r12.A0S).digest(), r12.A07), r12.A07), AnonymousClass1ut.A08("finished", new byte[0], r12.A07), r12.A07), digest, r12.A0S);
                                allocate14.put((byte) A092.length);
                                allocate14.put(A092);
                                allocate.put(allocate14.array());
                                allocate13.put(allocate.array());
                            } catch (CloneNotSupportedException e) {
                                throw new AnonymousClass1v5((byte) 80, new SSLException(e));
                            }
                        }
                        return allocate13.array();
                    }
                    str = AnonymousClass006.A03("Must use key group sent by HelloRetryRequest: ", s, " client key group: ", 23);
                } catch (UnsupportedEncodingException e2) {
                    throw new AnonymousClass1v5((byte) 80, new SSLException(e2));
                }
            } else {
                str = "Legacy session id is not correctly initialized.";
            }
        } else {
            str = "Illegal argument. Context cannot be null.";
        }
        throw new AnonymousClass1v5((byte) 80, new SSLException(str));
    }

    public final void A07() throws IOException {
        if (!(this instanceof AnonymousClass1mA)) {
            this.A08 = super.getInputStream();
            this.A09 = super.getOutputStream();
            return;
        }
        AnonymousClass1mA r2 = (AnonymousClass1mA) this;
        Socket socket = r2.A00;
        r2.A08 = socket.getInputStream();
        r2.A09 = socket.getOutputStream();
    }

    public final byte[] A08(byte[] bArr) throws SSLException {
        String str;
        if (!this.A0D) {
            str = "TLS handshake is not yet complete!";
        } else if (!"EXPORTER-Token-Binding".isEmpty()) {
            try {
                byte[] bArr2 = this.A03.A0X.get("exporter_master_secret");
                if (bArr2 != null) {
                    MessageDigest instance = MessageDigest.getInstance(this.A03.A0S);
                    instance.update(new byte[0]);
                    byte[] digest = instance.digest();
                    int length = digest.length;
                    byte[] A012 = this.A03.A0J.A01(bArr2, AnonymousClass1ut.A08("EXPORTER-Token-Binding", digest, length), length);
                    instance.reset();
                    instance.update(bArr);
                    return this.A03.A0J.A01(A012, AnonymousClass1ut.A08("exporter", instance.digest(), 32), 32);
                }
                throw new SSLException("Cannot export keying material until TLS session initialized");
            } catch (AnonymousClass1v5 e) {
                throw e.ex;
            } catch (NoSuchAlgorithmException e2) {
                throw new SSLException(AnonymousClass006.A05("Unable to load hashing algorithm:  ", this.A03.A0S), e2);
            }
        } else {
            str = "Exporter label cannot be null or empty!";
        }
        throw new SSLException(str);
    }

    @Override // X.AnonymousClass1mC
    public final void A7q() throws IOException {
        AnonymousClass1w6 A002;
        AnonymousClass1w6 r6 = null;
        try {
            if (this.A0E && !this.A0D) {
                AnonymousClass1vF r1 = this.A03;
                if (r1.A0a) {
                    r1.A03 = false;
                    this.A07.A00(new AnonymousClass1w1(true));
                    A02();
                    AnonymousClass1vF r12 = this.A03;
                    if (!r12.A0g) {
                        long j = 0;
                        for (C10871vm r8 : r12.A0U) {
                            j += (long) r8.A00;
                            this.A07.A00(new C11001vz(r8));
                        }
                        AnonymousClass1lH.A00(AnonymousClass007.A01, AnonymousClass006.A04("Replayed early data len = ", j));
                    }
                    AnonymousClass1vF r0 = this.A03;
                    r0.A0U = null;
                    long j2 = 0;
                    for (C10871vm r82 : r0.A0V) {
                        j2 += (long) r82.A00;
                        this.A07.A00(new C11001vz(r82));
                    }
                    if (j2 > 0) {
                        AnonymousClass1lH.A00(AnonymousClass007.A01, AnonymousClass006.A04("Spillover early data len = ", j2));
                    }
                    this.A03.A0V = null;
                }
            }
        } catch (IOException e) {
            throw e;
        } catch (AnonymousClass1v5 e2) {
            A04((byte) 2, e2.description, e2.errorTransient, e2.ex);
        } catch (Exception e3) {
            e = e3;
            if (e.getCause() != null) {
                e = e.getCause();
            }
            A04((byte) 2, (byte) 80, false, new SSLException(e));
        }
        do {
            try {
                AnonymousClass1vI r2 = this.A03.A0K;
                synchronized (r2) {
                    A002 = r2.A00();
                }
                r6 = A002;
                if (!(A002 instanceof AnonymousClass1w7)) {
                    if (A002 instanceof AnonymousClass1w5) {
                        break;
                    }
                    this.A07.A00(A002);
                }
            } catch (AnonymousClass1v5 e4) {
                A04((byte) 2, e4.description, e4.errorTransient, e4.ex);
            } catch (Exception e5) {
                e = e5;
                if (e.getCause() != null) {
                    e = e.getCause();
                }
                A04((byte) 2, (byte) 80, false, new SSLException(e));
            }
            if (!AnonymousClass1vY.A00.getClass().isInstance(this.A07.A00.A00)) {
                break;
            }
        } while (!(r6 instanceof AnonymousClass1w0));
        if (r6 instanceof AnonymousClass1w5) {
            A05(r6);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AnonymousClass1mC
    public final void AAA(byte[] bArr, int i, int i2) throws IOException {
        try {
            C10871vm r6 = new C10871vm(bArr, i, i2);
            if (this.A0E && !this.A0D) {
                AnonymousClass1vF r9 = this.A03;
                if (r9.A03) {
                    long j = (long) i2;
                    long j2 = r9.A0N.A01.maxEarlyDataSize;
                    if (r9.A00 + j > j2) {
                        r9.A00 = j2;
                        long j3 = r9.A01 + j;
                        long j4 = r9.A09;
                        if (j3 <= j4) {
                            r9.A01 = j3;
                            r9.A0V.add(r6);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Client request exceeded the max spillover limit ");
                            sb.append(j3);
                            sb.append(" > ");
                            sb.append(j4);
                            throw new AnonymousClass1v5((byte) 80, new SSLException(sb.toString()));
                        }
                    } else {
                        this.A07.A00(new C10981vx(r6));
                        this.A03.A0U.add(r6);
                        this.A03.A00 += j;
                    }
                    this.A03.A0a = true;
                    return;
                }
            }
            this.A07.A00(new C11001vz(r6));
        } catch (AnonymousClass1v5 e) {
            A04((byte) 2, e.description, e.errorTransient, e.ex);
        } catch (Exception e2) {
            e = e2;
            if (e.getCause() != null) {
                e = e.getCause();
            }
            A04((byte) 2, (byte) 80, false, new SSLException(e));
        }
    }

    public final void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.A0C.add(handshakeCompletedListener);
    }

    public final SocketChannel getChannel() {
        throw new AssertionError("Channels are not supported by WtSocket.");
    }

    @Override // java.net.Socket
    public final InputStream getInputStream() throws IOException {
        AnonymousClass1mD r0 = this.A01;
        if (r0 != null) {
            return r0;
        }
        throw new IOException("Input stream is closed.");
    }

    public final boolean getNeedClientAuth() {
        return this.A04.getNeedClientAuth();
    }

    @Override // java.net.Socket
    public final OutputStream getOutputStream() throws IOException {
        AnonymousClass1mB r0 = this.A02;
        if (r0 != null) {
            return r0;
        }
        throw new IOException("Output stream is closed.");
    }

    public final boolean getWantClientAuth() {
        return this.A04.getWantClientAuth();
    }

    public final void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.A0C.remove(handshakeCompletedListener);
    }

    public final void setEnabledCipherSuites(String[] strArr) {
        this.A04.setCipherSuites(strArr);
    }

    public final void setEnabledProtocols(String[] strArr) {
        this.A04.setProtocols(strArr);
    }

    public final void setNeedClientAuth(boolean z) {
        this.A04.setNeedClientAuth(z);
    }

    public final void setSSLParameters(SSLParameters sSLParameters) {
        if (sSLParameters instanceof AbstractC10791ve) {
            this.A04 = (AbstractC10791ve) sSLParameters;
        }
    }

    public final void setWantClientAuth(boolean z) {
        this.A04.setWantClientAuth(z);
    }

    public final SSLSession getHandshakeSession() {
        return this.A05;
    }

    public final SSLParameters getSSLParameters() {
        return this.A04;
    }

    public final SSLSession getSession() {
        return this.A06;
    }

    public boolean isClosed() {
        return this.A0F;
    }
}
