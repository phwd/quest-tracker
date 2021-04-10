package X;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0ih  reason: invalid class name and case insensitive filesystem */
public final class C05130ih {
    public final int A00;
    @Nullable
    public final C05140ii A01;
    @Nullable
    public final String A02;
    @Nullable
    public final String A03;
    public final List<String> A04;

    public final String A00() {
        List<String> list = this.A04;
        if (!list.isEmpty()) {
            return list.iterator().next();
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
        C05140ii r2 = this.A01;
        if (r2 == null) {
            str = "null";
        } else {
            str = r2.sha1Hash;
        }
        sb.append(str);
        sb.append(", sha2=");
        if (r2 == null) {
            str2 = "null";
        } else {
            str2 = r2.sha256Hash;
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

    public C05130ih(int i, List<String> list, @Nullable C05140ii r5, @Nullable String str, @Nullable String str2) {
        this.A00 = i;
        this.A04 = Collections.unmodifiableList(list);
        this.A01 = r5;
        this.A03 = str;
        this.A02 = str2;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("At least one package name is required");
        }
    }
}
