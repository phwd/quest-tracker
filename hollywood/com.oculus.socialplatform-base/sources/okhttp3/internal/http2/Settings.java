package okhttp3.internal.http2;

import java.util.Arrays;

public final class Settings {
    public static final int COUNT = 10;
    public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
    public static final int ENABLE_PUSH = 2;
    public static final int HEADER_TABLE_SIZE = 1;
    public static final int INITIAL_WINDOW_SIZE = 7;
    public static final int MAX_CONCURRENT_STREAMS = 4;
    public static final int MAX_FRAME_SIZE = 5;
    public static final int MAX_HEADER_LIST_SIZE = 6;
    public int set;
    public final int[] values = new int[10];

    public void clear() {
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    public boolean isSet(int i) {
        return ((1 << i) & this.set) != 0;
    }

    public void merge(Settings settings) {
        int i = 0;
        do {
            if (settings.isSet(i)) {
                set(i, settings.values[i]);
            }
            i++;
        } while (i < 10);
    }

    public int get(int i) {
        return this.values[i];
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000f A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getEnablePush(boolean r5) {
        /*
            r4 = this;
            int r0 = r4.set
            r0 = r0 & 4
            r3 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0011
            int[] r1 = r4.values
            r0 = 2
            r0 = r1[r0]
            if (r0 != r2) goto L_0x0010
        L_0x000f:
            r3 = 1
        L_0x0010:
            return r3
        L_0x0011:
            if (r5 == 0) goto L_0x0010
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Settings.getEnablePush(boolean):boolean");
    }

    public int getHeaderTableSize() {
        if ((this.set & 2) != 0) {
            return this.values[1];
        }
        return -1;
    }

    public int getInitialWindowSize() {
        if ((this.set & 128) != 0) {
            return this.values[7];
        }
        return DEFAULT_INITIAL_WINDOW_SIZE;
    }

    public int getMaxConcurrentStreams(int i) {
        if ((this.set & 16) != 0) {
            return this.values[4];
        }
        return i;
    }

    public int getMaxFrameSize(int i) {
        if ((this.set & 32) != 0) {
            return this.values[5];
        }
        return i;
    }

    public int getMaxHeaderListSize(int i) {
        if ((this.set & 64) != 0) {
            return this.values[6];
        }
        return i;
    }

    public Settings set(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.values;
            if (i < iArr.length) {
                this.set = (1 << i) | this.set;
                iArr[i] = i2;
            }
        }
        return this;
    }

    public int size() {
        return Integer.bitCount(this.set);
    }
}
