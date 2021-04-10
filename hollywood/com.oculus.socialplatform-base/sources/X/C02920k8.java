package X;

import java.io.Serializable;
import javax.annotation.Nullable;

/* renamed from: X.0k8  reason: invalid class name and case insensitive filesystem */
public final class C02920k8 implements Serializable {
    public static final long serialVersionUID = 0;
    public String name;
    public String sha1Hash;
    @Nullable
    public String sha256Hash;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C02920k8)) {
            return false;
        }
        C02920k8 r4 = (C02920k8) obj;
        String str = this.sha256Hash;
        String str2 = r4.sha256Hash;
        if (str != null && str2 != null) {
            return str.equals(str2);
        }
        String str3 = this.sha1Hash;
        String str4 = r4.sha1Hash;
        if (str3 == null || str4 == null || !str3.equals(str4)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        String str = this.sha256Hash;
        if (str == null) {
            str = this.sha1Hash;
        }
        return str.hashCode();
    }

    public C02920k8(String str, @Nullable String str2, @Nullable String str3) {
        this.name = str;
        if (str2 == null) {
            throw new SecurityException("Must provide SHA1 key hash.");
        } else if (str2.length() == 27) {
            this.sha1Hash = str2;
            if (str3 == null) {
                return;
            }
            if (str3.length() == 43) {
                this.sha256Hash = str3;
                return;
            }
            throw new SecurityException("Invalid SHA256 key hash - should be 256-bit.");
        } else {
            throw new SecurityException("Invalid SHA1 key hash - should be 160-bit.");
        }
    }
}
