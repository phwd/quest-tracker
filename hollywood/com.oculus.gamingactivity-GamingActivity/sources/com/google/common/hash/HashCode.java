package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.primitives.UnsignedInts;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public abstract class HashCode {
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    public abstract byte[] asBytes();

    public abstract int asInt();

    public abstract long asLong();

    public abstract int bits();

    /* access modifiers changed from: package-private */
    public abstract boolean equalsSameBits(HashCode hashCode);

    public abstract long padToLong();

    /* access modifiers changed from: package-private */
    public abstract void writeBytesToImpl(byte[] bArr, int i, int i2);

    HashCode() {
    }

    @CanIgnoreReturnValue
    public int writeBytesTo(byte[] dest, int offset, int maxLength) {
        int maxLength2 = Ints.min(maxLength, bits() / 8);
        Preconditions.checkPositionIndexes(offset, offset + maxLength2, dest.length);
        writeBytesToImpl(dest, offset, maxLength2);
        return maxLength2;
    }

    /* access modifiers changed from: package-private */
    public byte[] getBytesInternal() {
        return asBytes();
    }

    public static HashCode fromInt(int hash) {
        return new IntHashCode(hash);
    }

    private static final class IntHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final int hash;

        IntHashCode(int hash2) {
            this.hash = hash2;
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return 32;
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            return new byte[]{(byte) this.hash, (byte) (this.hash >> 8), (byte) (this.hash >> 16), (byte) (this.hash >> 24)};
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            return this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            return UnsignedInts.toLong(this.hash);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.hash.HashCode
        public void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
            for (int i = 0; i < maxLength; i++) {
                dest[offset + i] = (byte) (this.hash >> (i * 8));
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.hash.HashCode
        public boolean equalsSameBits(HashCode that) {
            return this.hash == that.asInt();
        }
    }

    public static HashCode fromLong(long hash) {
        return new LongHashCode(hash);
    }

    private static final class LongHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final long hash;

        LongHashCode(long hash2) {
            this.hash = hash2;
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return 64;
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            return new byte[]{(byte) ((int) this.hash), (byte) ((int) (this.hash >> 8)), (byte) ((int) (this.hash >> 16)), (byte) ((int) (this.hash >> 24)), (byte) ((int) (this.hash >> 32)), (byte) ((int) (this.hash >> 40)), (byte) ((int) (this.hash >> 48)), (byte) ((int) (this.hash >> 56))};
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            return (int) this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            return this.hash;
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            return this.hash;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.hash.HashCode
        public void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
            for (int i = 0; i < maxLength; i++) {
                dest[offset + i] = (byte) ((int) (this.hash >> (i * 8)));
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.hash.HashCode
        public boolean equalsSameBits(HashCode that) {
            return this.hash == that.asLong();
        }
    }

    public static HashCode fromBytes(byte[] bytes) {
        boolean z = true;
        if (bytes.length < 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "A HashCode must contain at least 1 byte.");
        return fromBytesNoCopy((byte[]) bytes.clone());
    }

    static HashCode fromBytesNoCopy(byte[] bytes) {
        return new BytesHashCode(bytes);
    }

    /* access modifiers changed from: private */
    public static final class BytesHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final byte[] bytes;

        BytesHashCode(byte[] bytes2) {
            this.bytes = (byte[]) Preconditions.checkNotNull(bytes2);
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return this.bytes.length * 8;
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            return (byte[]) this.bytes.clone();
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            boolean z;
            if (this.bytes.length >= 4) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkState(z, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", this.bytes.length);
            return (this.bytes[0] & UnsignedBytes.MAX_VALUE) | ((this.bytes[1] & UnsignedBytes.MAX_VALUE) << 8) | ((this.bytes[2] & UnsignedBytes.MAX_VALUE) << 16) | ((this.bytes[3] & UnsignedBytes.MAX_VALUE) << 24);
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            Preconditions.checkState(this.bytes.length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", this.bytes.length);
            return padToLong();
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            long retVal = (long) (this.bytes[0] & UnsignedBytes.MAX_VALUE);
            for (int i = 1; i < Math.min(this.bytes.length, 8); i++) {
                retVal |= (((long) this.bytes[i]) & 255) << (i * 8);
            }
            return retVal;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.hash.HashCode
        public void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
            System.arraycopy(this.bytes, 0, dest, offset, maxLength);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.hash.HashCode
        public byte[] getBytesInternal() {
            return this.bytes;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.hash.HashCode
        public boolean equalsSameBits(HashCode that) {
            if (this.bytes.length != that.getBytesInternal().length) {
                return false;
            }
            boolean areEqual = true;
            for (int i = 0; i < this.bytes.length; i++) {
                areEqual &= this.bytes[i] == that.getBytesInternal()[i];
            }
            return areEqual;
        }
    }

    public static HashCode fromString(String string) {
        boolean z = true;
        Preconditions.checkArgument(string.length() >= 2, "input string (%s) must have at least 2 characters", string);
        if (string.length() % 2 != 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "input string (%s) must have an even number of characters", string);
        byte[] bytes = new byte[(string.length() / 2)];
        for (int i = 0; i < string.length(); i += 2) {
            bytes[i / 2] = (byte) ((decode(string.charAt(i)) << 4) + decode(string.charAt(i + 1)));
        }
        return fromBytesNoCopy(bytes);
    }

    private static int decode(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        }
        if (ch >= 'a' && ch <= 'f') {
            return (ch - 'a') + 10;
        }
        throw new IllegalArgumentException("Illegal hexadecimal character: " + ch);
    }

    public final boolean equals(@NullableDecl Object object) {
        if (!(object instanceof HashCode)) {
            return false;
        }
        HashCode that = (HashCode) object;
        if (bits() != that.bits() || !equalsSameBits(that)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        if (bits() >= 32) {
            return asInt();
        }
        byte[] bytes = getBytesInternal();
        int val = bytes[0] & UnsignedBytes.MAX_VALUE;
        for (int i = 1; i < bytes.length; i++) {
            val |= (bytes[i] & UnsignedBytes.MAX_VALUE) << (i * 8);
        }
        return val;
    }

    public final String toString() {
        byte[] bytes = getBytesInternal();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(hexDigits[(b >> 4) & 15]).append(hexDigits[b & Ascii.SI]);
        }
        return sb.toString();
    }
}
