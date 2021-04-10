package X;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;

/* renamed from: X.1vH  reason: invalid class name */
public final class AnonymousClass1vH {
    public static void A00(byte[] bArr, boolean z, AnonymousClass1vF r15) throws AnonymousClass1v5 {
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
        byte[] bArr4 = new byte[AnonymousClass1ut.A01(bArr3)];
        wrap.get(bArr4);
        AnonymousClass1vA r6 = new AnonymousClass1vA(bArr4);
        C10851vk A00 = r6.A00(43);
        if (A00 != null) {
            if (C10781vd.A01.contains(Short.valueOf((short) AnonymousClass1ut.A01(A00.A01)))) {
                byte[] bArr5 = r15.A04;
                if (bArr5 == null || AnonymousClass1ut.A04(A00.A01, bArr5)) {
                    if (z) {
                        set = C10781vd.A02;
                    } else {
                        set = C10781vd.A03;
                    }
                    HashSet hashSet = new HashSet(r6.A02.keySet());
                    hashSet.removeAll(set);
                    if (hashSet.size() != 0) {
                        str3 = "Unexpected extension provided by the server";
                    } else {
                        Short sh = C10781vd.A00;
                        b = 80;
                        if (s != 771) {
                            StringBuilder sb = new StringBuilder("Unexpected protocol version");
                            sb.append((int) s);
                            sb.append(" != ");
                            sb.append(sh);
                            str2 = sb.toString();
                        } else if (AnonymousClass1ut.A04(r15.A0m, bArr2)) {
                            short s3 = r15.A02;
                            if (s3 != 0 && s3 != s2) {
                                str3 = "Cipher suite in server hello does not match HelloRetryRequest cipher suite.";
                            } else if (s2 != 4865) {
                                str2 = "Server selected invalid cipher suite";
                            } else if (b2 == 0) {
                                C10851vk A002 = r6.A00(51);
                                if (A002 != null) {
                                    ByteBuffer wrap2 = ByteBuffer.wrap(A002.A01);
                                    short s4 = wrap2.getShort();
                                    if (s4 == 23) {
                                        if (!z) {
                                            byte[] bArr6 = new byte[2];
                                            wrap2.get(bArr6);
                                            int A01 = AnonymousClass1ut.A01(bArr6);
                                            if (A01 == 65) {
                                                byte[] bArr7 = new byte[65];
                                                r15.A0n = bArr7;
                                                wrap2.get(bArr7);
                                            } else {
                                                str2 = AnonymousClass006.A03("Key length mismatch ", A01, " != ", 65);
                                            }
                                        }
                                        C10851vk A003 = r6.A00(41);
                                        if (!(r15.A0N.A01 == null || A003 == null)) {
                                            if (AnonymousClass1ut.A01(A003.A01) <= 0) {
                                                r15.A0c = true;
                                                r15.A0h = true;
                                            } else {
                                                StringBuilder sb2 = new StringBuilder("Incorrect PSK index value chosen by server ");
                                                sb2.append(A003);
                                                str2 = sb2.toString();
                                            }
                                        }
                                        if (z) {
                                            r15.A04 = A00.A01;
                                            r15.A02 = s2;
                                            r15.A0Y = s4;
                                            C10851vk A004 = r6.A00(44);
                                            if (A004 != null) {
                                                ByteBuffer wrap3 = ByteBuffer.wrap(A004.A01);
                                                byte[] bArr8 = new byte[2];
                                                wrap3.get(bArr8);
                                                byte[] bArr9 = new byte[AnonymousClass1ut.A01(bArr8)];
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
                throw new AnonymousClass1v5((byte) 47, new SSLException(str3));
            }
            b = 110;
            str2 = "Server sent an unsupported version.";
            throw new AnonymousClass1v5(b, new SSLException(str2));
        }
        str = "Supported version extension not found.";
        throw new AnonymousClass1v5((byte) 109, new SSLException(str));
    }
}
