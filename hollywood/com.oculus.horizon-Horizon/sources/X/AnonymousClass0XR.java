package X;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLException;

/* renamed from: X.0XR  reason: invalid class name */
public final class AnonymousClass0XR {
    public Cipher A00;
    public SecretKey A01;
    public byte[] A02;

    public static byte[] A00(byte[] bArr, long j) {
        int length = bArr.length;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        for (int i = 0; i < length - 8; i++) {
            allocate.put((byte) 0);
        }
        allocate.putLong(j);
        byte[] array = allocate.array();
        byte[] bArr2 = new byte[length];
        int i2 = 0;
        for (byte b : bArr) {
            bArr2[i2] = (byte) (b ^ array[i2]);
            i2++;
        }
        return bArr2;
    }

    public final void A01(byte[] bArr, byte[] bArr2) throws AnonymousClass1v5 {
        SSLException sSLException;
        int length;
        if (bArr == null || (length = bArr.length) != 16) {
            sSLException = new SSLException(AnonymousClass006.A05("Invalid key ", AnonymousClass1ut.A03(bArr)));
        } else if (bArr2 == null || bArr2.length != 12) {
            throw new AnonymousClass1v5((byte) 80, new SSLException(AnonymousClass006.A05("Invalid iv ", AnonymousClass1ut.A03(bArr2))));
        } else {
            try {
                this.A00 = Cipher.getInstance("AES/GCM/NoPadding");
                this.A02 = (byte[]) bArr2.clone();
                this.A01 = new SecretKeySpec(bArr, 0, length, "AES");
                return;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                sSLException = new SSLException("Unable to initialize cipher", e);
            }
        }
        throw new AnonymousClass1v5((byte) 80, sSLException);
    }
}
