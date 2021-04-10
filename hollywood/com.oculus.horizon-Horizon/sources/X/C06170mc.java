package X;

import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/* renamed from: X.0mc  reason: invalid class name and case insensitive filesystem */
public final class C06170mc extends AnonymousClass0Y8 {
    public final KeyPair A00;

    @Override // X.AnonymousClass0Y8
    public final String A00() {
        return C01910Xz.A01(this.A00.getPrivate().getEncoded());
    }

    @Override // X.AnonymousClass0Y8
    public final String A01() {
        return C01910Xz.A01(this.A00.getPublic().getEncoded());
    }

    @Override // X.AnonymousClass0Y8
    public final byte[] A02() {
        KeyPair keyPair = this.A00;
        byte[] byteArray = ((ECPublicKey) keyPair.getPublic()).getW().getAffineX().toByteArray();
        byte[] byteArray2 = ((ECPublicKey) keyPair.getPublic()).getW().getAffineY().toByteArray();
        byte[] bArr = new byte[65];
        int i = 0;
        bArr[0] = 64;
        int length = byteArray.length;
        int max = Math.max(32 - length, 0) + 1;
        int length2 = byteArray2.length;
        int max2 = Math.max(32 - length2, 0) + 1;
        int i2 = 0;
        if (32 < length) {
            i2 = 1;
        }
        if (32 < length2) {
            i = 1;
        }
        System.arraycopy(byteArray, i2, bArr, max, length - i2);
        System.arraycopy(byteArray2, i, bArr, max2 + 32, length2 - i);
        return bArr;
    }

    @Override // X.AnonymousClass0Y8
    public final byte[] A03(byte[] bArr) throws SignatureException, AnonymousClass0Y1 {
        KeyPair keyPair = this.A00;
        if (keyPair != null) {
            try {
                Signature instance = Signature.getInstance("SHA256withECDSA");
                instance.initSign(keyPair.getPrivate());
                instance.update(super.A00.getEncoded());
                instance.update(this.A01.getEncoded());
                instance.update(bArr);
                try {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(instance.sign());
                    try {
                        int read = byteArrayInputStream.read();
                        if (read == 16 || read == 48) {
                            int read2 = byteArrayInputStream.read();
                            if ((((byte) read2) & 128) > 0) {
                                long j = (long) (read2 & 295);
                                if (j != byteArrayInputStream.skip(j)) {
                                    throw new IOException("Could not parse extra length bits");
                                }
                            } else if (byteArrayInputStream.available() != read2) {
                                throw new IOException("Available bytes not equal to length flag");
                            }
                            if (byteArrayInputStream.read() == 2) {
                                int read3 = byteArrayInputStream.read();
                                byte[] bArr2 = new byte[read3];
                                byteArrayInputStream.read(bArr2, 0, read3);
                                if (byteArrayInputStream.read() == 2) {
                                    int read4 = byteArrayInputStream.read();
                                    byte[] bArr3 = new byte[read4];
                                    byteArrayInputStream.read(bArr3, 0, read4);
                                    Pair pair = new Pair(bArr2, bArr3);
                                    byteArrayInputStream.close();
                                    byte[] bArr4 = (byte[]) pair.first;
                                    byte[] bArr5 = (byte[]) pair.second;
                                    byte[] bArr6 = new byte[64];
                                    int length = bArr4.length;
                                    int i = 0;
                                    int max = Math.max(32 - length, 0);
                                    int length2 = bArr5.length;
                                    int max2 = Math.max(32 - length2, 0);
                                    int i2 = 0;
                                    if (32 < length) {
                                        i2 = 1;
                                    }
                                    if (32 < length2) {
                                        i = 1;
                                    }
                                    System.arraycopy(bArr4, i2, bArr6, max, length - i2);
                                    System.arraycopy(bArr5, i, bArr6, max2 + 32, bArr5.length - i);
                                    return bArr6;
                                }
                                throw new IOException("No integer sequence tag for s");
                            }
                            throw new IOException("No integer sequence sag for r");
                        }
                        throw new IOException("Sequence tag not found");
                    } catch (Throwable unused) {
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Malformed signature: ", e);
                }
            } catch (Throwable th) {
                throw new SignatureException("Could not produce valid signature", th);
            }
        } else {
            throw new AnonymousClass0Y1();
        }
        throw th;
    }

    public C06170mc() {
        super(AnonymousClass0Y7.PROVIDED_TOKEN_BINDING, AnonymousClass0Y9.ECDSAP256);
        try {
            ECGenParameterSpec eCGenParameterSpec = new ECGenParameterSpec("secp256r1");
            KeyPairGenerator instance = KeyPairGenerator.getInstance("EC");
            instance.initialize(eCGenParameterSpec);
            try {
                this.A00 = instance.generateKeyPair();
            } catch (AnonymousClass0Y0 unused) {
                throw new RuntimeException("Unable to create key pair for secp256r1");
            }
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
            throw new AnonymousClass0Y0(e);
        }
    }

    public C06170mc(byte[] bArr, byte[] bArr2) {
        super(AnonymousClass0Y7.PROVIDED_TOKEN_BINDING, AnonymousClass0Y9.ECDSAP256);
        try {
            KeyFactory instance = KeyFactory.getInstance("EC");
            this.A00 = new KeyPair(instance.generatePublic(new X509EncodedKeySpec(bArr)), instance.generatePrivate(new PKCS8EncodedKeySpec(bArr2)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Unable to create key pair from data", e);
        }
    }
}
