package sun.security.x509;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PublicKey;
import java.util.Arrays;
import sun.misc.HexDumpEncoder;
import sun.security.util.BitArray;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class X509Key implements PublicKey {
    private static final long serialVersionUID = -5359250853002055002L;
    protected AlgorithmId algid;
    private BitArray bitStringKey = null;
    protected byte[] encodedKey;
    protected byte[] key = null;
    private int unusedBits = 0;

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public X509Key() {
    }

    private X509Key(AlgorithmId algorithmId, BitArray bitArray) {
        this.algid = algorithmId;
        setKey(bitArray);
        encode();
    }

    /* access modifiers changed from: protected */
    public void setKey(BitArray bitArray) {
        this.bitStringKey = (BitArray) bitArray.clone();
        this.key = bitArray.toByteArray();
        int length = bitArray.length() % 8;
        this.unusedBits = length == 0 ? 0 : 8 - length;
    }

    /* access modifiers changed from: protected */
    public BitArray getKey() {
        byte[] bArr = this.key;
        this.bitStringKey = new BitArray((bArr.length * 8) - this.unusedBits, bArr);
        return (BitArray) this.bitStringKey.clone();
    }

    public static PublicKey parse(DerValue derValue) {
        if (derValue.tag == 48) {
            try {
                PublicKey buildX509Key = buildX509Key(AlgorithmId.parse(derValue.data.getDerValue()), derValue.data.getUnalignedBitString());
                if (derValue.data.available() == 0) {
                    return buildX509Key;
                }
                throw new IOException("excess subject key");
            } catch (InvalidKeyException e) {
                throw new IOException("subject key, " + e.getMessage(), e);
            }
        } else {
            throw new IOException("corrupt subject key");
        }
    }

    /* access modifiers changed from: protected */
    public void parseKeyBits() {
        encode();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:14|15|16|17|18|(1:20)(1:21)|(1:23)|24|(2:26|27)|35|37) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0054 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005a A[Catch:{ NoSuchAlgorithmException -> 0x0029, InvalidKeySpecException -> 0x001e, ClassNotFoundException | InstantiationException -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005f A[Catch:{ NoSuchAlgorithmException -> 0x0029, InvalidKeySpecException -> 0x001e, ClassNotFoundException | InstantiationException -> 0x0098 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.security.PublicKey buildX509Key(sun.security.x509.AlgorithmId r4, sun.security.util.BitArray r5) {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.x509.X509Key.buildX509Key(sun.security.x509.AlgorithmId, sun.security.util.BitArray):java.security.PublicKey");
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algid.getName();
    }

    public final void encode(DerOutputStream derOutputStream) {
        encode(derOutputStream, this.algid, getKey());
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return (byte[]) getEncodedInternal().clone();
        } catch (InvalidKeyException unused) {
            return null;
        }
    }

    public byte[] getEncodedInternal() {
        byte[] bArr = this.encodedKey;
        if (bArr == null) {
            try {
                DerOutputStream derOutputStream = new DerOutputStream();
                encode(derOutputStream);
                bArr = derOutputStream.toByteArray();
                this.encodedKey = bArr;
            } catch (IOException e) {
                throw new InvalidKeyException("IOException : " + e.getMessage());
            }
        }
        return bArr;
    }

    public byte[] encode() {
        return (byte[]) getEncodedInternal().clone();
    }

    public String toString() {
        HexDumpEncoder hexDumpEncoder = new HexDumpEncoder();
        StringBuilder sb = new StringBuilder();
        sb.append("algorithm = ");
        sb.append(this.algid.toString());
        sb.append(", unparsed keybits = \n");
        hexDumpEncoder.encodeBuffer(this.key);
        throw null;
    }

    public void decode(InputStream inputStream) {
        try {
            DerValue derValue = new DerValue(inputStream);
            if (derValue.tag == 48) {
                this.algid = AlgorithmId.parse(derValue.data.getDerValue());
                setKey(derValue.data.getUnalignedBitString());
                parseKeyBits();
                if (derValue.data.available() != 0) {
                    throw new InvalidKeyException("excess key data");
                }
                return;
            }
            throw new InvalidKeyException("invalid key format");
        } catch (IOException e) {
            throw new InvalidKeyException("IOException: " + e.getMessage());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.write(getEncoded());
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        try {
            decode(objectInputStream);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new IOException("deserialized key is invalid: " + e.getMessage());
        }
    }

    public boolean equals(Object obj) {
        byte[] bArr;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Key)) {
            return false;
        }
        try {
            byte[] encodedInternal = getEncodedInternal();
            if (obj instanceof X509Key) {
                bArr = ((X509Key) obj).getEncodedInternal();
            } else {
                bArr = ((Key) obj).getEncoded();
            }
            return Arrays.equals(encodedInternal, bArr);
        } catch (InvalidKeyException unused) {
            return false;
        }
    }

    public int hashCode() {
        try {
            byte[] encodedInternal = getEncodedInternal();
            int length = encodedInternal.length;
            for (byte b : encodedInternal) {
                length += (b & 255) * 37;
            }
            return length;
        } catch (InvalidKeyException unused) {
            return 0;
        }
    }

    static void encode(DerOutputStream derOutputStream, AlgorithmId algorithmId, BitArray bitArray) {
        DerOutputStream derOutputStream2 = new DerOutputStream();
        algorithmId.encode(derOutputStream2);
        derOutputStream2.putUnalignedBitString(bitArray);
        derOutputStream.write((byte) 48, derOutputStream2);
    }
}
