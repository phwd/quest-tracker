package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: nV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3899nV0 extends Animation {
    public final /* synthetic */ C4241pV0 F;

    public C3899nV0(C4241pV0 pv0) {
        this.F = pv0;
    }

    public void applyTransformation(float f, Transformation transformation) {
        C4241pV0 pv0 = this.F;
        int i = pv0.T;
        int left = (i + ((int) (((float) (pv0.U - i)) * f))) - pv0.R.getLeft();
        C4241pV0 pv02 = this.F;
        pv02.P += (float) left;
        Math.min(1.0f, pv02.a() / this.F.I);
        C4241pV0 pv03 = this.F;
        pv03.R.offsetLeftAndRight(left);
        pv03.O = pv03.R.getLeft();
    }
}
