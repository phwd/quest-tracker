package defpackage;

import android.content.pm.Signature;
import java.util.Collections;
import java.util.Set;

/* renamed from: qV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4412qV0 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f11144a;
    public final Signature b;
    public final Set c;

    public C4412qV0(boolean z, Signature signature, Set set) {
        this.f11144a = z;
        this.b = signature;
        this.c = Collections.unmodifiableSet(set);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SigCertInfo(");
        sb.append("isTrusted=");
        sb.append(this.f11144a);
        sb.append(", packageNames=[");
        int i = 0;
        for (String str : this.c) {
            i++;
            if (i > 1) {
                sb.append(",");
            }
            sb.append(str);
        }
        sb.append("]");
        sb.append(", signature=");
        sb.append(this.b.toCharsString());
        sb.append(")");
        return sb.toString();
    }
}
