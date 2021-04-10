package X;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;

/* renamed from: X.16S  reason: invalid class name */
public final class AnonymousClass16S {
    public static void A00(byte[] bArr, boolean z, AnonymousClass16P r15) throws AnonymousClass11f {
        String str;
        byte b;
        String str2;
        Set<Short> set;
        String str3;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        short s = wrap.getShort();
        wrap.get(new byte[32]);
        byte[] bArr2 = new byte[((short) (((short) (wrap.get() & 255)) | 0))];
        wrap.get(bArr2);
        short s2 = wrap.getShort();
        byte b2 = wrap.get();
        byte[] bArr3 = new byte[2];
        wrap.get(bArr3);
        byte[] bArr4 = new byte[AnonymousClass11e.A01(bArr3)];
        wrap.get(bArr4);
        AnonymousClass16U r6 = new AnonymousClass16U(bArr4);
        AnonymousClass171 A00 = r6.A00(43);
        if (A00 != null) {
            if (AnonymousClass10p.A01.contains(Short.valueOf((short) AnonymousClass11e.A01(A00.A01)))) {
                byte[] bArr5 = r15.A04;
                if (bArr5 == null || AnonymousClass11e.A04(A00.A01, bArr5)) {
                    if (z) {
                        set = AnonymousClass10p.A02;
                    } else {
                        set = AnonymousClass10p.A03;
                    }
                    HashSet hashSet = new HashSet(r6.A02.keySet());
                    hashSet.removeAll(set);
                    if (hashSet.size() != 0) {
                        str3 = "Unexpected extension provided by the server";
                    } else {
                        Short sh = AnonymousClass10p.A00;
                        b = 80;
                        if (s != 771) {
                            str2 = "Unexpected protocol version" + ((int) s) + " != " + sh;
                        } else if (AnonymousClass11e.A04(r15.A0m, bArr2)) {
                            short s3 = r15.A02;
                            if (s3 != 0 && s3 != s2) {
                                str3 = "Cipher suite in server hello does not match HelloRetryRequest cipher suite.";
                            } else if (s2 != 4865) {
                                str2 = "Server selected invalid cipher suite";
                            } else if (b2 == 0) {
                                AnonymousClass171 A002 = r6.A00(51);
                                if (A002 != null) {
                                    ByteBuffer wrap2 = ByteBuffer.wrap(A002.A01);
                                    short s4 = wrap2.getShort();
                                    if (s4 == 23) {
                                        if (!z) {
                                            byte[] bArr6 = new byte[2];
                                            wrap2.get(bArr6);
                                            int A01 = AnonymousClass11e.A01(bArr6);
                                            if (A01 == 65) {
                                                byte[] bArr7 = new byte[65];
                                                r15.A0n = bArr7;
                                                wrap2.get(bArr7);
                                            } else {
                                                str2 = AnonymousClass006.A03("Key length mismatch ", A01, " != ", 65);
                                            }
                                        }
                                        AnonymousClass171 A003 = r6.A00(41);
                                        if (!(r15.A0N.A01 == null || A003 == null)) {
                                            if (AnonymousClass11e.A01(A003.A01) <= 0) {
                                                r15.A0c = true;
                                                r15.A0h = true;
                                            } else {
                                                str2 = "Incorrect PSK index value chosen by server " + A003;
                                            }
                                        }
                                        if (z) {
                                            r15.A04 = A00.A01;
                                            r15.A02 = s2;
                                            r15.A0Y = s4;
                                            AnonymousClass171 A004 = r6.A00(44);
                                            if (A004 != null) {
                                                ByteBuffer wrap3 = ByteBuffer.wrap(A004.A01);
                                                byte[] bArr8 = new byte[2];
                                                wrap3.get(bArr8);
                                                byte[] bArr9 = new byte[AnonymousClass11e.A01(bArr8)];
                                                wrap3.get(bArr9);
                                                r15.A0l = bArr9;
                                            }
                                        }
                                        if (wrap.hasRemaining()) {
                                            str2 = "Server Hello has more bytes than expected.";
                                        } else {
                                            return;
                                        }
                                    } else {
                                        str2 = "Key share algorithm mismatch.";
                                    }
                                } else {
                                    str = "Key share extension not found.";
                                }
                            } else {
                                str2 = "Invalid compression method.0";
                            }
                        } else {
                            str2 = "Bad session id";
                        }
                    }
                } else {
                    str3 = "Supported version in server hello does not match HelloRetryRequest supported version.";
                }
                throw new AnonymousClass11f((byte) 47, new SSLException(str3));
            }
            b = 110;
            str2 = "Server sent an unsupported version.";
            throw new AnonymousClass11f(b, new SSLException(str2));
        }
        str = "Supported version extension not found.";
        throw new AnonymousClass11f((byte) 109, new SSLException(str));
    }
}
