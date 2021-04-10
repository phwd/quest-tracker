package X;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.hC  reason: case insensitive filesystem */
public final class C0408hC {
    public final int A00;
    @Nullable
    public final C0409hD A01;
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
        C0409hD hDVar = this.A01;
        if (hDVar == null) {
            str = "null";
        } else {
            str = hDVar.sha1Hash;
        }
        sb.append(str);
        sb.append(", sha2=");
        if (hDVar == null) {
            str2 = "null";
        } else {
            str2 = hDVar.sha256Hash;
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

    public C0408hC(int i, List<String> list, @Nullable C0409hD hDVar, @Nullable String str, @Nullable String str2) {
        this.A00 = i;
        this.A04 = Collections.unmodifiableList(list);
        this.A01 = hDVar;
        this.A03 = str;
        this.A02 = str2;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("At least one package name is required");
        }
    }
}
