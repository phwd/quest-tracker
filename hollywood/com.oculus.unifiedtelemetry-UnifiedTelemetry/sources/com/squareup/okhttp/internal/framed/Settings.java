package com.squareup.okhttp.internal.framed;

import com.facebook.acra.util.HttpRequest;
import java.util.Arrays;

public final class Settings {
    public static final int CLIENT_CERTIFICATE_VECTOR_SIZE = 8;
    public static final int COUNT = 10;
    public static final int CURRENT_CWND = 5;
    public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65536;
    public static final int DOWNLOAD_BANDWIDTH = 2;
    public static final int DOWNLOAD_RETRANS_RATE = 6;
    public static final int ENABLE_PUSH = 2;
    public static final int FLAG_CLEAR_PREVIOUSLY_PERSISTED_SETTINGS = 1;
    public static final int FLOW_CONTROL_OPTIONS = 10;
    public static final int FLOW_CONTROL_OPTIONS_DISABLED = 1;
    public static final int HEADER_TABLE_SIZE = 1;
    public static final int INITIAL_WINDOW_SIZE = 7;
    public static final int MAX_CONCURRENT_STREAMS = 4;
    public static final int MAX_FRAME_SIZE = 5;
    public static final int MAX_HEADER_LIST_SIZE = 6;
    public static final int PERSISTED = 2;
    public static final int PERSIST_VALUE = 1;
    public static final int ROUND_TRIP_TIME = 3;
    public static final int UPLOAD_BANDWIDTH = 1;
    public int persistValue;
    public int persisted;
    public int set;
    public final int[] values = new int[10];

    public void clear() {
        this.persisted = 0;
        this.persistValue = 0;
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    public boolean isPersisted(int i) {
        return ((1 << i) & this.persisted) != 0;
    }

    public boolean isSet(int i) {
        return ((1 << i) & this.set) != 0;
    }

    public void merge(Settings settings) {
        int i = 0;
        do {
            if (settings.isSet(i)) {
                set(i, settings.flags(i), settings.values[i]);
            }
            i++;
        } while (i < 10);
    }

    public boolean persistValue(int i) {
        return ((1 << i) & this.persistValue) != 0;
    }

    public int get(int i) {
        return this.values[i];
    }

    public int getClientCertificateVectorSize(int i) {
        if ((this.set & HttpRequest.CHAR_ARRAY_BUFFER_SIZE) != 0) {
            return this.values[8];
        }
        return i;
    }

    public int getCurrentCwnd(int i) {
        if ((this.set & 32) != 0) {
            return this.values[5];
        }
        return i;
    }

    public int getDownloadBandwidth(int i) {
        if ((this.set & 4) != 0) {
            return this.values[2];
        }
        return i;
    }

    public int getDownloadRetransRate(int i) {
        if ((this.set & 64) != 0) {
            return this.values[6];
        }
        return i;
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
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.Settings.getEnablePush(boolean):boolean");
    }

    public int getHeaderTableSize() {
        if ((this.set & 2) != 0) {
            return this.values[1];
        }
        return -1;
    }

    public int getInitialWindowSize(int i) {
        if ((this.set & 128) != 0) {
            return this.values[7];
        }
        return i;
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

    public int getRoundTripTime(int i) {
        if ((this.set & 8) != 0) {
            return this.values[3];
        }
        return i;
    }

    public int getUploadBandwidth(int i) {
        if ((this.set & 2) != 0) {
            return this.values[1];
        }
        return i;
    }

    public boolean isFlowControlDisabled() {
        if ((this.set & 1024) == 0 || (this.values[10] & 1) == 0) {
            return false;
        }
        return true;
    }

    public Settings set(int i, int i2, int i3) {
        int i4;
        int i5;
        int[] iArr = this.values;
        if (i < iArr.length) {
            int i6 = 1 << i;
            this.set |= i6;
            if ((i2 & 1) != 0) {
                i4 = this.persistValue | i6;
            } else {
                i4 = this.persistValue & (i6 ^ -1);
            }
            this.persistValue = i4;
            if ((i2 & 2) != 0) {
                i5 = this.persisted | i6;
            } else {
                i5 = this.persisted & (i6 ^ -1);
            }
            this.persisted = i5;
            iArr[i] = i3;
        }
        return this;
    }

    public int size() {
        return Integer.bitCount(this.set);
    }

    public int flags(int i) {
        int i2 = 0;
        if (isPersisted(i)) {
            i2 = 2;
        }
        if (persistValue(i)) {
            return i2 | 1;
        }
        return i2;
    }
}
