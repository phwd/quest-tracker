package defpackage;

import android.animation.ObjectAnimator;
import android.view.View;
import java.util.List;
import org.chromium.chrome.browser.omnibox.LocationBarPhone;

/* renamed from: oa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4080oa0 implements AbstractC3738ma0 {
    public LocationBarPhone F;
    public R11 G;

    public C4080oa0(LocationBarPhone locationBarPhone, R11 r11) {
        this.F = locationBarPhone;
        this.G = r11;
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        this.F = null;
        this.G = null;
    }

    public float f() {
        return this.F.getAlpha();
    }

    public int g() {
        return this.F.getLayoutDirection();
    }

    public void h(List list, long j, long j2, float f) {
        View childAt;
        int i = 0;
        while (i < this.F.getChildCount() && (childAt = this.F.getChildAt(i)) != this.F.g0) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(childAt, View.ALPHA, f);
            ofFloat.setStartDelay(j);
            ofFloat.setDuration(j2);
            ofFloat.setInterpolator(animation.InterpolatorC5286vf.e);
            list.add(ofFloat);
            i++;
        }
    }
}
