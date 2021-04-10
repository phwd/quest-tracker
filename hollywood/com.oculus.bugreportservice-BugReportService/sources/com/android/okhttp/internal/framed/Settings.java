package com.android.okhttp.internal.framed;

import java.util.Arrays;

public final class Settings {
    private int persistValue;
    private int persisted;
    private int set;
    private final int[] values = new int[10];

    /* access modifiers changed from: package-private */
    public void clear() {
        this.persisted = 0;
        this.persistValue = 0;
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    /* access modifiers changed from: package-private */
    public Settings set(int i, int i2, int i3) {
        if (i >= this.values.length) {
            return this;
        }
        int i4 = 1 << i;
        this.set |= i4;
        if ((i2 & 1) != 0) {
            this.persistValue |= i4;
        } else {
            this.persistValue &= ~i4;
        }
        if ((i2 & 2) != 0) {
            this.persisted |= i4;
        } else {
            this.persisted &= ~i4;
        }
        this.values[i] = i3;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isSet(int i) {
        return (this.set & (1 << i)) != 0;
    }

    /* access modifiers changed from: package-private */
    public int get(int i) {
        return this.values[i];
    }

    /* access modifiers changed from: package-private */
    public int flags(int i) {
        int i2 = isPersisted(i) ? 2 : 0;
        return persistValue(i) ? i2 | 1 : i2;
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
    public int getMaxConcurrentStreams(int i) {
        return (this.set & 16) != 0 ? this.values[4] : i;
    }

    /* access modifiers changed from: package-private */
    public int getMaxFrameSize(int i) {
        return (this.set & 32) != 0 ? this.values[5] : i;
    }

    /* access modifiers changed from: package-private */
    public int getInitialWindowSize(int i) {
        return (this.set & 128) != 0 ? this.values[7] : i;
    }

    /* access modifiers changed from: package-private */
    public boolean persistValue(int i) {
        return (this.persistValue & (1 << i)) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isPersisted(int i) {
        return (this.persisted & (1 << i)) != 0;
    }

    /* access modifiers changed from: package-private */
    public void merge(Settings settings) {
        for (int i = 0; i < 10; i++) {
            if (settings.isSet(i)) {
                set(i, settings.flags(i), settings.get(i));
            }
        }
    }
}
