package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

final class MessageDigestHashFunction extends AbstractStreamingHashFunction implements Serializable {
    private final int bytes;
    private final MessageDigest prototype;
    private final boolean supportsClone;
    private final String toString;

    MessageDigestHashFunction(String algorithmName, String toString2) {
        this.prototype = getMessageDigest(algorithmName);
        this.bytes = this.prototype.getDigestLength();
        this.toString = (String) Preconditions.checkNotNull(toString2);
        this.supportsClone = supportsClone();
    }

    MessageDigestHashFunction(String algorithmName, int bytes2, String toString2) {
        this.toString = (String) Preconditions.checkNotNull(toString2);
        this.prototype = getMessageDigest(algorithmName);
        int maxLength = this.prototype.getDigestLength();
        Preconditions.checkArgument(bytes2 >= 4 && bytes2 <= maxLength, "bytes (%s) must be >= 4 and < %s", Integer.valueOf(bytes2), Integer.valueOf(maxLength));
        this.bytes = bytes2;
        this.supportsClone = supportsClone();
    }

    private boolean supportsClone() {
        try {
            this.prototype.clone();
            return true;
        } catch (CloneNotSupportedException e) {
            return false;
        }
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return this.bytes * 8;
    }

    public String toString() {
        return this.toString;
    }

    private static MessageDigest getMessageDigest(String algorithmName) {
        try {
            return MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        if (this.supportsClone) {
            try {
                return new MessageDigestHasher((MessageDigest) this.prototype.clone(), this.bytes);
            } catch (CloneNotSupportedException e) {
            }
        }
        return new MessageDigestHasher(getMessageDigest(this.prototype.getAlgorithm()), this.bytes);
    }

    private static final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final String algorithmName;
        private final int bytes;
        private final String toString;

        private SerializedForm(String algorithmName2, int bytes2, String toString2) {
            this.algorithmName = algorithmName2;
            this.bytes = bytes2;
            this.toString = toString2;
        }

        private Object readResolve() {
            return new MessageDigestHashFunction(this.algorithmName, this.bytes, this.toString);
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.prototype.getAlgorithm(), this.bytes, this.toString);
    }

    private static final class MessageDigestHasher extends AbstractByteHasher {
        private final int bytes;
        private final MessageDigest digest;
        private boolean done;

        private MessageDigestHasher(MessageDigest digest2, int bytes2) {
            this.digest = digest2;
            this.bytes = bytes2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte b) {
            checkNotDone();
            this.digest.update(b);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] b) {
            checkNotDone();
            this.digest.update(b);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte[] b, int off, int len) {
            checkNotDone();
            this.digest.update(b, off, len);
        }

        private void checkNotDone() {
            Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            checkNotDone();
            this.done = true;
            if (this.bytes == this.digest.getDigestLength()) {
                return HashCode.fromBytesNoCopy(this.digest.digest());
            }
            return HashCode.fromBytesNoCopy(Arrays.copyOf(this.digest.digest(), this.bytes));
        }
    }
}
