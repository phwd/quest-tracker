package X;

import com.facebook.internal.Utility;
import java.io.Serializable;

/* renamed from: X.1gV  reason: invalid class name */
public class AnonymousClass1gV implements Serializable {
    public static final long serialVersionUID = 1;
    public final String accessTokenString;
    public final String applicationId;

    private Object writeReplace() {
        return new AnonymousClass1hS(this.accessTokenString, this.applicationId);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass1gV)) {
            return false;
        }
        AnonymousClass1gV r4 = (AnonymousClass1gV) obj;
        if (!Utility.areObjectsEqual(r4.accessTokenString, this.accessTokenString) || !Utility.areObjectsEqual(r4.applicationId, this.applicationId)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        String str = this.accessTokenString;
        int i = 0;
        if (str == null) {
            hashCode = 0;
        } else {
            hashCode = str.hashCode();
        }
        String str2 = this.applicationId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode ^ i;
    }

    public AnonymousClass1gV(String str, String str2) {
        this.accessTokenString = Utility.isNullOrEmpty(str) ? null : str;
        this.applicationId = str2;
    }
}
