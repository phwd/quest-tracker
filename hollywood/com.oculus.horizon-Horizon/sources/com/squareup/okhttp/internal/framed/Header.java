package com.squareup.okhttp.internal.framed;

import X.C07700vD;

public final class Header {
    public static final C07700vD RESPONSE_STATUS = C07700vD.A04(":status");
    public static final C07700vD TARGET_AUTHORITY = C07700vD.A04(":authority");
    public static final C07700vD TARGET_HOST = C07700vD.A04(":host");
    public static final C07700vD TARGET_METHOD = C07700vD.A04(":method");
    public static final C07700vD TARGET_PATH = C07700vD.A04(":path");
    public static final C07700vD TARGET_SCHEME = C07700vD.A04(":scheme");
    public static final C07700vD VERSION = C07700vD.A04(":version");
    public final int hpackSize;
    public final C07700vD name;
    public final C07700vD value;

    public String toString() {
        return String.format("%s: %s", this.name.A0A(), this.value.A0A());
    }

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

    public Header(String str, String str2) {
        this(C07700vD.A04(str), C07700vD.A04(str2));
    }

    public Header(C07700vD r2, String str) {
        this(r2, C07700vD.A04(str));
    }

    public Header(C07700vD r3, C07700vD r4) {
        this.name = r3;
        this.value = r4;
        this.hpackSize = r3.A07() + 32 + r4.A07();
    }
}
