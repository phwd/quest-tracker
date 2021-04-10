package X;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: X.1nA  reason: invalid class name */
public final class AnonymousClass1nA {
    public final int A00 = 32;
    public final String A01 = "SHA-256";

    public final byte[] A00(byte[] bArr, byte[] bArr2) {
        String replace = this.A01.replace("-", "");
        try {
            Mac instance = Mac.getInstance(AnonymousClass006.A05("Hmac", replace));
            instance.init(new SecretKeySpec(bArr, AnonymousClass006.A05("Hmac", replace)));
            return instance.doFinal(bArr2);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public final byte[] A01(byte[] bArr, byte[] bArr2, int i) {
        String replace = this.A01.replace("-", "");
        try {
            int ceil = (int) Math.ceil(((double) i) / ((double) this.A00));
            byte[] bArr3 = new byte[0];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i2 = 1; i2 < ceil + 1; i2++) {
                Mac instance = Mac.getInstance(AnonymousClass006.A05("Hmac", replace));
                instance.init(new SecretKeySpec(bArr, AnonymousClass006.A05("Hmac", replace)));
                instance.update(bArr3);
                if (bArr2 != null) {
                    instance.update(bArr2);
                }
                instance.update((byte) i2);
                bArr3 = instance.doFinal();
                int min = Math.min(i, bArr3.length);
                byteArrayOutputStream.write(bArr3, 0, min);
                i -= min;
            }
            return byteArrayOutputStream.toByteArray();
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}
