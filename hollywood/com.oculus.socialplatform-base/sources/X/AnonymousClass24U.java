package X;

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

/* renamed from: X.24U  reason: invalid class name */
public class AnonymousClass24U extends SSLSocket implements AnonymousClass1oF {
    public int A00;
    public AnonymousClass1oC A01;
    public AnonymousClass1oE A02;
    public AnonymousClass24X A03;
    public AbstractC147924w A04;
    public AnonymousClass1nn A05;
    public AnonymousClass1nn A06;
    public C147224p A07;
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
            case 42:
                return "bad_certificate";
            case 43:
                return "unsupported_certificate";
            case 44:
                return "certificate_revoked";
            case 45:
                return "certificate_expired";
            case 46:
                return "certificate_unknown";
            case 47:
                return "illegal_parameter";
            case 48:
                return "unknown_ca";
            case 49:
                return "access_denied";
            case 50:
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
        if (!(this instanceof C142222k)) {
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
            printStream.println(AnonymousClass006.A0C(AnonymousClass1Ot.A00(num), ": ", obj2, " : ", obj));
            if (sSLException != null) {
                sSLException.printStackTrace(printStream);
            }
            try {
                this.A03.A0L.A01((byte) 21, new byte[]{b, b2}, 0, 2);
            } catch (Exception e) {
                Integer num2 = AnonymousClass007.A05;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Encountered exception. Nothing much can be done here. ");
                sb2.append(e);
                AnonymousClass1Ou.A00(num2, sb2.toString());
            }
            A03();
        }
        if (b == 2) {
            String A072 = AnonymousClass006.A07("WATLS Exception\n", A00());
            Throwable th = sSLException;
            if (sSLException != null) {
                Throwable cause2 = sSLException.getCause();
                th = sSLException;
                if (cause2 != null) {
                    th = sSLException.getCause();
                }
            }
            throw new IOException(A072, th);
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

    public final String[] getEnabledProtocols() {
        return new String[]{"TLSv1.3", "TLSv1.2"};
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
            AnonymousClass1Ou.A00(AnonymousClass007.A03, "Start handshake.");
            if (!this.A0E) {
                this.A0B = System.currentTimeMillis();
                this.A0E = true;
                AbstractC147924w r2 = this.A04;
                this.A03.A0I = new AnonymousClass1oD();
                AnonymousClass24X r3 = this.A03;
                r3.A0A = C148425b.A00;
                r3.A0S = "SHA-256";
                r3.A07 = 32;
                r3.A0J = new AnonymousClass24J();
                r3.A0C = new C148124y();
                r3.A0B = C148725e.A00;
                r3.A0D = C148625d.A00;
                AnonymousClass25Y r1 = new AnonymousClass25Y();
                r3.A0E = r1;
                r1.A00 = ((AnonymousClass257) r2).A00;
                r3.A0R = "";
                r3.A0G = C148925m.A00;
                r3.A0K = new C146324d(this.A08, r3.A0I);
                AnonymousClass24X r22 = this.A03;
                r22.A0L = new AnonymousClass24M(this.A09);
                r22.A0F = new AnonymousClass259();
                AnonymousClass24X r23 = this.A03;
                r23.A05 = 1;
                r23.A0T = this.A0A;
                r23.A0X = new HashMap();
                r23.A0P = this.A08;
                r23.A0Q = this.A09;
                r23.A0H = this.A01.A00;
                r23.A0M = AnonymousClass1nm.A04;
                r23.A0c = false;
                r23.A0e = true;
                byte[] bArr = new byte[32];
                r23.A0m = bArr;
                r23.A0F.A00.nextBytes(bArr);
                AnonymousClass24X r0 = this.A03;
                AnonymousClass1nn r32 = (AnonymousClass1nn) r0.A0M.getSession(AnonymousClass24K.A07(r0.A0T, this.A00, "TLS_AES_128_GCM_SHA256"));
                if (r32 == null) {
                    AnonymousClass24X r02 = this.A03;
                    r32 = new AnonymousClass1nn(r02.A0M, r02.A0T, this.A00, "TLS_AES_128_GCM_SHA256");
                }
                this.A05 = r32;
                AnonymousClass24X r03 = this.A03;
                r03.A0N = r32;
                this.A06 = r32;
                byte[] bArr2 = new byte[32];
                r03.A0k = bArr2;
                r03.A0F.A00.nextBytes(bArr2);
                C148124y r33 = this.A03.A0C;
                try {
                    KeyPairGenerator instance = KeyPairGenerator.getInstance("EC");
                    instance.initialize(new ECGenParameterSpec("secp256r1"));
                    r33.A00 = instance.generateKeyPair();
                } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException unused) {
                }
                AnonymousClass24X r7 = this.A03;
                C148124y r12 = r7.A0C;
                BigInteger affineX = ((ECPublicKey) r12.A00.getPublic()).getW().getAffineX();
                BigInteger affineY = ((ECPublicKey) r12.A00.getPublic()).getW().getAffineY();
                ByteBuffer allocate = ByteBuffer.allocate(65);
                allocate.put((byte) 4);
                allocate.put(C148124y.A00(affineX));
                allocate.put(C148124y.A00(affineY));
                r7.A0j = allocate.array();
                AnonymousClass24X r13 = this.A03;
                r13.A0i = r13.A0C.A00.getPrivate().getEncoded();
                AnonymousClass24X r24 = this.A03;
                r24.A0O = new C147724u(r24.A0S);
                AnonymousClass24X r25 = this.A03;
                r25.A0f = false;
                r25.A0a = false;
                r25.A0b = false;
                r25.A0U = new ArrayList();
                r25.A0V = new ArrayList();
                r25.A09 = 4294967296L;
                byte[] A002 = AnonymousClass24L.A00((byte) 1, A06(r25));
                this.A03.A0L.A01((byte) 22, A002, 0, A002.length);
                AnonymousClass24X r26 = this.A03;
                AnonymousClass24X r04 = r26;
                if (!r26.A0b || r26.A0N.A01 == null) {
                    this.A07.A00(new AnonymousClass25S(A002));
                    A02();
                    return;
                }
                if (r26.A0e) {
                    r26.A0L.A01((byte) 20, new byte[]{1}, 0, 1);
                    r04 = this.A03;
                    r04.A0f = true;
                }
                r04.A03 = true;
                this.A07.A00(new AnonymousClass25R(A002));
            }
        } catch (IOException e) {
            throw e;
        } catch (AnonymousClass25A e2) {
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
        AnonymousClass24X r2 = this.A03;
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

    private final void A02() throws IOException, AnonymousClass25A {
        AnonymousClass25V A002;
        while (!this.A07.A00.A00.equals(C147424r.A08)) {
            AnonymousClass24Z r2 = this.A03.A0K;
            synchronized (r2) {
                A002 = r2.A00();
            }
            if (!(A002 instanceof AnonymousClass25W)) {
                if (A002 instanceof AnonymousClass25U) {
                    A05(A002);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                this.A07.A00(A002);
                if (A002 instanceof AnonymousClass25K) {
                    byte[] A003 = AnonymousClass24L.A00((byte) 1, A06(this.A03));
                    this.A03.A0L.A01((byte) 22, A003, 0, A003.length);
                    this.A03.A0O.A00(A003);
                }
            }
        }
        if (!this.A03.A0c) {
            A04((byte) 2, (byte) 116, false, new SSLException("Server must either choose a PSK or send certificates."));
        }
        if (this.A03.A0g) {
            byte[] A004 = AnonymousClass24L.A00((byte) 5, new byte[0]);
            this.A03.A0L.A01((byte) 22, A004, 0, A004.length);
            this.A03.A0O.A00(A004);
        }
        AnonymousClass24X r1 = this.A03;
        if (r1.A0e && !r1.A0f) {
            r1.A0L.A01((byte) 20, new byte[]{1}, 0, 1);
        }
        AnonymousClass24X r0 = this.A03;
        C146924m r6 = new C146924m();
        r6.A01(r0.A0X.get("client_hs_key"), this.A03.A0X.get("client_hs_iv"));
        AnonymousClass24X r22 = this.A03;
        r22.A0L = new C146224c(r22.A0Q, r6);
        if (r22.A0Z) {
            byte[] A005 = AnonymousClass24L.A00((byte) 11, new byte[4]);
            this.A03.A0L.A01((byte) 22, A005, 0, A005.length);
            this.A03.A0O.A00(A005);
        }
        AnonymousClass24X r62 = this.A03;
        if (r62 != null) {
            byte[] A006 = AnonymousClass24L.A00((byte) 20, AnonymousClass24K.A09(r62.A0X.get("client_finished"), r62.A0O.A01(), r62.A0S));
            this.A03.A0L.A01((byte) 22, A006, 0, A006.length);
            this.A07.A00(new AnonymousClass25T(A006));
            long currentTimeMillis = System.currentTimeMillis();
            this.A0D = true;
            Integer num = AnonymousClass007.A03;
            StringBuilder sb = new StringBuilder("Handshake complete : session_resumed ");
            AnonymousClass24X r12 = this.A03;
            sb.append(r12.A0h);
            sb.append(" early_data_sent ");
            sb.append(r12.A0a);
            sb.append(" early_data_accepted ");
            sb.append(r12.A0g);
            sb.append(" client_cert_requested ");
            sb.append(r12.A0Z);
            sb.append(" time_ms ");
            sb.append(currentTimeMillis - this.A0B);
            AnonymousClass1Ou.A00(num, sb.toString());
            HandshakeCompletedEvent handshakeCompletedEvent = new HandshakeCompletedEvent(this, this.A06);
            for (HandshakeCompletedListener handshakeCompletedListener : this.A0C) {
                handshakeCompletedListener.handshakeCompleted(handshakeCompletedEvent);
            }
            return;
        }
        throw new AnonymousClass25A((byte) 80, new SSLException("Illegal argument. Context cannot be null."));
    }

    private void A05(AnonymousClass25V r7) throws IOException {
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
        AnonymousClass1Ou.A00(num, sb.toString());
        A03();
        byte b2 = bArr[1];
        if (b2 == 0 || b2 == 50) {
            throw new IOException(new SSLException(AnonymousClass006.A03("Received alert ", b2)));
        }
        throw new IOException(AnonymousClass006.A07("WATLS Exception\n", A00()), new SSLException(AnonymousClass006.A03("Received alert ", b2)));
    }

    public static byte[] A06(AnonymousClass24X r12) throws AnonymousClass25A {
        ByteBuffer allocate;
        long currentTimeMillis;
        short s;
        byte[] bArr;
        if (r12 != null) {
            byte[] bArr2 = r12.A0k;
            if (bArr2 == null || bArr2.length != 32) {
                throw new AnonymousClass25A((byte) 80, new SSLException("Client random is not correctly initialized."));
            } else if (r12.A0m != null) {
                AnonymousClass24S r3 = new AnonymousClass24S();
                try {
                    String str = r12.A0R;
                    if (str != null && !str.isEmpty()) {
                        byte[] bytes = str.getBytes("UTF-8");
                        int length = bytes.length;
                        ByteBuffer allocate2 = ByteBuffer.allocate(length + 3);
                        allocate2.put(AnonymousClass24K.A06(length + 1));
                        allocate2.put((byte) length);
                        allocate2.put(bytes);
                        r3.A01(new AnonymousClass251(16, allocate2.array()));
                    }
                    ByteBuffer allocate3 = ByteBuffer.allocate(4);
                    allocate3.putShort(2);
                    allocate3.putShort(1027);
                    r3.A01(new AnonymousClass251(13, allocate3.array()));
                    ByteBuffer allocate4 = ByteBuffer.allocate(4);
                    allocate4.putShort(2);
                    allocate4.putShort(23);
                    r3.A01(new AnonymousClass251(10, allocate4.array()));
                    ByteBuffer allocate5 = ByteBuffer.allocate(2);
                    allocate5.put((byte) 1);
                    allocate5.put(r12.A05);
                    r3.A01(new AnonymousClass251(45, allocate5.array()));
                    ByteBuffer allocate6 = ByteBuffer.allocate(5);
                    allocate6.put((byte) 4);
                    allocate6.putShort(772);
                    allocate6.putShort(-1254);
                    r3.A01(new AnonymousClass251(43, allocate6.array()));
                    ByteBuffer allocate7 = ByteBuffer.allocate(4);
                    allocate7.putShort(2);
                    allocate7.putShort(1027);
                    r3.A01(new AnonymousClass251(50, allocate7.array()));
                    if (r12.A0b && r12.A0N.A01 != null && !r12.A0d) {
                        r3.A01(new AnonymousClass251(42, new byte[0]));
                    }
                    try {
                        byte[] bytes2 = r12.A0T.getBytes("UTF-8");
                        int length2 = bytes2.length;
                        ByteBuffer allocate8 = ByteBuffer.allocate(length2 + 5);
                        allocate8.put(AnonymousClass24K.A06(length2 + 3));
                        allocate8.put(AnonymousClass24K.A05(length2));
                        allocate8.put(bytes2);
                        r3.A01(new AnonymousClass251(0, allocate8.array()));
                        if (r12.A0d && (bArr = r12.A0l) != null) {
                            ByteBuffer allocate9 = ByteBuffer.allocate(bArr.length + 2);
                            allocate9.put(AnonymousClass24K.A06(bArr.length));
                            allocate9.put(bArr);
                            r3.A01(new AnonymousClass251(44, allocate9.array()));
                        }
                        if (!r12.A0d || (s = r12.A0Y) == 23) {
                            ByteBuffer allocate10 = ByteBuffer.allocate(71);
                            allocate10.put(AnonymousClass24K.A06(69));
                            allocate10.putShort(23);
                            allocate10.put(AnonymousClass24K.A06(65));
                            allocate10.put(r12.A0j);
                            r3.A01(new AnonymousClass251(51, allocate10.array()));
                            ByteBuffer allocate11 = ByteBuffer.allocate(r3.A00);
                            Iterator<AnonymousClass251> it = r3.A01.iterator();
                            while (it.hasNext()) {
                                AnonymousClass251 next = it.next();
                                ByteBuffer allocate12 = ByteBuffer.allocate(next.A01.length + 4);
                                allocate12.putShort(next.A00);
                                allocate12.put(AnonymousClass24K.A06(next.A01.length));
                                allocate12.put(next.A01);
                                allocate11.put(allocate12.array());
                            }
                            byte[] array = allocate11.array();
                            AnonymousClass1np r0 = r12.A0N.A01;
                            if (r0 == null) {
                                allocate = ByteBuffer.allocate(0);
                            } else {
                                allocate = ByteBuffer.allocate(r0.ticket.length + 6 + 6 + r12.A07 + 1 + 2);
                                byte[] bArr3 = r12.A0N.A01.ticket;
                                allocate.putShort(41);
                                allocate.put(AnonymousClass24K.A06(allocate.capacity() - 4));
                                int length3 = bArr3.length;
                                allocate.put(AnonymousClass24K.A06(length3 + 6));
                                allocate.put(AnonymousClass24K.A06(length3));
                                allocate.put(bArr3);
                                AnonymousClass1np r7 = r12.A0N.A01;
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
                                    throw new AnonymousClass25A((byte) 80, new SSLException(sb.toString()));
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
                            allocate13.put(AnonymousClass24K.A06(length4));
                            allocate13.put(array);
                            if (r12.A0N.A01 != null) {
                                try {
                                    MessageDigest messageDigest = (MessageDigest) r12.A0O.A00.clone();
                                    byte[] copyOfRange = Arrays.copyOfRange(allocate13.array(), 0, allocate13.position());
                                    byte[] copyOfRange2 = Arrays.copyOfRange(allocate.array(), 0, allocate.position());
                                    messageDigest.update((byte) 1);
                                    messageDigest.update(AnonymousClass24K.A05(allocate13.capacity()));
                                    messageDigest.update(copyOfRange);
                                    messageDigest.update(copyOfRange2);
                                    byte[] digest = messageDigest.digest();
                                    int i = r12.A07 + 1;
                                    ByteBuffer allocate14 = ByteBuffer.allocate(i + 2);
                                    allocate14.put(AnonymousClass24K.A06(i));
                                    try {
                                        byte[] A092 = AnonymousClass24K.A09(r12.A0J.A01(r12.A0J.A01(r12.A0J.A00(new byte[r12.A07], r12.A0N.A01.pskVal), AnonymousClass24K.A08("res binder", MessageDigest.getInstance(r12.A0S).digest(), r12.A07), r12.A07), AnonymousClass24K.A08("finished", new byte[0], r12.A07), r12.A07), digest, r12.A0S);
                                        allocate14.put((byte) A092.length);
                                        allocate14.put(A092);
                                        allocate.put(allocate14.array());
                                        allocate13.put(allocate.array());
                                    } catch (NoSuchAlgorithmException e) {
                                        throw new AnonymousClass25A((byte) 80, new SSLException(e));
                                    }
                                } catch (CloneNotSupportedException e2) {
                                    throw new AnonymousClass25A((byte) 80, new SSLException(e2));
                                }
                            }
                            return allocate13.array();
                        }
                        throw new AnonymousClass25A((byte) 80, new SSLException(AnonymousClass006.A05("Must use key group sent by HelloRetryRequest: ", s, " client key group: ", 23)));
                    } catch (UnsupportedEncodingException e3) {
                        throw new AnonymousClass25A((byte) 80, new SSLException(e3));
                    }
                } catch (UnsupportedEncodingException e4) {
                    throw new AnonymousClass25A((byte) 80, new SSLException(e4));
                }
            } else {
                throw new AnonymousClass25A((byte) 80, new SSLException("Legacy session id is not correctly initialized."));
            }
        } else {
            throw new AnonymousClass25A((byte) 80, new SSLException("Illegal argument. Context cannot be null."));
        }
    }

    public final void A07() throws IOException {
        if (!(this instanceof C142222k)) {
            this.A08 = super.getInputStream();
            this.A09 = super.getOutputStream();
            return;
        }
        C142222k r2 = (C142222k) this;
        Socket socket = r2.A00;
        r2.A08 = socket.getInputStream();
        r2.A09 = socket.getOutputStream();
    }

    @Override // X.AnonymousClass1oF
    public final void A8s() throws IOException {
        AnonymousClass25V A002;
        AnonymousClass25V r6 = null;
        try {
            if (this.A0E && !this.A0D) {
                AnonymousClass24X r1 = this.A03;
                if (r1.A0a) {
                    r1.A03 = false;
                    this.A07.A00(new AnonymousClass25Q(true));
                    A02();
                    AnonymousClass24X r12 = this.A03;
                    if (!r12.A0g) {
                        long j = 0;
                        for (AnonymousClass256 r8 : r12.A0U) {
                            j += (long) r8.A00;
                            this.A07.A00(new AnonymousClass25O(r8));
                        }
                        AnonymousClass1Ou.A00(AnonymousClass007.A01, AnonymousClass006.A06("Replayed early data len = ", j));
                    }
                    AnonymousClass24X r0 = this.A03;
                    r0.A0U = null;
                    long j2 = 0;
                    for (AnonymousClass256 r82 : r0.A0V) {
                        j2 += (long) r82.A00;
                        this.A07.A00(new AnonymousClass25O(r82));
                    }
                    if (j2 > 0) {
                        AnonymousClass1Ou.A00(AnonymousClass007.A01, AnonymousClass006.A06("Spillover early data len = ", j2));
                    }
                    this.A03.A0V = null;
                }
            }
        } catch (IOException e) {
            throw e;
        } catch (AnonymousClass25A e2) {
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
                AnonymousClass24Z r2 = this.A03.A0K;
                synchronized (r2) {
                    A002 = r2.A00();
                }
                r6 = A002;
                if (!(A002 instanceof AnonymousClass25W)) {
                    if (A002 instanceof AnonymousClass25U) {
                        break;
                    }
                    this.A07.A00(A002);
                }
            } catch (AnonymousClass25A e4) {
                A04((byte) 2, e4.description, e4.errorTransient, e4.ex);
            } catch (Exception e5) {
                e = e5;
                if (e.getCause() != null) {
                    e = e.getCause();
                }
                A04((byte) 2, (byte) 80, false, new SSLException(e));
            }
            if (!C147424r.A00.getClass().isInstance(this.A07.A00.A00)) {
                break;
            }
        } while (!(r6 instanceof AnonymousClass25P));
        if (r6 instanceof AnonymousClass25U) {
            A05(r6);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AnonymousClass1oF
    public final void ABC(byte[] bArr, int i, int i2) throws IOException {
        try {
            AnonymousClass256 r6 = new AnonymousClass256(bArr, i, i2);
            if (this.A0E && !this.A0D) {
                AnonymousClass24X r9 = this.A03;
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
                            throw new AnonymousClass25A((byte) 80, new SSLException(sb.toString()));
                        }
                    } else {
                        this.A07.A00(new AnonymousClass25M(r6));
                        this.A03.A0U.add(r6);
                        this.A03.A00 += j;
                    }
                    this.A03.A0a = true;
                    return;
                }
            }
            this.A07.A00(new AnonymousClass25O(r6));
        } catch (AnonymousClass25A e) {
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

    public final String[] getEnabledCipherSuites() {
        return new String[]{"TLS_AES_128_GCM_SHA256", "use default"};
    }

    public final SSLSession getHandshakeSession() {
        return this.A05;
    }

    @Override // java.net.Socket
    public final InputStream getInputStream() throws IOException {
        AnonymousClass1oC r0 = this.A01;
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
        AnonymousClass1oE r0 = this.A02;
        if (r0 != null) {
            return r0;
        }
        throw new IOException("Output stream is closed.");
    }

    public final SSLParameters getSSLParameters() {
        return this.A04;
    }

    public final SSLSession getSession() {
        return this.A06;
    }

    public final String[] getSupportedCipherSuites() {
        return new String[]{"TLS_AES_128_GCM_SHA256", "use default"};
    }

    public final boolean getWantClientAuth() {
        return this.A04.getWantClientAuth();
    }

    public boolean isClosed() {
        return this.A0F;
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
        if (sSLParameters instanceof AbstractC147924w) {
            this.A04 = (AbstractC147924w) sSLParameters;
        }
    }

    public final void setWantClientAuth(boolean z) {
        this.A04.setWantClientAuth(z);
    }
}
