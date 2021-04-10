package java.security;

import sun.security.jca.Providers;

public abstract class MessageDigest extends MessageDigestSpi {
    private String algorithm;
    private Provider provider;
    private int state = 0;

    protected MessageDigest(String str) {
        this.algorithm = str;
    }

    public static MessageDigest getInstance(String str) {
        MessageDigest messageDigest;
        try {
            Object[] impl = Security.getImpl(str, "MessageDigest", (String) null);
            if (impl[0] instanceof MessageDigest) {
                messageDigest = (MessageDigest) impl[0];
            } else {
                messageDigest = new Delegate((MessageDigestSpi) impl[0], str);
            }
            messageDigest.provider = (Provider) impl[1];
            return messageDigest;
        } catch (NoSuchProviderException unused) {
            throw new NoSuchAlgorithmException(str + " not found");
        }
    }

    public static MessageDigest getInstance(String str, Provider provider2) {
        if (provider2 != null) {
            Providers.checkBouncyCastleDeprecation(provider2, "MessageDigest", str);
            Object[] impl = Security.getImpl(str, "MessageDigest", provider2);
            if (impl[0] instanceof MessageDigest) {
                MessageDigest messageDigest = (MessageDigest) impl[0];
                messageDigest.provider = (Provider) impl[1];
                return messageDigest;
            }
            Delegate delegate = new Delegate((MessageDigestSpi) impl[0], str);
            ((MessageDigest) delegate).provider = (Provider) impl[1];
            return delegate;
        }
        throw new IllegalArgumentException("missing provider");
    }

    public void update(byte b) {
        engineUpdate(b);
        this.state = 1;
    }

    public void update(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("No input buffer given");
        } else if (bArr.length - i >= i2) {
            engineUpdate(bArr, i, i2);
            this.state = 1;
        } else {
            throw new IllegalArgumentException("Input buffer too short");
        }
    }

    public void update(byte[] bArr) {
        engineUpdate(bArr, 0, bArr.length);
        this.state = 1;
    }

    public byte[] digest() {
        byte[] engineDigest = engineDigest();
        this.state = 0;
        return engineDigest;
    }

    public byte[] digest(byte[] bArr) {
        update(bArr);
        return digest();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.algorithm);
        sb.append(" Message Digest from ");
        sb.append(this.provider.getName());
        sb.append(", ");
        int i = this.state;
        if (i == 0) {
            sb.append("<initialized>");
        } else if (i == 1) {
            sb.append("<in progress>");
        }
        return sb.toString();
    }

    public static boolean isEqual(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        return i == 0;
    }

    public void reset() {
        engineReset();
        this.state = 0;
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.MessageDigestSpi
    public Object clone() {
        if (this instanceof Cloneable) {
            return super.clone();
        }
        throw new CloneNotSupportedException();
    }

    /* access modifiers changed from: package-private */
    public static class Delegate extends MessageDigest {
        private MessageDigestSpi digestSpi;

        public Delegate(MessageDigestSpi messageDigestSpi, String str) {
            super(str);
            this.digestSpi = messageDigestSpi;
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() {
            MessageDigestSpi messageDigestSpi = this.digestSpi;
            if (messageDigestSpi instanceof Cloneable) {
                Delegate delegate = new Delegate((MessageDigestSpi) messageDigestSpi.clone(), ((MessageDigest) this).algorithm);
                ((MessageDigest) delegate).provider = ((MessageDigest) this).provider;
                ((MessageDigest) delegate).state = ((MessageDigest) this).state;
                return delegate;
            }
            throw new CloneNotSupportedException();
        }

        /* access modifiers changed from: protected */
        @Override // java.security.MessageDigestSpi
        public void engineUpdate(byte b) {
            this.digestSpi.engineUpdate(b);
        }

        /* access modifiers changed from: protected */
        @Override // java.security.MessageDigestSpi
        public void engineUpdate(byte[] bArr, int i, int i2) {
            this.digestSpi.engineUpdate(bArr, i, i2);
        }

        /* access modifiers changed from: protected */
        @Override // java.security.MessageDigestSpi
        public byte[] engineDigest() {
            return this.digestSpi.engineDigest();
        }

        /* access modifiers changed from: protected */
        @Override // java.security.MessageDigestSpi
        public void engineReset() {
            this.digestSpi.engineReset();
        }
    }
}
