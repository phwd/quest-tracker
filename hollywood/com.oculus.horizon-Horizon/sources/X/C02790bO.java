package X;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0bO  reason: invalid class name and case insensitive filesystem */
public final class C02790bO {
    public final int A00;
    @Nullable
    public final AnonymousClass0bQ A01;
    @Nullable
    public final String A02;
    @Nullable
    public final String A03;
    public final List<String> A04;

    public static String A00(@Nullable C02790bO r1) {
        if (r1 == null) {
            return "no_app_identity";
        }
        if (r1.A01() == null) {
            return "null";
        }
        return r1.A01();
    }

    public final String A01() {
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
        AnonymousClass0bQ r2 = this.A01;
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

    public C02790bO(int i, List<String> list, @Nullable AnonymousClass0bQ r5, @Nullable String str, @Nullable String str2) {
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
