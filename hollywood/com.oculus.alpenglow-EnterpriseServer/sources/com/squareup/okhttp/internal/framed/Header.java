package com.squareup.okhttp.internal.framed;

import X.C04610h7;

public final class Header {
    public static final C04610h7 RESPONSE_STATUS = C04610h7.A04(":status");
    public static final C04610h7 TARGET_AUTHORITY = C04610h7.A04(":authority");
    public static final C04610h7 TARGET_HOST = C04610h7.A04(":host");
    public static final C04610h7 TARGET_METHOD = C04610h7.A04(":method");
    public static final C04610h7 TARGET_PATH = C04610h7.A04(":path");
    public static final C04610h7 TARGET_SCHEME = C04610h7.A04(":scheme");
    public static final C04610h7 VERSION = C04610h7.A04(":version");
    public final int hpackSize;
    public final C04610h7 name;
    public final C04610h7 value;

    public boolean equals(Object obj) {
        if (!(obj instanceof Header)) {
            return false;
        }
        Header header = (Header) obj;
        if (!this.name.equals(header.name) || !this.value.equals(header.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((527 + this.name.hashCode()) * 31) + this.value.hashCode();
    }

    public String toString() {
        return String.format("%s: %s", this.name.A0A(), this.value.A0A());
    }

    public Header(String str, String str2) {
        this(C04610h7.A04(str), C04610h7.A04(str2));
    }

    public Header(C04610h7 r2, String str) {
        this(r2, C04610h7.A04(str));
    }

    public Header(C04610h7 r3, C04610h7 r4) {
        this.name = r3;
        this.value = r4;
        this.hpackSize = r3.A07() + 32 + r4.A07();
    }
}
