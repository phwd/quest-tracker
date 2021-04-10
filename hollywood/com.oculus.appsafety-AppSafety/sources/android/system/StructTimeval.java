package android.system;

import libcore.util.Objects;

public final class StructTimeval {
    public final long tv_sec;
    public final long tv_usec;

    private StructTimeval(long tv_sec2, long tv_usec2) {
        this.tv_sec = tv_sec2;
        this.tv_usec = tv_usec2;
        if (tv_usec2 < 0 || tv_usec2 > 999999) {
            throw new IllegalArgumentException("tv_usec value " + tv_usec2 + " is not in [0, 999999]");
        }
    }

    public static StructTimeval fromMillis(long millis) {
        long tv_sec2 = millis / 1000;
        long tv_usec2 = (millis - (tv_sec2 * 1000)) * 1000;
        if (millis < 0) {
            tv_sec2--;
            tv_usec2 += 1000000;
        }
        return new StructTimeval(tv_sec2, tv_usec2);
    }

    public long toMillis() {
        return (this.tv_sec * 1000) + (this.tv_usec / 1000);
    }

    public String toString() {
        return Objects.toString(this);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StructTimeval that = (StructTimeval) o;
        if (this.tv_sec == that.tv_sec && this.tv_usec == that.tv_usec) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return java.util.Objects.hash(Long.valueOf(this.tv_sec), Long.valueOf(this.tv_usec));
    }
}
