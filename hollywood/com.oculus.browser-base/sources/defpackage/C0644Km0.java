package defpackage;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import org.chromium.chrome.browser.gesturenav.NavigationBubble;

/* renamed from: Km0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0644Km0 implements ValueAnimator.AnimatorUpdateListener {
    public int F;
    public int G;
    public final /* synthetic */ NavigationBubble H;

    public C0644Km0(NavigationBubble navigationBubble, AbstractC0583Jm0 jm0) {
        this.H = navigationBubble;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.H.M.setImageTintList(ColorStateList.valueOf(AbstractC1270Uv.a(this.F, this.G, ((Float) valueAnimator.getAnimatedValue()).floatValue())));
    }
}
