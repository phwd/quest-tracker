package defpackage;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import java.util.Map;
import java.util.Set;

/* renamed from: zf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnGlobalLayoutListenerC5967zf0 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ Map F;
    public final /* synthetic */ Map G;
    public final /* synthetic */ DialogC0504If0 H;

    public ViewTreeObserver$OnGlobalLayoutListenerC5967zf0(DialogC0504If0 if0, Map map, Map map2) {
        this.H = if0;
        this.F = map;
        this.G = map2;
    }

    public void onGlobalLayout() {
        C0048At0 at0;
        int i;
        C2392eh0 eh0;
        this.H.i0.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        DialogC0504If0 if0 = this.H;
        Map map = this.F;
        Map map2 = this.G;
        Set set = if0.l0;
        if (!(set == null || if0.m0 == null)) {
            int size = set.size() - if0.m0.size();
            animation.Animation$AnimationListenerC0016Af0 af0 = new animation.Animation$AnimationListenerC0016Af0(if0);
            int firstVisiblePosition = if0.i0.getFirstVisiblePosition();
            boolean z = false;
            for (int i2 = 0; i2 < if0.i0.getChildCount(); i2++) {
                View childAt = if0.i0.getChildAt(i2);
                C2392eh0 eh02 = (C2392eh0) if0.j0.getItem(firstVisiblePosition + i2);
                Rect rect = (Rect) map.get(eh02);
                int top = childAt.getTop();
                if (rect != null) {
                    i = rect.top;
                } else {
                    i = (if0.s0 * size) + top;
                }
                AnimationSet animationSet = new AnimationSet(true);
                Set set2 = if0.l0;
                if (set2 == null || !set2.contains(eh02)) {
                    eh0 = eh02;
                } else {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                    eh0 = eh02;
                    alphaAnimation.setDuration((long) if0.M0);
                    animationSet.addAnimation(alphaAnimation);
                    i = top;
                }
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (i - top), 0.0f);
                translateAnimation.setDuration((long) if0.L0);
                animationSet.addAnimation(translateAnimation);
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                animationSet.setInterpolator(if0.O0);
                if (!z) {
                    animationSet.setAnimationListener(af0);
                    z = true;
                }
                childAt.clearAnimation();
                childAt.startAnimation(animationSet);
                map.remove(eh0);
                map2.remove(eh0);
            }
            for (Map.Entry entry : map2.entrySet()) {
                C2392eh0 eh03 = (C2392eh0) entry.getKey();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) entry.getValue();
                Rect rect2 = (Rect) map.get(eh03);
                if (if0.m0.contains(eh03)) {
                    at0 = new C0048At0(bitmapDrawable, rect2);
                    at0.h = 1.0f;
                    at0.i = 0.0f;
                    at0.e = (long) if0.N0;
                    at0.d = if0.O0;
                } else {
                    C0048At0 at02 = new C0048At0(bitmapDrawable, rect2);
                    at02.g = if0.s0 * size;
                    at02.e = (long) if0.L0;
                    at02.d = if0.O0;
                    at02.m = new C4266pf0(if0, eh03);
                    if0.n0.add(eh03);
                    at0 = at02;
                }
                if0.i0.F.add(at0);
            }
        }
    }
}
