package sun.nio.fs;

/* access modifiers changed from: package-private */
public class UnixFileKey {
    private final long st_dev;
    private final long st_ino;

    UnixFileKey(long st_dev2, long st_ino2) {
        this.st_dev = st_dev2;
        this.st_ino = st_ino2;
    }

    public int hashCode() {
        long j = this.st_dev;
        long j2 = this.st_ino;
        return ((int) (j ^ (j >>> 32))) + ((int) ((j2 >>> 32) ^ j2));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UnixFileKey)) {
            return false;
        }
        UnixFileKey other = (UnixFileKey) obj;
        if (this.st_dev == other.st_dev && this.st_ino == other.st_ino) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "(dev=" + Long.toHexString(this.st_dev) + ",ino=" + this.st_ino + ')';
    }
}
