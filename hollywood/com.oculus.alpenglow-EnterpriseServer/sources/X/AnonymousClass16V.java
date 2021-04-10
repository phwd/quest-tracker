package X;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.GCMParameterSpec;
import javax.net.ssl.SSLException;

/* renamed from: X.16V  reason: invalid class name */
public abstract class AnonymousClass16V {
    public final C098316s A00;
    public final C098316s A01;
    public final InputStream A02;

    private final AnonymousClass10c A01() throws AnonymousClass11f {
        try {
            C098316s r6 = this.A00;
            if (r6.available() <= 0 && 0 == 0) {
                return null;
            }
            r6.A00();
            byte[] bArr = new byte[4];
            if (r6.read(bArr) < 4) {
                r6.reset();
                return new C099317c();
            }
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            byte b = wrap.get();
            byte[] bArr2 = new byte[3];
            wrap.get(bArr2);
            int A002 = AnonymousClass11e.A00(bArr2);
            if (r6.available() < A002) {
                r6.reset();
                return new C099317c();
            }
            r6.reset();
            int i = A002 + 4;
            byte[] bArr3 = new byte[i];
            if (r6.read(bArr3) == i) {
                byte b2 = (byte) b;
                if (b2 == 1) {
                    return new AnonymousClass17Z(bArr3);
                }
                if (b2 != 2) {
                    if (b2 == 4) {
                        return new AnonymousClass17Q(bArr3);
                    }
                    if (b2 == 8) {
                        return new AnonymousClass17S(bArr3);
                    }
                    if (b2 == 11) {
                        return new AnonymousClass17O(bArr3);
                    }
                    if (b2 == 13) {
                        return new AnonymousClass17U(bArr3);
                    }
                    if (b2 == 15) {
                        return new AnonymousClass17N(bArr3);
                    }
                    if (b2 == 20) {
                        return new AnonymousClass17M(bArr3);
                    }
                    if (b2 == 24) {
                        return new AnonymousClass17P(bArr3);
                    }
                    throw new SSLException(AnonymousClass006.A01("Invalid handshake message type ", b2));
                } else if (i < 38 || !AnonymousClass11e.A04(Arrays.copyOfRange(bArr3, 6, 38), AnonymousClass10p.A07)) {
                    return new AnonymousClass17L(bArr3);
                } else {
                    return new AnonymousClass17R(bArr3);
                }
            } else {
                throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A01("Could not read handshake message of length ", i)));
            }
        } catch (IOException e) {
            throw new AnonymousClass11f((byte) 80, new SSLException(e));
        }
    }

    private final boolean A02() throws AnonymousClass11f {
        try {
            C098316s r8 = this.A01;
            if (r8.available() < 5) {
                return false;
            }
            byte[] bArr = new byte[5];
            r8.A00();
            int read = r8.read(bArr);
            if (read == 5) {
                r8.reset();
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                byte b = wrap.get();
                short s = wrap.getShort();
                byte[] bArr2 = new byte[2];
                wrap.get(bArr2);
                int A012 = AnonymousClass11e.A01(bArr2);
                if (!AnonymousClass10b.A00.contains(Byte.valueOf(b)) || s != 771) {
                    throw new AnonymousClass11f((byte) 10, new SSLException(AnonymousClass006.A05("Invalid record header ", AnonymousClass11e.A03(bArr))), true);
                } else if (A012 < 0 || A012 > 16640) {
                    throw new AnonymousClass11f((byte) 22, new SSLException(AnonymousClass006.A05("Invalid record header ", AnonymousClass11e.A03(bArr))), true);
                } else if (r8.available() >= A012 + 5) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read, " != ", 5)));
            }
        } catch (IOException e) {
            throw new AnonymousClass11f((byte) 80, new SSLException(e));
        }
    }

    public final AnonymousClass10c A00() throws AnonymousClass11f {
        if (!(this instanceof AnonymousClass16Z)) {
            AnonymousClass16X r6 = (AnonymousClass16X) this;
            try {
                AnonymousClass10c A012 = r6.A01();
                if (A012 != null && !(A012 instanceof C099317c)) {
                    return A012;
                }
                if (r6.A02()) {
                    byte[] bArr = new byte[5];
                    C098316s r13 = ((AnonymousClass16V) r6).A01;
                    int read = r13.read(bArr);
                    if (read == 5) {
                        try {
                            ByteBuffer wrap = ByteBuffer.wrap(bArr);
                            byte b = wrap.get();
                            wrap.getShort();
                            byte[] bArr2 = new byte[2];
                            wrap.get(bArr2);
                            int A013 = AnonymousClass11e.A01(bArr2);
                            if (b == 23 || b == 20) {
                                byte[] bArr3 = new byte[A013];
                                int read2 = r13.read(bArr3);
                                if (read2 != A013) {
                                    throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read2, " != ", A013)));
                                } else if (b == 20) {
                                    return new C099317c();
                                } else {
                                    C097916o r7 = r6.A01;
                                    try {
                                        r7.A00.init(2, r7.A01, new GCMParameterSpec(128, C097916o.A00(r7.A02, r6.A00)));
                                        r7.A00.updateAAD(bArr);
                                        byte[] doFinal = r7.A00.doFinal(bArr3, 0, A013);
                                        r6.A00++;
                                        C098816x r1 = new C098816x(doFinal);
                                        byte b2 = r1.A00;
                                        if (b2 == 20) {
                                            return new C099317c();
                                        }
                                        switch (b2) {
                                            case 21:
                                                return new C099217b(r1.A02);
                                            case 22:
                                                C098316s r2 = ((AnonymousClass16V) r6).A00;
                                                byte[] bArr4 = r1.A02;
                                                r2.A14(bArr4, 0, bArr4.length);
                                                return r6.A01();
                                            case 23:
                                                if (((AnonymousClass16V) r6).A00.available() <= 0) {
                                                    return new AnonymousClass17W(r1.A02);
                                                }
                                                throw new AnonymousClass11f((byte) 10, new SSLException("App data and handshake messages cannot interleave"));
                                            default:
                                                throw new AnonymousClass11f((byte) 10, new SSLException(AnonymousClass006.A01("Invalid content type ", b2)));
                                        }
                                    } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                                        throw new AnonymousClass11f((byte) 80, new SSLException(e));
                                    }
                                }
                            } else {
                                throw new AnonymousClass11f((byte) 47, new SSLException(AnonymousClass006.A01("Invalid content type ", b)));
                            }
                        } catch (SocketException | SocketTimeoutException e2) {
                            throw new AnonymousClass11f((byte) 80, new SSLException(e2), true);
                        }
                    } else {
                        throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read, " != ", 5)));
                    }
                } else {
                    byte[] bArr5 = new byte[16645];
                    int read3 = r6.A02.read(bArr5);
                    if (read3 != -1) {
                        ((AnonymousClass16V) r6).A01.A14(bArr5, 0, read3);
                        return new C099317c();
                    }
                    throw new AnonymousClass11f((byte) 80, new SSLException("Transport layer is reached end of file."), true);
                }
            } catch (IOException e3) {
                throw new AnonymousClass11f((byte) 80, new SSLException(e3));
            }
        } else {
            try {
                AnonymousClass10c A014 = A01();
                if (A014 != null && !(A014 instanceof C099317c)) {
                    return A014;
                }
                if (A02()) {
                    byte[] bArr6 = new byte[5];
                    C098316s r10 = this.A01;
                    int read4 = r10.read(bArr6);
                    if (read4 == 5) {
                        try {
                            ByteBuffer wrap2 = ByteBuffer.wrap(bArr6);
                            byte b3 = wrap2.get();
                            wrap2.getShort();
                            byte[] bArr7 = new byte[2];
                            wrap2.get(bArr7);
                            int A015 = AnonymousClass11e.A01(bArr7);
                            byte[] bArr8 = new byte[A015];
                            int read5 = r10.read(bArr8);
                            if (read5 != A015) {
                                throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read5, " != ", A015)));
                            } else if (b3 == 20) {
                                return new C099317c();
                            } else {
                                switch (b3) {
                                    case 21:
                                        return new C099217b(bArr8);
                                    case 22:
                                        this.A00.A14(bArr8, 0, A015);
                                        return A01();
                                    case 23:
                                        if (this.A00.available() <= 0) {
                                            return new AnonymousClass17W(bArr8);
                                        }
                                        throw new AnonymousClass11f((byte) 10, new SSLException("App data and handshake messages cannot interleave"));
                                    default:
                                        throw new AnonymousClass11f((byte) 10, new SSLException(AnonymousClass006.A01("Received Message with invalid type ", b3)));
                                }
                            }
                        } catch (SocketException | SocketTimeoutException e4) {
                            throw new AnonymousClass11f((byte) 80, new SSLException(e4), true);
                        }
                    } else {
                        throw new AnonymousClass11f((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read4, " != ", 5)));
                    }
                } else {
                    byte[] bArr9 = new byte[16645];
                    int read6 = this.A02.read(bArr9);
                    if (read6 != -1) {
                        this.A01.A14(bArr9, 0, read6);
                        return new C099317c();
                    }
                    throw new AnonymousClass11f((byte) 80, new SSLException("Transport layer is reached end of file."), true);
                }
            } catch (IOException e5) {
                throw new AnonymousClass11f((byte) 80, new SSLException(e5));
            }
        }
    }

    public AnonymousClass16V(InputStream inputStream, C098316s r5) throws AnonymousClass11f {
        if (inputStream == null || r5 == null) {
            throw new AnonymousClass11f((byte) 80, new SSLException("transportIn or recordStream is null"));
        }
        this.A02 = inputStream;
        this.A00 = new C098316s();
        this.A01 = r5;
    }
}
