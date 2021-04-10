package sun.security.x509;

import java.io.ByteArrayInputStream;
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
    @Deprecated
    protected byte[] key = null;
    @Deprecated
    private int unusedBits = 0;

    public X509Key() {
    }

    private X509Key(AlgorithmId algid2, BitArray key2) throws InvalidKeyException {
        this.algid = algid2;
        setKey(key2);
        encode();
    }

    /* access modifiers changed from: protected */
    public void setKey(BitArray key2) {
        this.bitStringKey = (BitArray) key2.clone();
        this.key = key2.toByteArray();
        int remaining = key2.length() % 8;
        this.unusedBits = remaining == 0 ? 0 : 8 - remaining;
    }

    /* access modifiers changed from: protected */
    public BitArray getKey() {
        byte[] bArr = this.key;
        this.bitStringKey = new BitArray((bArr.length * 8) - this.unusedBits, bArr);
        return (BitArray) this.bitStringKey.clone();
    }

    public static PublicKey parse(DerValue in) throws IOException {
        if (in.tag == 48) {
            try {
                PublicKey subjectKey = buildX509Key(AlgorithmId.parse(in.data.getDerValue()), in.data.getUnalignedBitString());
                if (in.data.available() == 0) {
                    return subjectKey;
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
    public void parseKeyBits() throws IOException, InvalidKeyException {
        encode();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a0, code lost:
        throw new java.io.IOException(r2 + " [internal error]");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0089 A[ExcHandler: IllegalAccessException (e java.lang.IllegalAccessException), PHI: r2 
      PHI: (r2v3 'classname' java.lang.String) = (r2v2 'classname' java.lang.String), (r2v4 'classname' java.lang.String), (r2v4 'classname' java.lang.String) binds: [B:9:0x002f, B:16:0x0052, B:17:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a1 A[ExcHandler: InstantiationException (e java.lang.InstantiationException), Splitter:B:9:0x002f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.security.PublicKey buildX509Key(sun.security.x509.AlgorithmId r8, sun.security.util.BitArray r9) throws java.io.IOException, java.security.InvalidKeyException {
        /*
        // Method dump skipped, instructions count: 171
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.x509.X509Key.buildX509Key(sun.security.x509.AlgorithmId, sun.security.util.BitArray):java.security.PublicKey");
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algid.getName();
    }

    public AlgorithmId getAlgorithmId() {
        return this.algid;
    }

    public final void encode(DerOutputStream out) throws IOException {
        encode(out, this.algid, getKey());
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return (byte[]) getEncodedInternal().clone();
        } catch (InvalidKeyException e) {
            return null;
        }
    }

    public byte[] getEncodedInternal() throws InvalidKeyException {
        byte[] encoded = this.encodedKey;
        if (encoded != null) {
            return encoded;
        }
        try {
            DerOutputStream out = new DerOutputStream();
            encode(out);
            byte[] encoded2 = out.toByteArray();
            this.encodedKey = encoded2;
            return encoded2;
        } catch (IOException e) {
            throw new InvalidKeyException("IOException : " + e.getMessage());
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public byte[] encode() throws InvalidKeyException {
        return (byte[]) getEncodedInternal().clone();
    }

    public String toString() {
        HexDumpEncoder encoder = new HexDumpEncoder();
        return "algorithm = " + this.algid.toString() + ", unparsed keybits = \n" + encoder.encodeBuffer(this.key);
    }

    public void decode(InputStream in) throws InvalidKeyException {
        try {
            DerValue val = new DerValue(in);
            if (val.tag == 48) {
                this.algid = AlgorithmId.parse(val.data.getDerValue());
                setKey(val.data.getUnalignedBitString());
                parseKeyBits();
                if (val.data.available() != 0) {
                    throw new InvalidKeyException("excess key data");
                }
                return;
            }
            throw new InvalidKeyException("invalid key format");
        } catch (IOException e) {
            throw new InvalidKeyException("IOException: " + e.getMessage());
        }
    }

    public void decode(byte[] encodedKey2) throws InvalidKeyException {
        decode(new ByteArrayInputStream(encodedKey2));
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.write(getEncoded());
    }

    private void readObject(ObjectInputStream stream) throws IOException {
        try {
            decode(stream);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new IOException("deserialized key is invalid: " + e.getMessage());
        }
    }

    public boolean equals(Object obj) {
        byte[] otherEncoded;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Key)) {
            return false;
        }
        try {
            byte[] thisEncoded = getEncodedInternal();
            if (obj instanceof X509Key) {
                otherEncoded = ((X509Key) obj).getEncodedInternal();
            } else {
                otherEncoded = ((Key) obj).getEncoded();
            }
            return Arrays.equals(thisEncoded, otherEncoded);
        } catch (InvalidKeyException e) {
            return false;
        }
    }

    public int hashCode() {
        try {
            byte[] b1 = getEncodedInternal();
            int r = b1.length;
            for (byte b : b1) {
                r += (b & 255) * 37;
            }
            return r;
        } catch (InvalidKeyException e) {
            return 0;
        }
    }

    static void encode(DerOutputStream out, AlgorithmId algid2, BitArray key2) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        algid2.encode(tmp);
        tmp.putUnalignedBitString(key2);
        out.write((byte) 48, tmp);
    }
}
