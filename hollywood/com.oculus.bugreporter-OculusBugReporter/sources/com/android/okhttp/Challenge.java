package com.android.okhttp;

import com.android.okhttp.internal.Util;

public final class Challenge {
    private final String realm;
    private final String scheme;

    public Challenge(String scheme2, String realm2) {
        this.scheme = scheme2;
        this.realm = realm2;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getRealm() {
        return this.realm;
    }

    public boolean equals(Object o) {
        return (o instanceof Challenge) && Util.equal(this.scheme, ((Challenge) o).scheme) && Util.equal(this.realm, ((Challenge) o).realm);
    }

    public int hashCode() {
        int i = 29 * 31;
        String str = this.realm;
        int i2 = 0;
        int result = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.scheme;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return result + i2;
    }

    public String toString() {
        return this.scheme + " realm=\"" + this.realm + "\"";
    }
}
