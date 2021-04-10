package defpackage;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Set;

/* renamed from: ry  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4663ry extends AbstractC2168dK {
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final Set g = new HashSet();
    public final Set h = new HashSet();
    public final Set i = new HashSet();
    public C0972Py0 j;
    public AbstractC4559rK k;
    public AbstractC4559rK l;

    public C4663ry(boolean z, boolean z2, boolean z3, boolean z4) {
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = z4;
    }

    public int c(String str, String str2, String str3) {
        int i2 = (!this.c || !TextUtils.isEmpty(str)) ? 0 : 1;
        if (this.d && !e().a(str2)) {
            i2 |= 2;
        }
        return (!this.e || d().a(str3)) ? i2 : i2 | 4;
    }

    public final AbstractC4559rK d() {
        if (this.l == null) {
            this.l = new C4493qy(this);
        }
        return this.l;
    }

    public final AbstractC4559rK e() {
        if (this.k == null) {
            this.k = new C4322py(this);
        }
        return this.k;
    }
}
