package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* renamed from: d  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2107d implements WA {

    /* renamed from: a  reason: collision with root package name */
    public final float f9737a;

    public C2107d(float f) {
        this.f9737a = f;
    }

    @Override // defpackage.WA
    public float a(RectF rectF) {
        return this.f9737a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2107d)) {
            return false;
        }
        return this.f9737a == ((C2107d) obj).f9737a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f9737a)});
    }
}
