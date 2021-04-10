package defpackage;

import android.util.Base64;
import java.util.List;
import java.util.Objects;

/* renamed from: DR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DR {

    /* renamed from: a  reason: collision with root package name */
    public final String f7892a;
    public final String b;
    public final String c;
    public final List d;
    public final int e;
    public final String f;

    public DR(String str, String str2, String str3, List list) {
        this.f7892a = str;
        this.b = str2;
        this.c = str3;
        Objects.requireNonNull(list);
        this.d = list;
        this.e = 0;
        this.f = str + "-" + str2 + "-" + str3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder i = AbstractC2531fV.i("FontRequest {mProviderAuthority: ");
        i.append(this.f7892a);
        i.append(", mProviderPackage: ");
        i.append(this.b);
        i.append(", mQuery: ");
        i.append(this.c);
        i.append(", mCertificates:");
        sb.append(i.toString());
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            sb.append(" [");
            List list = (List) this.d.get(i2);
            for (int i3 = 0; i3 < list.size(); i3++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString((byte[]) list.get(i3), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.e);
        return sb.toString();
    }

    public DR(String str, String str2, String str3, int i) {
        this.f7892a = str;
        this.b = str2;
        this.c = str3;
        this.d = null;
        if (i != 0) {
            this.e = i;
            this.f = str + "-" + str2 + "-" + str3;
            return;
        }
        throw new IllegalArgumentException();
    }
}
