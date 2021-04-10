package defpackage;

import android.animation.ValueAnimator;
import android.view.View;

/* renamed from: Yu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Yu1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Ry1 F;
    public final /* synthetic */ View G;

    public Yu1(Zu1 zu1, Ry1 ry1, View view) {
        this.F = ry1;
        this.G = view;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        ((View) this.F.f8865a.f.getParent()).invalidate();
    }
}
