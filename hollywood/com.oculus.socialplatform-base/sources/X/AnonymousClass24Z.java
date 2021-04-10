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

/* renamed from: X.24Z  reason: invalid class name */
public abstract class AnonymousClass24Z {
    public final AnonymousClass1oD A00;
    public final AnonymousClass1oD A01;
    public final InputStream A02;

    private final AnonymousClass25V A01() throws AnonymousClass25A {
        try {
            AnonymousClass1oD r6 = this.A00;
            if (r6.available() <= 0 && 0 == 0) {
                return null;
            }
            r6.A00();
            byte[] bArr = new byte[4];
            if (r6.read(bArr) < 4) {
                r6.reset();
                return new AnonymousClass25W();
            }
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            byte b = wrap.get();
            byte[] bArr2 = new byte[3];
            wrap.get(bArr2);
            int A002 = AnonymousClass24K.A00(bArr2);
            if (r6.available() < A002) {
                r6.reset();
                return new AnonymousClass25W();
            }
            r6.reset();
            int i = A002 + 4;
            byte[] bArr3 = new byte[i];
            if (r6.read(bArr3) == i) {
                byte b2 = (byte) b;
                if (b2 == 1) {
                    return new AnonymousClass25S(bArr3);
                }
                if (b2 != 2) {
                    if (b2 == 4) {
                        return new AnonymousClass25J(bArr3);
                    }
                    if (b2 == 8) {
                        return new AnonymousClass25L(bArr3);
                    }
                    if (b2 == 11) {
                        return new AnonymousClass25H(bArr3);
                    }
                    if (b2 == 13) {
                        return new AnonymousClass25N(bArr3);
                    }
                    if (b2 == 15) {
                        return new AnonymousClass25G(bArr3);
                    }
                    if (b2 == 20) {
                        return new AnonymousClass25F(bArr3);
                    }
                    if (b2 == 24) {
                        return new AnonymousClass25I(bArr3);
                    }
                    throw new SSLException(AnonymousClass006.A03("Invalid handshake message type ", b2));
                } else if (i < 38 || !AnonymousClass24K.A04(Arrays.copyOfRange(bArr3, 6, 38), C147824v.A07)) {
                    return new AnonymousClass25E(bArr3);
                } else {
                    return new AnonymousClass25K(bArr3);
                }
            } else {
                throw new AnonymousClass25A((byte) 80, new SSLException(AnonymousClass006.A03("Could not read handshake message of length ", i)));
            }
        } catch (IOException e) {
            throw new AnonymousClass25A((byte) 80, new SSLException(e));
        }
    }

    private final boolean A02() throws AnonymousClass25A {
        try {
            AnonymousClass1oD r8 = this.A01;
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
                int A012 = AnonymousClass24K.A01(bArr2);
                if (!AnonymousClass1Rc.A00.contains(Byte.valueOf(b)) || s != 771) {
                    throw new AnonymousClass25A((byte) 10, new SSLException(AnonymousClass006.A07("Invalid record header ", AnonymousClass24K.A03(bArr))), true);
                } else if (A012 < 0 || A012 > 16640) {
                    throw new AnonymousClass25A((byte) 22, new SSLException(AnonymousClass006.A07("Invalid record header ", AnonymousClass24K.A03(bArr))), true);
                } else if (r8.available() >= A012 + 5) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new AnonymousClass25A((byte) 80, new SSLException(AnonymousClass006.A05("read returned fewer than expected bytes ", read, " != ", 5)));
            }
        } catch (IOException e) {
            throw new AnonymousClass25A((byte) 80, new SSLException(e));
        }
    }

    public final AnonymousClass25V A00() throws AnonymousClass25A {
        if (!(this instanceof C146324d)) {
            AnonymousClass24b r6 = (AnonymousClass24b) this;
            try {
                AnonymousClass25V A012 = r6.A01();
                if (A012 != null && !(A012 instanceof AnonymousClass25W)) {
                    return A012;
                }
                if (r6.A02()) {
                    byte[] bArr = new byte[5];
                    AnonymousClass1oD r13 = ((AnonymousClass24Z) r6).A01;
                    int read = r13.read(bArr);
                    if (read == 5) {
                        try {
                            ByteBuffer wrap = ByteBuffer.wrap(bArr);
                            byte b = wrap.get();
                            wrap.getShort();
                            byte[] bArr2 = new byte[2];
                            wrap.get(bArr2);
                            int A013 = AnonymousClass24K.A01(bArr2);
                            if (b == 23 || b == 20) {
                                byte[] bArr3 = new byte[A013];
                                int read2 = r13.read(bArr3);
                                if (read2 != A013) {
                                    throw new AnonymousClass25A((byte) 80, new SSLException(AnonymousClass006.A05("read returned fewer than expected bytes ", read2, " != ", A013)));
                                } else if (b == 20) {
                                    return new AnonymousClass25W();
                                } else {
                                    C146924m r7 = r6.A01;
                                    try {
                                        r7.A00.init(2, r7.A01, new GCMParameterSpec(128, C146924m.A00(r7.A02, r6.A00)));
                                        r7.A00.updateAAD(bArr);
                                        byte[] doFinal = r7.A00.doFinal(bArr3, 0, A013);
                                        r6.A00++;
                                        C148024x r1 = new C148024x(doFinal);
                                        byte b2 = r1.A00;
                                        if (b2 == 20) {
                                            return new AnonymousClass25W();
                                        }
                                        switch (b2) {
                                            case 21:
                                                return new AnonymousClass25U(r1.A02);
                                            case 22:
                                                AnonymousClass1oD r2 = ((AnonymousClass24Z) r6).A00;
                                                byte[] bArr4 = r1.A02;
                                                r2.A1S(bArr4, 0, bArr4.length);
                                                return r6.A01();
                                            case 23:
                                                if (((AnonymousClass24Z) r6).A00.available() <= 0) {
                                                    return new AnonymousClass25P(r1.A02);
                                                }
                                                throw new AnonymousClass25A((byte) 10, new SSLException("App data and handshake messages cannot interleave"));
                                            default:
                                                throw new AnonymousClass25A((byte) 10, new SSLException(AnonymousClass006.A03("Invalid content type ", b2)));
                                        }
                                    } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                                        throw new AnonymousClass25A((byte) 80, new SSLException(e));
                                    }
                                }
                            } else {
                                throw new AnonymousClass25A((byte) 47, new SSLException(AnonymousClass006.A03("Invalid content type ", b)));
                            }
                        } catch (SocketException | SocketTimeoutException e2) {
                            throw new AnonymousClass25A((byte) 80, new SSLException(e2), true);
                        }
                    } else {
                        throw new AnonymousClass25A((byte) 80, new SSLException(AnonymousClass006.A05("read returned fewer than expected bytes ", read, " != ", 5)));
                    }
                } else {
                    byte[] bArr5 = new byte[16645];
                    int read3 = r6.A02.read(bArr5);
                    if (read3 != -1) {
                        ((AnonymousClass24Z) r6).A01.A1S(bArr5, 0, read3);
                        return new AnonymousClass25W();
                    }
                    throw new AnonymousClass25A((byte) 80, new SSLException("Transport layer is reached end of file."), true);
                }
            } catch (IOException e3) {
                throw new AnonymousClass25A((byte) 80, new SSLException(e3));
            }
        } else {
            try {
                AnonymousClass25V A014 = A01();
                if (A014 != null && !(A014 instanceof AnonymousClass25W)) {
                    return A014;
                }
                if (A02()) {
                    byte[] bArr6 = new byte[5];
                    AnonymousClass1oD r10 = this.A01;
                    int read4 = r10.read(bArr6);
                    if (read4 == 5) {
                        try {
                            ByteBuffer wrap2 = ByteBuffer.wrap(bArr6);
                            byte b3 = wrap2.get();
                            wrap2.getShort();
                            byte[] bArr7 = new byte[2];
                            wrap2.get(bArr7);
                            int A015 = AnonymousClass24K.A01(bArr7);
                            byte[] bArr8 = new byte[A015];
                            int read5 = r10.read(bArr8);
                            if (read5 != A015) {
                                throw new AnonymousClass25A((byte) 80, new SSLException(AnonymousClass006.A05("read returned fewer than expected bytes ", read5, " != ", A015)));
                            } else if (b3 == 20) {
                                return new AnonymousClass25W();
                            } else {
                                switch (b3) {
                                    case 21:
                                        return new AnonymousClass25U(bArr8);
                                    case 22:
                                        this.A00.A1S(bArr8, 0, A015);
                                        return A01();
                                    case 23:
                                        if (this.A00.available() <= 0) {
                                            return new AnonymousClass25P(bArr8);
                                        }
                                        throw new AnonymousClass25A((byte) 10, new SSLException("App data and handshake messages cannot interleave"));
                                    default:
                                        throw new AnonymousClass25A((byte) 10, new SSLException(AnonymousClass006.A03("Received Message with invalid type ", b3)));
                                }
                            }
                        } catch (SocketException | SocketTimeoutException e4) {
                            throw new AnonymousClass25A((byte) 80, new SSLException(e4), true);
                        }
                    } else {
                        throw new AnonymousClass25A((byte) 80, new SSLException(AnonymousClass006.A05("read returned fewer than expected bytes ", read4, " != ", 5)));
                    }
                } else {
                    byte[] bArr9 = new byte[16645];
                    int read6 = this.A02.read(bArr9);
                    if (read6 != -1) {
                        this.A01.A1S(bArr9, 0, read6);
                        return new AnonymousClass25W();
                    }
                    throw new AnonymousClass25A((byte) 80, new SSLException("Transport layer is reached end of file."), true);
                }
            } catch (IOException e5) {
                throw new AnonymousClass25A((byte) 80, new SSLException(e5));
            }
        }
    }

    public AnonymousClass24Z(InputStream inputStream, AnonymousClass1oD r5) throws AnonymousClass25A {
        if (inputStream == null || r5 == null) {
            throw new AnonymousClass25A((byte) 80, new SSLException("transportIn or recordStream is null"));
        }
        this.A02 = inputStream;
        this.A00 = new AnonymousClass1oD();
        this.A01 = r5;
    }
}
