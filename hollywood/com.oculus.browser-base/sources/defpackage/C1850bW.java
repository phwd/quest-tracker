package defpackage;

import com.google.android.gms.common.Feature;
import java.util.Arrays;

/* renamed from: bW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1850bW {

    /* renamed from: a  reason: collision with root package name */
    public final C4861t7 f9544a;
    public final Feature b;

    public C1850bW(C4861t7 t7Var, Feature feature, C2826hB1 hb1) {
        this.f9544a = t7Var;
        this.b = feature;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof C1850bW)) {
            C1850bW bWVar = (C1850bW) obj;
            if (!AbstractC0895Oq0.a(this.f9544a, bWVar.f9544a) || !AbstractC0895Oq0.a(this.b, bWVar.b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f9544a, this.b});
    }

    public final String toString() {
        C0834Nq0 nq0 = new C0834Nq0(this, null);
        nq0.a("key", this.f9544a);
        nq0.a("feature", this.b);
        return nq0.toString();
    }
}
