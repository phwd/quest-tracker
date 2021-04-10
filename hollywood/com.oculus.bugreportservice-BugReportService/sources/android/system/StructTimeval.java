package android.system;

import libcore.util.Objects;

public final class StructTimeval {
    public final long tv_sec;
    public final long tv_usec;

    private StructTimeval(long j, long j2) {
        this.tv_sec = j;
        this.tv_usec = j2;
        if (j2 < 0 || j2 > 999999) {
            throw new IllegalArgumentException("tv_usec value " + j2 + " is not in [0, 999999]");
        }
    }

    public static StructTimeval fromMillis(long j) {
        long j2 = j / 1000;
        long j3 = (j - (j2 * 1000)) * 1000;
        if (j < 0) {
            j2--;
            j3 += 1000000;
        }
        return new StructTimeval(j2, j3);
    }

    public long toMillis() {
        return (this.tv_sec * 1000) + (this.tv_usec / 1000);
    }

    public String toString() {
        return Objects.toString(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StructTimeval.class != obj.getClass()) {
            return false;
        }
        StructTimeval structTimeval = (StructTimeval) obj;
        return this.tv_sec == structTimeval.tv_sec && this.tv_usec == structTimeval.tv_usec;
    }

    public int hashCode() {
        return java.util.Objects.hash(Long.valueOf(this.tv_sec), Long.valueOf(this.tv_usec));
    }
}
