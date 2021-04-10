package X;

import androidx.annotation.RestrictTo;
import java.util.List;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0GE  reason: invalid class name */
public final class AnonymousClass0GE {
    public final String A00;
    public final List<String> A01;
    public final boolean A02;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AnonymousClass0GE) {
            AnonymousClass0GE r5 = (AnonymousClass0GE) obj;
            if (this.A02 == r5.A02 && this.A01.equals(r5.A01)) {
                String str = this.A00;
                boolean startsWith = str.startsWith("index_");
                String str2 = r5.A00;
                if (startsWith) {
                    return str2.startsWith("index_");
                }
                return str.equals(str2);
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        String str = this.A00;
        if (str.startsWith("index_")) {
            hashCode = "index_".hashCode();
        } else {
            hashCode = str.hashCode();
        }
        return (((hashCode * 31) + (this.A02 ? 1 : 0)) * 31) + this.A01.hashCode();
    }

    public final String toString() {
        return "Index{name='" + this.A00 + '\'' + ", unique=" + this.A02 + ", columns=" + this.A01 + '}';
    }

    public AnonymousClass0GE(String str, boolean z, List<String> list) {
        this.A00 = str;
        this.A02 = z;
        this.A01 = list;
    }
}
