package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.io.Serializable;
import java.util.zip.Checksum;

/* access modifiers changed from: package-private */
public final class ChecksumHashFunction extends AbstractStreamingHashFunction implements Serializable {
    private static final long serialVersionUID = 0;
    private final int bits;
    private final Supplier<? extends Checksum> checksumSupplier;
    private final String toString;

    ChecksumHashFunction(Supplier<? extends Checksum> checksumSupplier2, int bits2, String toString2) {
        this.checksumSupplier = (Supplier) Preconditions.checkNotNull(checksumSupplier2);
        Preconditions.checkArgument(bits2 == 32 || bits2 == 64, "bits (%s) must be either 32 or 64", Integer.valueOf(bits2));
        this.bits = bits2;
        this.toString = (String) Preconditions.checkNotNull(toString2);
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bits;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new ChecksumHasher((Checksum) this.checksumSupplier.get());
    }

    public String toString() {
        return this.toString;
    }

    private final class ChecksumHasher extends AbstractByteHasher {
        private final Checksum checksum;

        private ChecksumHasher(Checksum checksum2) {
            this.checksum = (Checksum) Preconditions.checkNotNull(checksum2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte b) {
            this.checksum.update(b);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] bytes, int off, int len) {
            this.checksum.update(bytes, off, len);
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            long value = this.checksum.getValue();
            if (ChecksumHashFunction.this.bits == 32) {
                return HashCode.fromInt((int) value);
            }
            return HashCode.fromLong(value);
        }
    }
}
