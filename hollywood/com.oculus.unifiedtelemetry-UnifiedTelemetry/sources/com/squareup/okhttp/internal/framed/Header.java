package com.squareup.okhttp.internal.framed;

import X.ci;

public final class Header {
    public static final ci RESPONSE_STATUS = ci.A04(":status");
    public static final ci TARGET_AUTHORITY = ci.A04(":authority");
    public static final ci TARGET_HOST = ci.A04(":host");
    public static final ci TARGET_METHOD = ci.A04(":method");
    public static final ci TARGET_PATH = ci.A04(":path");
    public static final ci TARGET_SCHEME = ci.A04(":scheme");
    public static final ci VERSION = ci.A04(":version");
    public final int hpackSize;
    public final ci name;
    public final ci value;

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
        this(ci.A04(str), ci.A04(str2));
    }

    public Header(ci ciVar, String str) {
        this(ciVar, ci.A04(str));
    }

    public Header(ci ciVar, ci ciVar2) {
        this.name = ciVar;
        this.value = ciVar2;
        this.hpackSize = ciVar.A07() + 32 + ciVar2.A07();
    }
}
