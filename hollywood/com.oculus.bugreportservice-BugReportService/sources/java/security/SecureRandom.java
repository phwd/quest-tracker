package java.security;

import java.security.Provider;
import java.util.Iterator;
import java.util.Random;
import sun.security.jca.GetInstance;
import sun.security.jca.Providers;

public class SecureRandom extends Random {
    private static volatile SecureRandom seedGenerator = null;
    static final long serialVersionUID = 4940670005562187L;
    private String algorithm;
    private long counter;
    private MessageDigest digest = null;
    private Provider provider = null;
    private byte[] randomBytes;
    private int randomBytesUsed;
    private SecureRandomSpi secureRandomSpi = null;
    private byte[] state;

    public SecureRandom() {
        super(0);
        getDefaultPRNG(false, null);
    }

    private void getDefaultPRNG(boolean z, byte[] bArr) {
        String prngAlgorithm = getPrngAlgorithm();
        if (prngAlgorithm != null) {
            try {
                SecureRandom instance = getInstance(prngAlgorithm);
                this.secureRandomSpi = instance.getSecureRandomSpi();
                this.provider = instance.getProvider();
                if (z) {
                    this.secureRandomSpi.engineSetSeed(bArr);
                }
                if (SecureRandom.class == SecureRandom.class) {
                    this.algorithm = prngAlgorithm;
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("No SecureRandom implementation!");
        }
    }

    private SecureRandom(SecureRandomSpi secureRandomSpi2, Provider provider2, String str) {
        super(0);
        this.secureRandomSpi = secureRandomSpi2;
        this.provider = provider2;
        this.algorithm = str;
    }

    public static SecureRandom getInstance(String str) {
        GetInstance.Instance instance = GetInstance.getInstance("SecureRandom", SecureRandomSpi.class, str);
        return new SecureRandom((SecureRandomSpi) instance.impl, instance.provider, str);
    }

    /* access modifiers changed from: package-private */
    public SecureRandomSpi getSecureRandomSpi() {
        return this.secureRandomSpi;
    }

    public final Provider getProvider() {
        return this.provider;
    }

    @Override // java.util.Random
    public void setSeed(long j) {
        if (j != 0) {
            this.secureRandomSpi.engineSetSeed(longToByteArray(j));
        }
    }

    public synchronized void nextBytes(byte[] bArr) {
        this.secureRandomSpi.engineNextBytes(bArr);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.Random
    public final int next(int i) {
        int i2 = (i + 7) / 8;
        byte[] bArr = new byte[i2];
        nextBytes(bArr);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 << 8) + (bArr[i4] & 255);
        }
        return i3 >>> ((i2 * 8) - i);
    }

    public static byte[] getSeed(int i) {
        if (seedGenerator == null) {
            seedGenerator = new SecureRandom();
        }
        return seedGenerator.generateSeed(i);
    }

    public byte[] generateSeed(int i) {
        return this.secureRandomSpi.engineGenerateSeed(i);
    }

    private static byte[] longToByteArray(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((int) j);
            j >>= 8;
        }
        return bArr;
    }

    private static String getPrngAlgorithm() {
        for (Provider provider2 : Providers.getProviderList().providers()) {
            Iterator it = provider2.getServices().iterator();
            while (true) {
                if (it.hasNext()) {
                    Provider.Service service = (Provider.Service) it.next();
                    if (service.getType().equals("SecureRandom")) {
                        return service.getAlgorithm();
                    }
                }
            }
        }
        return null;
    }
}
