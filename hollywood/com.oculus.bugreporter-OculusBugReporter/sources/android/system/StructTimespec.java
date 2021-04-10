package android.system;

import libcore.util.Objects;

public final class StructTimespec implements Comparable<StructTimespec> {
    public final long tv_nsec;
    public final long tv_sec;

    public StructTimespec(long tv_sec2, long tv_nsec2) {
        this.tv_sec = tv_sec2;
        this.tv_nsec = tv_nsec2;
        if (tv_nsec2 < 0 || tv_nsec2 > 999999999) {
            throw new IllegalArgumentException("tv_nsec value " + tv_nsec2 + " is not in [0, 999999999]");
        }
    }

    public int compareTo(StructTimespec other) {
        long j = this.tv_sec;
        long j2 = other.tv_sec;
        if (j > j2) {
            return 1;
        }
        if (j < j2) {
            return -1;
        }
        long j3 = this.tv_nsec;
        long j4 = other.tv_nsec;
        if (j3 > j4) {
            return 1;
        }
        if (j3 < j4) {
            return -1;
        }
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StructTimespec that = (StructTimespec) o;
        if (this.tv_sec != that.tv_sec) {
            return false;
        }
        if (this.tv_nsec == that.tv_nsec) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.tv_sec;
        long j2 = this.tv_nsec;
        return (((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return Objects.toString(this);
    }
}
