package okhttp3.internal.http2;

import java.util.Arrays;

public final class Settings {
    private int set;
    private final int[] values = new int[10];

    /* access modifiers changed from: package-private */
    public void clear() {
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    /* access modifiers changed from: package-private */
    public Settings set(int id, int value) {
        if (id >= 0 && id < this.values.length) {
            this.set |= 1 << id;
            this.values[id] = value;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isSet(int id) {
        if ((this.set & (1 << id)) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int get(int id) {
        return this.values[id];
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return Integer.bitCount(this.set);
    }

    /* access modifiers changed from: package-private */
    public int getHeaderTableSize() {
        if ((this.set & 2) != 0) {
            return this.values[1];
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getMaxConcurrentStreams(int defaultValue) {
        return (this.set & 16) != 0 ? this.values[4] : defaultValue;
    }

    /* access modifiers changed from: package-private */
    public int getMaxFrameSize(int defaultValue) {
        return (this.set & 32) != 0 ? this.values[5] : defaultValue;
    }

    /* access modifiers changed from: package-private */
    public int getInitialWindowSize() {
        if ((this.set & 128) != 0) {
            return this.values[7];
        }
        return 65535;
    }

    /* access modifiers changed from: package-private */
    public void merge(Settings other) {
        for (int i = 0; i < 10; i++) {
            if (other.isSet(i)) {
                set(i, other.get(i));
            }
        }
    }
}
