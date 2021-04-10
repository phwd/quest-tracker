package X;

import com.adobe.xmp.impl.Base64;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;

/* renamed from: X.24Y  reason: invalid class name */
public final class AnonymousClass24Y {
    public static void A00(byte[] bArr, boolean z, AnonymousClass24X r15) throws AnonymousClass25A {
        Set<Short> set;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        short s = wrap.getShort();
        wrap.get(new byte[32]);
        byte[] bArr2 = new byte[((short) (((short) (wrap.get() & Base64.INVALID)) | 0))];
        wrap.get(bArr2);
        short s2 = wrap.getShort();
        byte b = wrap.get();
        byte[] bArr3 = new byte[2];
        wrap.get(bArr3);
        byte[] bArr4 = new byte[AnonymousClass24K.A01(bArr3)];
        wrap.get(bArr4);
        AnonymousClass24S r6 = new AnonymousClass24S(bArr4);
        AnonymousClass251 A00 = r6.A00(43);
        if (A00 == null) {
            throw new AnonymousClass25A((byte) 109, new SSLException("Supported version extension not found."));
        } else if (C147824v.A01.contains(Short.valueOf((short) AnonymousClass24K.A01(A00.A01)))) {
            byte[] bArr5 = r15.A04;
            if (bArr5 == null || AnonymousClass24K.A04(A00.A01, bArr5)) {
                if (z) {
                    set = C147824v.A02;
                } else {
                    set = C147824v.A03;
                }
                HashSet hashSet = new HashSet(r6.A02.keySet());
                hashSet.removeAll(set);
                if (hashSet.size() != 0) {
                    throw new AnonymousClass25A((byte) 47, new SSLException("Unexpected extension provided by the server"));
                }
                Short sh = C147824v.A00;
                if (s != 771) {
                    StringBuilder sb = new StringBuilder("Unexpected protocol version");
                    sb.append((int) s);
                    sb.append(" != ");
                    sb.append(sh);
                    throw new AnonymousClass25A((byte) 80, new SSLException(sb.toString()));
                } else if (AnonymousClass24K.A04(r15.A0m, bArr2)) {
                    short s3 = r15.A02;
                    if (s3 != 0 && s3 != s2) {
                        throw new AnonymousClass25A((byte) 47, new SSLException("Cipher suite in server hello does not match HelloRetryRequest cipher suite."));
                    } else if (s2 != 4865) {
                        throw new AnonymousClass25A((byte) 80, new SSLException("Server selected invalid cipher suite"));
                    } else if (b == 0) {
                        AnonymousClass251 A002 = r6.A00(51);
                        if (A002 != null) {
                            ByteBuffer wrap2 = ByteBuffer.wrap(A002.A01);
                            short s4 = wrap2.getShort();
                            if (s4 == 23) {
                                if (!z) {
                                    byte[] bArr6 = new byte[2];
                                    wrap2.get(bArr6);
                                    int A01 = AnonymousClass24K.A01(bArr6);
                                    if (A01 == 65) {
                                        byte[] bArr7 = new byte[65];
                                        r15.A0n = bArr7;
                                        wrap2.get(bArr7);
                                    } else {
                                        throw new AnonymousClass25A((byte) 80, new SSLException(AnonymousClass006.A05("Key length mismatch ", A01, " != ", 65)));
                                    }
                                }
                                AnonymousClass251 A003 = r6.A00(41);
                                if (!(r15.A0N.A01 == null || A003 == null)) {
                                    if (AnonymousClass24K.A01(A003.A01) <= 0) {
                                        r15.A0c = true;
                                        r15.A0h = true;
                                    } else {
                                        StringBuilder sb2 = new StringBuilder("Incorrect PSK index value chosen by server ");
                                        sb2.append(A003);
                                        throw new AnonymousClass25A((byte) 80, new SSLException(sb2.toString()));
                                    }
                                }
                                if (z) {
                                    r15.A04 = A00.A01;
                                    r15.A02 = s2;
                                    r15.A0Y = s4;
                                    AnonymousClass251 A004 = r6.A00(44);
                                    if (A004 != null) {
                                        ByteBuffer wrap3 = ByteBuffer.wrap(A004.A01);
                                        byte[] bArr8 = new byte[2];
                                        wrap3.get(bArr8);
                                        byte[] bArr9 = new byte[AnonymousClass24K.A01(bArr8)];
                                        wrap3.get(bArr9);
                                        r15.A0l = bArr9;
                                    }
                                }
                                if (wrap.hasRemaining()) {
                                    throw new AnonymousClass25A((byte) 80, new SSLException("Server Hello has more bytes than expected."));
                                }
                                return;
                            }
                            throw new AnonymousClass25A((byte) 80, new SSLException("Key share algorithm mismatch."));
                        }
                        throw new AnonymousClass25A((byte) 109, new SSLException("Key share extension not found."));
                    } else {
                        throw new AnonymousClass25A((byte) 80, new SSLException("Invalid compression method.0"));
                    }
                } else {
                    throw new AnonymousClass25A((byte) 80, new SSLException("Bad session id"));
                }
            } else {
                throw new AnonymousClass25A((byte) 47, new SSLException("Supported version in server hello does not match HelloRetryRequest supported version."));
            }
        } else {
            throw new AnonymousClass25A((byte) 110, new SSLException("Server sent an unsupported version."));
        }
    }
}
