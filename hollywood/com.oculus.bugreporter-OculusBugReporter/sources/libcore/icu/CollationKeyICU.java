package libcore.icu;

import java.text.CollationKey;

public final class CollationKeyICU extends CollationKey {
    private final android.icu.text.CollationKey key;

    public CollationKeyICU(String source, android.icu.text.CollationKey key2) {
        super(source);
        this.key = key2;
    }

    @Override // java.text.CollationKey
    public int compareTo(CollationKey other) {
        android.icu.text.CollationKey otherKey;
        if (other instanceof CollationKeyICU) {
            otherKey = ((CollationKeyICU) other).key;
        } else {
            otherKey = new android.icu.text.CollationKey(other.getSourceString(), other.toByteArray());
        }
        return this.key.compareTo(otherKey);
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof CollationKey)) {
            return false;
        }
        if (compareTo((CollationKey) object) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    @Override // java.text.CollationKey
    public byte[] toByteArray() {
        return this.key.toByteArray();
    }
}
