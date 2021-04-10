package okhttp3;

import X.AnonymousClass006;
import okhttp3.internal.Util;

public final class Challenge {
    public final String realm;
    public final String scheme;

    public boolean equals(Object obj) {
        if (obj instanceof Challenge) {
            Challenge challenge = (Challenge) obj;
            if (!Util.equal(this.scheme, challenge.scheme) || !Util.equal(this.realm, challenge.realm)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        String str = this.realm;
        int i2 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i3 = (899 + i) * 31;
        String str2 = this.scheme;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return i3 + i2;
    }

    public String toString() {
        return AnonymousClass006.A0B(this.scheme, " realm=\"", this.realm, "\"");
    }

    public Challenge(String str, String str2) {
        this.scheme = str;
        this.realm = str2;
    }

    public String realm() {
        return this.realm;
    }

    public String scheme() {
        return this.scheme;
    }
}
