package com.android.org.bouncycastle.asn1;

import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Properties;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.math.BigInteger;

public class ASN1Integer extends ASN1Primitive {
    private final byte[] bytes;

    public static ASN1Integer getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Integer)) {
            return (ASN1Integer) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1Integer) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Integer getInstance(ASN1TaggedObject obj, boolean explicit) {
        ASN1Primitive o = obj.getObject();
        if (explicit || (o instanceof ASN1Integer)) {
            return getInstance(o);
        }
        return new ASN1Integer(ASN1OctetString.getInstance(o).getOctets());
    }

    public ASN1Integer(long value) {
        this.bytes = BigInteger.valueOf(value).toByteArray();
    }

    public ASN1Integer(BigInteger value) {
        this.bytes = value.toByteArray();
    }

    public ASN1Integer(byte[] bytes2) {
        this(bytes2, true);
    }

    ASN1Integer(byte[] bytes2, boolean clone) {
        if (Properties.isOverrideSet("com.android.org.bouncycastle.asn1.allow_unsafe_integer") || !isMalformed(bytes2)) {
            this.bytes = clone ? Arrays.clone(bytes2) : bytes2;
            return;
        }
        throw new IllegalArgumentException("malformed integer");
    }

    static boolean isMalformed(byte[] bytes2) {
        if (bytes2.length > 1) {
            if (bytes2[0] == 0 && (bytes2[1] & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return true;
            }
            return bytes2[0] == -1 && (bytes2[1] & UnsignedBytes.MAX_POWER_OF_TWO) != 0;
        }
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    public BigInteger getPositiveValue() {
        return new BigInteger(1, this.bytes);
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.bytes.length) + 1 + this.bytes.length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream out) throws IOException {
        out.writeEncoded(2, this.bytes);
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Primitive
    public int hashCode() {
        int value = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.bytes;
            if (i == bArr.length) {
                return value;
            }
            value ^= (bArr[i] & UnsignedBytes.MAX_VALUE) << (i % 4);
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof ASN1Integer)) {
            return false;
        }
        return Arrays.areEqual(this.bytes, ((ASN1Integer) o).bytes);
    }

    public String toString() {
        return getValue().toString();
    }
}
