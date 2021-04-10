package android.icu.util;

import java.util.concurrent.ConcurrentHashMap;

public final class VersionInfo implements Comparable {
    public static final VersionInfo ICU_DATA_VERSION = ICU_VERSION;
    public static final VersionInfo ICU_VERSION = getInstance(63, 2, 0, 0);
    private static final ConcurrentHashMap MAP_ = new ConcurrentHashMap();
    private static volatile String TZDATA_VERSION = null;
    public static final VersionInfo UCOL_BUILDER_VERSION = getInstance(9);
    public static final VersionInfo UCOL_RUNTIME_VERSION = getInstance(9);
    public static final VersionInfo UCOL_TAILORINGS_VERSION = getInstance(1);
    public static final VersionInfo UNICODE_10_0 = getInstance(10, 0, 0, 0);
    public static final VersionInfo UNICODE_11_0 = getInstance(11, 0, 0, 0);
    public static final VersionInfo UNICODE_1_0 = getInstance(1, 0, 0, 0);
    public static final VersionInfo UNICODE_1_0_1 = getInstance(1, 0, 1, 0);
    public static final VersionInfo UNICODE_1_1_0 = getInstance(1, 1, 0, 0);
    public static final VersionInfo UNICODE_1_1_5 = getInstance(1, 1, 5, 0);
    public static final VersionInfo UNICODE_2_0 = getInstance(2, 0, 0, 0);
    public static final VersionInfo UNICODE_2_1_2 = getInstance(2, 1, 2, 0);
    public static final VersionInfo UNICODE_2_1_5 = getInstance(2, 1, 5, 0);
    public static final VersionInfo UNICODE_2_1_8 = getInstance(2, 1, 8, 0);
    public static final VersionInfo UNICODE_2_1_9 = getInstance(2, 1, 9, 0);
    public static final VersionInfo UNICODE_3_0 = getInstance(3, 0, 0, 0);
    public static final VersionInfo UNICODE_3_0_1 = getInstance(3, 0, 1, 0);
    public static final VersionInfo UNICODE_3_1_0 = getInstance(3, 1, 0, 0);
    public static final VersionInfo UNICODE_3_1_1 = getInstance(3, 1, 1, 0);
    public static final VersionInfo UNICODE_3_2 = getInstance(3, 2, 0, 0);
    public static final VersionInfo UNICODE_4_0 = getInstance(4, 0, 0, 0);
    public static final VersionInfo UNICODE_4_0_1 = getInstance(4, 0, 1, 0);
    public static final VersionInfo UNICODE_4_1 = getInstance(4, 1, 0, 0);
    public static final VersionInfo UNICODE_5_0 = getInstance(5, 0, 0, 0);
    public static final VersionInfo UNICODE_5_1 = getInstance(5, 1, 0, 0);
    public static final VersionInfo UNICODE_5_2 = getInstance(5, 2, 0, 0);
    public static final VersionInfo UNICODE_6_0 = getInstance(6, 0, 0, 0);
    public static final VersionInfo UNICODE_6_1 = getInstance(6, 1, 0, 0);
    public static final VersionInfo UNICODE_6_2 = getInstance(6, 2, 0, 0);
    public static final VersionInfo UNICODE_6_3 = getInstance(6, 3, 0, 0);
    public static final VersionInfo UNICODE_7_0 = getInstance(7, 0, 0, 0);
    public static final VersionInfo UNICODE_8_0 = getInstance(8, 0, 0, 0);
    public static final VersionInfo UNICODE_9_0 = getInstance(9, 0, 0, 0);
    private static final VersionInfo UNICODE_VERSION = UNICODE_11_0;
    private int m_version_;

    private static int getInt(int i, int i2, int i3, int i4) {
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public static VersionInfo getInstance(String str) {
        int length = str.length();
        int[] iArr = {0, 0, 0, 0};
        int i = 0;
        int i2 = 0;
        while (i < 4 && i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt == '.') {
                i++;
            } else {
                char c = (char) (charAt - '0');
                if (c < 0 || c > '\t') {
                    throw new IllegalArgumentException("Invalid version number: Version number may be negative or greater than 255");
                }
                iArr[i] = iArr[i] * 10;
                iArr[i] = iArr[i] + c;
            }
            i2++;
        }
        if (i2 == length) {
            for (int i3 = 0; i3 < 4; i3++) {
                if (iArr[i3] < 0 || iArr[i3] > 255) {
                    throw new IllegalArgumentException("Invalid version number: Version number may be negative or greater than 255");
                }
            }
            return getInstance(iArr[0], iArr[1], iArr[2], iArr[3]);
        }
        throw new IllegalArgumentException("Invalid version number: String '" + str + "' exceeds version format");
    }

    public static VersionInfo getInstance(int i, int i2, int i3, int i4) {
        if (i < 0 || i > 255 || i2 < 0 || i2 > 255 || i3 < 0 || i3 > 255 || i4 < 0 || i4 > 255) {
            throw new IllegalArgumentException("Invalid version number: Version number may be negative or greater than 255");
        }
        int i5 = getInt(i, i2, i3, i4);
        Integer valueOf = Integer.valueOf(i5);
        VersionInfo versionInfo = (VersionInfo) MAP_.get(valueOf);
        if (versionInfo != null) {
            return versionInfo;
        }
        VersionInfo versionInfo2 = new VersionInfo(i5);
        VersionInfo versionInfo3 = (VersionInfo) MAP_.putIfAbsent(valueOf, versionInfo2);
        return versionInfo3 != null ? versionInfo3 : versionInfo2;
    }

    public static VersionInfo getInstance(int i) {
        return getInstance(i, 0, 0, 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(7);
        sb.append(getMajor());
        sb.append('.');
        sb.append(getMinor());
        sb.append('.');
        sb.append(getMilli());
        sb.append('.');
        sb.append(getMicro());
        return sb.toString();
    }

    public int getMajor() {
        return (this.m_version_ >> 24) & 255;
    }

    public int getMinor() {
        return (this.m_version_ >> 16) & 255;
    }

    public int getMilli() {
        return (this.m_version_ >> 8) & 255;
    }

    public int getMicro() {
        return this.m_version_ & 255;
    }

    public int hashCode() {
        return this.m_version_;
    }

    public int compareTo(VersionInfo versionInfo) {
        return this.m_version_ - versionInfo.m_version_;
    }

    private VersionInfo(int i) {
        this.m_version_ = i;
    }
}
