package defpackage;

import android.view.animation.Animation;
import java.util.Objects;

/* renamed from: G41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G41 implements Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ K41 f8060a;

    public G41(K41 k41) {
        this.f8060a = k41;
    }

    public void onAnimationEnd(Animation animation) {
        Objects.requireNonNull(this.f8060a);
        K41 k41 = this.f8060a;
        k41.l(k41.e0);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
