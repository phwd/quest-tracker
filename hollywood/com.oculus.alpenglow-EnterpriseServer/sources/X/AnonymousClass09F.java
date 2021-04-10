package X;

import android.util.Base64;
import androidx.annotation.NonNull;
import java.util.List;

/* renamed from: X.09F  reason: invalid class name */
public final class AnonymousClass09F {
    public final String A00;
    public final String A01;
    public final String A02;
    public final String A03;
    public final List<List<byte[]>> A04;

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.A01 + ", mProviderPackage: " + this.A02 + ", mQuery: " + this.A03 + ", mCertificates:");
        int i = 0;
        while (true) {
            List<List<byte[]>> list = this.A04;
            if (i < list.size()) {
                sb.append(" [");
                List<byte[]> list2 = list.get(i);
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    sb.append(" \"");
                    sb.append(Base64.encodeToString(list2.get(i2), 0));
                    sb.append("\"");
                }
                sb.append(" ]");
                i++;
            } else {
                sb.append("}");
                sb.append(AnonymousClass006.A01("mCertificatesArray: ", 0));
                return sb.toString();
            }
        }
    }

    public AnonymousClass09F(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<List<byte[]>> list) {
        this.A01 = str;
        this.A02 = str2;
        this.A03 = str3;
        if (list != null) {
            this.A04 = list;
            this.A00 = AnonymousClass006.A09(str, "-", str2, "-", str3);
            return;
        }
        throw null;
    }
}
