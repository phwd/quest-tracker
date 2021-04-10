package defpackage;

import android.graphics.drawable.Drawable;
import java.util.Objects;

/* renamed from: w31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5361w31 {

    /* renamed from: a  reason: collision with root package name */
    public final Drawable f11515a;
    public final boolean b;
    public final boolean c;
    public final boolean d;

    public C5361w31(Drawable drawable, boolean z, boolean z2, boolean z3, int i, AbstractC5021u31 u31) {
        this.f11515a = drawable;
        this.c = z;
        this.d = z2;
        this.b = z3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C5361w31)) {
            return false;
        }
        C5361w31 w31 = (C5361w31) obj;
        return this.d == w31.d && this.c == w31.c && this.b == w31.b && Objects.equals(this.f11515a, w31.f11515a);
    }
}
