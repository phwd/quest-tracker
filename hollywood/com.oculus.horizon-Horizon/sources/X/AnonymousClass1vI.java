package X;

import com.facebook.FacebookSdk;
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

/* renamed from: X.1vI  reason: invalid class name */
public abstract class AnonymousClass1vI {
    public final AnonymousClass1mE A00;
    public final AnonymousClass1mE A01;
    public final InputStream A02;

    private final AnonymousClass1w6 A01() throws AnonymousClass1v5 {
        try {
            AnonymousClass1mE r6 = this.A00;
            if (r6.available() <= 0 && 0 == 0) {
                return null;
            }
            r6.A00();
            byte[] bArr = new byte[4];
            if (r6.read(bArr) < 4) {
                r6.reset();
                return new AnonymousClass1w7();
            }
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            byte b = wrap.get();
            byte[] bArr2 = new byte[3];
            wrap.get(bArr2);
            int A002 = AnonymousClass1ut.A00(bArr2);
            if (r6.available() < A002) {
                r6.reset();
                return new AnonymousClass1w7();
            }
            r6.reset();
            int i = A002 + 4;
            byte[] bArr3 = new byte[i];
            if (r6.read(bArr3) == i) {
                byte b2 = (byte) b;
                if (b2 == 1) {
                    return new AnonymousClass1w3(bArr3);
                }
                if (b2 != 2) {
                    if (b2 == 4) {
                        return new C10951vu(bArr3);
                    }
                    if (b2 == 8) {
                        return new C10971vw(bArr3);
                    }
                    if (b2 == 11) {
                        return new C10931vs(bArr3);
                    }
                    if (b2 == 13) {
                        return new C10991vy(bArr3);
                    }
                    if (b2 == 15) {
                        return new C10921vr(bArr3);
                    }
                    if (b2 == 20) {
                        return new C10911vq(bArr3);
                    }
                    if (b2 == 24) {
                        return new C10941vt(bArr3);
                    }
                    throw new SSLException(AnonymousClass006.A01("Invalid handshake message type ", b2));
                } else if (i < 38 || !AnonymousClass1ut.A04(Arrays.copyOfRange(bArr3, 6, 38), C10781vd.A07)) {
                    return new C10901vp(bArr3);
                } else {
                    return new C10961vv(bArr3);
                }
            } else {
                throw new AnonymousClass1v5((byte) 80, new SSLException(AnonymousClass006.A01("Could not read handshake message of length ", i)));
            }
        } catch (IOException e) {
            throw new AnonymousClass1v5((byte) 80, new SSLException(e));
        }
    }

    private final boolean A02() throws AnonymousClass1v5 {
        try {
            AnonymousClass1mE r8 = this.A01;
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
                int A012 = AnonymousClass1ut.A01(bArr2);
                if (!AnonymousClass1uw.A00.contains(Byte.valueOf(b)) || s != 771) {
                    throw new AnonymousClass1v5((byte) 10, new SSLException(AnonymousClass006.A05("Invalid record header ", AnonymousClass1ut.A03(bArr))), true);
                } else if (A012 < 0 || A012 > 16640) {
                    throw new AnonymousClass1v5((byte) 22, new SSLException(AnonymousClass006.A05("Invalid record header ", AnonymousClass1ut.A03(bArr))), true);
                } else if (r8.available() >= A012 + 5) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new AnonymousClass1v5((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read, " != ", 5)));
            }
        } catch (IOException e) {
            throw new AnonymousClass1v5((byte) 80, new SSLException(e));
        }
    }

    public final AnonymousClass1w6 A00() throws AnonymousClass1v5 {
        if (!(this instanceof AnonymousClass1vN)) {
            AnonymousClass1vL r6 = (AnonymousClass1vL) this;
            try {
                AnonymousClass1w6 A012 = r6.A01();
                if (A012 != null && !(A012 instanceof AnonymousClass1w7)) {
                    return A012;
                }
                if (r6.A02()) {
                    byte[] bArr = new byte[5];
                    AnonymousClass1mE r13 = ((AnonymousClass1vI) r6).A01;
                    int read = r13.read(bArr);
                    if (read == 5) {
                        try {
                            ByteBuffer wrap = ByteBuffer.wrap(bArr);
                            byte b = wrap.get();
                            wrap.getShort();
                            byte[] bArr2 = new byte[2];
                            wrap.get(bArr2);
                            int A013 = AnonymousClass1ut.A01(bArr2);
                            if (b == 23 || b == 20) {
                                byte[] bArr3 = new byte[A013];
                                int read2 = r13.read(bArr3);
                                if (read2 != A013) {
                                    throw new AnonymousClass1v5((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read2, " != ", A013)));
                                } else if (b == 20) {
                                    return new AnonymousClass1w7();
                                } else {
                                    AnonymousClass0XR r7 = r6.A01;
                                    try {
                                        r7.A00.init(2, r7.A01, new GCMParameterSpec(FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE, AnonymousClass0XR.A00(r7.A02, r6.A00)));
                                        r7.A00.updateAAD(bArr);
                                        byte[] doFinal = r7.A00.doFinal(bArr3, 0, A013);
                                        r6.A00++;
                                        C10801vf r1 = new C10801vf(doFinal);
                                        byte b2 = r1.A00;
                                        if (b2 == 20) {
                                            return new AnonymousClass1w7();
                                        }
                                        switch (b2) {
                                            case 21:
                                                return new AnonymousClass1w5(r1.A02);
                                            case 22:
                                                AnonymousClass1mE r2 = ((AnonymousClass1vI) r6).A00;
                                                byte[] bArr4 = r1.A02;
                                                r2.A1F(bArr4, 0, bArr4.length);
                                                return r6.A01();
                                            case 23:
                                                if (((AnonymousClass1vI) r6).A00.available() <= 0) {
                                                    return new AnonymousClass1w0(r1.A02);
                                                }
                                                throw new AnonymousClass1v5((byte) 10, new SSLException("App data and handshake messages cannot interleave"));
                                            default:
                                                throw new AnonymousClass1v5((byte) 10, new SSLException(AnonymousClass006.A01("Invalid content type ", b2)));
                                        }
                                    } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                                        throw new AnonymousClass1v5((byte) 80, new SSLException(e));
                                    }
                                }
                            } else {
                                throw new AnonymousClass1v5((byte) 47, new SSLException(AnonymousClass006.A01("Invalid content type ", b)));
                            }
                        } catch (SocketException | SocketTimeoutException e2) {
                            throw new AnonymousClass1v5((byte) 80, new SSLException(e2), true);
                        }
                    } else {
                        throw new AnonymousClass1v5((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read, " != ", 5)));
                    }
                } else {
                    byte[] bArr5 = new byte[16645];
                    int read3 = r6.A02.read(bArr5);
                    if (read3 != -1) {
                        ((AnonymousClass1vI) r6).A01.A1F(bArr5, 0, read3);
                        return new AnonymousClass1w7();
                    }
                    throw new AnonymousClass1v5((byte) 80, new SSLException("Transport layer is reached end of file."), true);
                }
            } catch (IOException e3) {
                throw new AnonymousClass1v5((byte) 80, new SSLException(e3));
            }
        } else {
            try {
                AnonymousClass1w6 A014 = A01();
                if (A014 != null && !(A014 instanceof AnonymousClass1w7)) {
                    return A014;
                }
                if (A02()) {
                    byte[] bArr6 = new byte[5];
                    AnonymousClass1mE r10 = this.A01;
                    int read4 = r10.read(bArr6);
                    if (read4 == 5) {
                        try {
                            ByteBuffer wrap2 = ByteBuffer.wrap(bArr6);
                            byte b3 = wrap2.get();
                            wrap2.getShort();
                            byte[] bArr7 = new byte[2];
                            wrap2.get(bArr7);
                            int A015 = AnonymousClass1ut.A01(bArr7);
                            byte[] bArr8 = new byte[A015];
                            int read5 = r10.read(bArr8);
                            if (read5 != A015) {
                                throw new AnonymousClass1v5((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read5, " != ", A015)));
                            } else if (b3 == 20) {
                                return new AnonymousClass1w7();
                            } else {
                                switch (b3) {
                                    case 21:
                                        return new AnonymousClass1w5(bArr8);
                                    case 22:
                                        this.A00.A1F(bArr8, 0, A015);
                                        return A01();
                                    case 23:
                                        if (this.A00.available() <= 0) {
                                            return new AnonymousClass1w0(bArr8);
                                        }
                                        throw new AnonymousClass1v5((byte) 10, new SSLException("App data and handshake messages cannot interleave"));
                                    default:
                                        throw new AnonymousClass1v5((byte) 10, new SSLException(AnonymousClass006.A01("Received Message with invalid type ", b3)));
                                }
                            }
                        } catch (SocketException | SocketTimeoutException e4) {
                            throw new AnonymousClass1v5((byte) 80, new SSLException(e4), true);
                        }
                    } else {
                        throw new AnonymousClass1v5((byte) 80, new SSLException(AnonymousClass006.A03("read returned fewer than expected bytes ", read4, " != ", 5)));
                    }
                } else {
                    byte[] bArr9 = new byte[16645];
                    int read6 = this.A02.read(bArr9);
                    if (read6 != -1) {
                        this.A01.A1F(bArr9, 0, read6);
                        return new AnonymousClass1w7();
                    }
                    throw new AnonymousClass1v5((byte) 80, new SSLException("Transport layer is reached end of file."), true);
                }
            } catch (IOException e5) {
                throw new AnonymousClass1v5((byte) 80, new SSLException(e5));
            }
        }
    }

    public AnonymousClass1vI(InputStream inputStream, AnonymousClass1mE r5) throws AnonymousClass1v5 {
        if (inputStream == null || r5 == null) {
            throw new AnonymousClass1v5((byte) 80, new SSLException("transportIn or recordStream is null"));
        }
        this.A02 = inputStream;
        this.A00 = new AnonymousClass1mE();
        this.A01 = r5;
    }
}
