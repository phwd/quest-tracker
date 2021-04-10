package X;

import java.io.Serializable;
import javax.annotation.Nullable;

/* renamed from: X.0bQ  reason: invalid class name */
public final class AnonymousClass0bQ implements Serializable {
    public static final long serialVersionUID = 0;
    public String name;
    public String sha1Hash;
    @Nullable
    public String sha256Hash;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass0bQ)) {
            return false;
        }
        AnonymousClass0bQ r4 = (AnonymousClass0bQ) obj;
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

    public AnonymousClass0bQ(String str, @Nullable String str2, @Nullable String str3) {
        String str4;
        this.name = str;
        if (str2 == null) {
            str4 = "Must provide SHA1 key hash.";
        } else if (str2.length() == 27) {
            this.sha1Hash = str2;
            if (str3 == null) {
                return;
            }
            if (str3.length() == 43) {
                this.sha256Hash = str3;
                return;
            }
            str4 = "Invalid SHA256 key hash - should be 256-bit.";
        } else {
            str4 = "Invalid SHA1 key hash - should be 160-bit.";
        }
        throw new SecurityException(str4);
    }
}
