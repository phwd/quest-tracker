package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* renamed from: U3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class U3 implements WA {

    /* renamed from: a  reason: collision with root package name */
    public final WA f9003a;
    public final float b;

    public U3(float f, WA wa) {
        while (wa instanceof U3) {
            wa = ((U3) wa).f9003a;
            f += ((U3) wa).b;
        }
        this.f9003a = wa;
        this.b = f;
    }

    @Override // defpackage.WA
    public float a(RectF rectF) {
        return Math.max(0.0f, this.f9003a.a(rectF) + this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof U3)) {
            return false;
        }
        U3 u3 = (U3) obj;
        return this.f9003a.equals(u3.f9003a) && this.b == u3.b;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f9003a, Float.valueOf(this.b)});
    }
}
