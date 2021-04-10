package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.util.Objects;

/* renamed from: H41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H41 extends Animation {
    public final /* synthetic */ K41 F;

    public H41(K41 k41) {
        this.F = k41;
    }

    public void applyTransformation(float f, Transformation transformation) {
        Objects.requireNonNull(this.F);
        K41 k41 = this.F;
        int abs = (int) (k41.a0 - ((float) Math.abs(k41.S)));
        K41 k412 = this.F;
        int i = k412.R;
        this.F.j((i + ((int) (((float) (abs - i)) * f))) - k412.P.getTop(), false);
        float f2 = 1.0f - f;
        C2380ed0 ed0 = this.F.T.f9935J;
        if (f2 != ed0.q) {
            ed0.q = f2;
            ed0.a();
        }
    }
}
