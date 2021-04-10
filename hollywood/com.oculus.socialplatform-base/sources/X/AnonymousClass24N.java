package X;

import java.io.IOException;
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

/* renamed from: X.24N  reason: invalid class name */
public abstract class AnonymousClass24N {
    public final synchronized void A01(byte b, byte[] bArr, int i, int i2) throws AnonymousClass25A {
        if (bArr == null) {
            throw new AnonymousClass25A((byte) 80, new SSLException("Data cannot be null"));
        } else if (AnonymousClass1Rc.A00.contains(Byte.valueOf(b))) {
            while (i2 > 16384) {
                A00(b, bArr, i, 16384);
                i += 16384;
                i2 -= 16384;
            }
            if (i2 > 0) {
                A00(b, bArr, i, i2);
            }
        } else {
            throw new AnonymousClass25A((byte) 80, new SSLException("Invalid content type"));
        }
    }

    private final void A00(byte b, byte[] bArr, int i, int i2) throws AnonymousClass25A {
        short s;
        if (!(this instanceof AnonymousClass24M)) {
            C146224c r7 = (C146224c) this;
            if (i2 <= 16384) {
                C148024x r2 = new C148024x(Arrays.copyOfRange(bArr, i, i2 + i), b);
                ByteBuffer allocate = ByteBuffer.allocate(r2.A02.length + 1 + r2.A01);
                allocate.put(r2.A02);
                allocate.put(r2.A00);
                allocate.put(new byte[r2.A01]);
                byte[] array = allocate.array();
                ByteBuffer allocate2 = ByteBuffer.allocate(5);
                allocate2.put((byte) 23);
                allocate2.putShort(771);
                int length = array.length;
                allocate2.put(AnonymousClass24K.A06(16 + length));
                C146924m r11 = r7.A01;
                long j = r7.A00;
                byte[] array2 = allocate2.array();
                try {
                    r11.A00.init(1, r11.A01, new GCMParameterSpec(128, C146924m.A00(r11.A02, j)));
                    r11.A00.updateAAD(array2);
                    byte[] doFinal = r11.A00.doFinal(array, 0, length);
                    r7.A00++;
                    try {
                        ByteBuffer allocate3 = ByteBuffer.allocate(doFinal.length + 5);
                        allocate3.put(allocate2.array());
                        allocate3.put(doFinal);
                        r7.A02.write(allocate3.array());
                    } catch (SocketException | SocketTimeoutException e) {
                        throw new AnonymousClass25A((byte) 80, new SSLException(e), true);
                    } catch (IOException e2) {
                        throw new AnonymousClass25A((byte) 80, new SSLException(e2));
                    }
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e3) {
                    throw new AnonymousClass25A((byte) 80, new SSLException(e3));
                }
            } else {
                throw new AnonymousClass25A((byte) 22, new SSLException(AnonymousClass006.A05("record size cannot exceed max length. ", i2, " > ", 16384)));
            }
        } else {
            AnonymousClass24M r1 = (AnonymousClass24M) this;
            if (i2 <= 16384) {
                try {
                    ByteBuffer allocate4 = ByteBuffer.allocate(i2 + 5);
                    allocate4.put(b);
                    if (b != 22 || r1.A01) {
                        s = 771;
                    } else {
                        r1.A01 = true;
                        s = 769;
                    }
                    allocate4.putShort(s);
                    allocate4.put(AnonymousClass24K.A06(i2));
                    allocate4.put(bArr, i, i2);
                    r1.A00.write(allocate4.array());
                } catch (SocketException | SocketTimeoutException e4) {
                    throw new AnonymousClass25A((byte) 80, new SSLException(e4), true);
                } catch (IOException e5) {
                    throw new AnonymousClass25A((byte) 80, new SSLException(e5));
                }
            } else {
                throw new AnonymousClass25A((byte) 22, new SSLException(AnonymousClass006.A05("record size cannot exceed max length. ", i2, " > ", 16384)));
            }
        }
    }
}
