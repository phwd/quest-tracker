package X;

import java.util.Collections;
import java.util.List;

/* renamed from: X.Jh  reason: case insensitive filesystem */
public final class C0194Jh {
    public final int A00;
    public final C0195Ji A01;
    public final String A02;
    public final String A03;
    public final List A04;

    public final String A00() {
        List list = this.A04;
        if (!list.isEmpty()) {
            return (String) list.iterator().next();
        }
        throw new IllegalStateException("Invalid AppIdentity object: no package names");
    }

    public final String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder("AppIdentity{uid=");
        sb.append(this.A00);
        sb.append(", packageNames=");
        sb.append(this.A04);
        sb.append(", sha1=");
        C0195Ji ji = this.A01;
        if (ji == null) {
            str = "null";
        } else {
            str = ji.sha1Hash;
        }
        sb.append(str);
        sb.append(", sha2=");
        if (ji == null) {
            str2 = "null";
        } else {
            str2 = ji.sha256Hash;
        }
        sb.append(str2);
        sb.append(", version=");
        String str3 = this.A03;
        if (str3 == null) {
            str3 = "null";
        }
        sb.append(str3);
        sb.append(", domain=");
        String str4 = this.A02;
        if (str4 == null) {
            str4 = "null";
        }
        sb.append(str4);
        sb.append('}');
        return sb.toString();
    }

    public C0194Jh(int i, List list, C0195Ji ji, String str, String str2) {
        this.A00 = i;
        this.A04 = Collections.unmodifiableList(list);
        this.A01 = ji;
        this.A03 = str;
        this.A02 = str2;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("At least one package name is required");
        }
    }
}
