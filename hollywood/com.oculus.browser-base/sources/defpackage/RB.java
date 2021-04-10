package defpackage;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;

/* renamed from: RB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RB implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ ColorDrawable H;

    public RB(CustomTabToolbar customTabToolbar, int i, int i2, ColorDrawable colorDrawable) {
        this.F = i;
        this.G = i2;
        this.H = colorDrawable;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        this.H.setColor(Color.rgb((int) ((((float) (Color.red(this.G) - Color.red(this.F))) * animatedFraction) + ((float) Color.red(this.F))), (int) ((((float) (Color.green(this.G) - Color.green(this.F))) * animatedFraction) + ((float) Color.green(this.F))), (int) ((animatedFraction * ((float) (Color.blue(this.G) - Color.blue(this.F)))) + ((float) Color.blue(this.F)))));
    }
}
