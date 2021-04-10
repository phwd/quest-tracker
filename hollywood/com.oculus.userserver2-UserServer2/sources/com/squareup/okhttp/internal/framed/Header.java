package com.squareup.okhttp.internal.framed;

import X.WM;

public final class Header {
    public static final WM RESPONSE_STATUS = WM.A04(":status");
    public static final WM TARGET_AUTHORITY = WM.A04(":authority");
    public static final WM TARGET_HOST = WM.A04(":host");
    public static final WM TARGET_METHOD = WM.A04(":method");
    public static final WM TARGET_PATH = WM.A04(":path");
    public static final WM TARGET_SCHEME = WM.A04(":scheme");
    public static final WM VERSION = WM.A04(":version");
    public final int hpackSize;
    public final WM name;
    public final WM value;

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
        this(WM.A04(str), WM.A04(str2));
    }

    public Header(WM wm, String str) {
        this(wm, WM.A04(str));
    }

    public Header(WM wm, WM wm2) {
        this.name = wm;
        this.value = wm2;
        this.hpackSize = wm.A07() + 32 + wm2.A07();
    }
}
