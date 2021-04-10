package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* renamed from: BL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BL0 implements WA {

    /* renamed from: a  reason: collision with root package name */
    public final float f7732a;

    public BL0(float f) {
        this.f7732a = f;
    }

    @Override // defpackage.WA
    public float a(RectF rectF) {
        return rectF.height() * this.f7732a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BL0)) {
            return false;
        }
        return this.f7732a == ((BL0) obj).f7732a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f7732a)});
    }
}
