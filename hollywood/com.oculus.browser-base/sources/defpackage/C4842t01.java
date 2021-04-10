package defpackage;

import android.graphics.Rect;
import java.util.Objects;

/* renamed from: t01  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4842t01 implements Q31 {
    public final C01 F;
    public final Q31 G;

    public C4842t01(C01 c01, Q31 q31) {
        this.F = c01;
        this.G = q31;
    }

    @Override // defpackage.Q31
    public Object get() {
        C01 c01 = this.F;
        Q31 q31 = this.G;
        Objects.requireNonNull(c01);
        return Float.valueOf(((float) ((Rect) q31.get()).left) / c01.T);
    }
}
