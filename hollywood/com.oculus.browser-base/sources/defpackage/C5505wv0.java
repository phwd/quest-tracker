package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* renamed from: wv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5505wv0 {

    /* renamed from: a  reason: collision with root package name */
    public final View$OnClickListenerC0479Hv0 f11578a;
    public final C0599Ju0 b;
    public final ViewGroup c;
    public final boolean d;
    public final Dialog e;
    public final UH0 f;
    public final C2746gl0 g;
    public final AbstractC3087il0 h;
    public Animator i;
    public boolean j;

    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: Ju0 */
    /* JADX WARN: Multi-variable type inference failed */
    public C5505wv0(Context context, View$OnClickListenerC0479Hv0 hv0, C0599Ju0 ju0, View view, boolean z, C2746gl0 gl0, AbstractC3087il0 il0) {
        this.f11578a = hv0;
        this.b = ju0;
        this.d = z;
        this.g = gl0;
        this.h = il0;
        if (z) {
            this.c = new C5165uv0(this, context, null, context, view);
        } else {
            this.c = new FadingEdgeScrollView(context, null);
        }
        this.c.setVisibility(4);
        this.c.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC4485qv0(this));
        this.c.addView(ju0 != 0 ? ju0 : hv0);
        if (z) {
            ViewGroup viewGroup = this.c;
            DialogC4995tv0 tv0 = new DialogC4995tv0(this, context);
            tv0.requestWindowFeature(1);
            tv0.setCanceledOnTouchOutside(true);
            Window window = tv0.getWindow();
            window.setGravity(48);
            window.setBackgroundDrawable(new ColorDrawable(0));
            tv0.setOnDismissListener(new DialogInterface$OnDismissListenerC4314pv0(this));
            tv0.addContentView(viewGroup, new LinearLayout.LayoutParams(-1, -1));
            window.setLayout(-1, -2);
            this.e = tv0;
            this.f = null;
            return;
        }
        ViewGroup viewGroup2 = this.c;
        Map c2 = UH0.c(AbstractC3258jl0.r);
        OH0 oh0 = AbstractC3258jl0.f10235a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = il0;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(oh0, lh0);
        TH0 th0 = AbstractC3258jl0.f;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = viewGroup2;
        hashMap.put(th0, lh02);
        QH0 qh0 = AbstractC3258jl0.m;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(qh0, gh0);
        this.f = new UH0(c2, null);
        this.e = null;
    }

    public static Animator a(C5505wv0 wv0, boolean z, Runnable runnable) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2;
        ObjectAnimator objectAnimator3;
        if (wv0.d) {
            ViewGroup viewGroup = wv0.c;
            float f2 = (float) (-viewGroup.getHeight());
            if (z) {
                viewGroup.setTranslationY(f2);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(viewGroup, View.TRANSLATION_Y, 0.0f);
                ofFloat.setInterpolator(animation.InterpolatorC5286vf.g);
                objectAnimator3 = ofFloat;
            } else {
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(viewGroup, View.TRANSLATION_Y, f2);
                ofFloat2.setInterpolator(animation.InterpolatorC5286vf.f);
                objectAnimator3 = ofFloat2;
            }
            objectAnimator3.setDuration(200L);
            objectAnimator = objectAnimator3;
        } else {
            objectAnimator = new AnimatorSet();
        }
        View$OnClickListenerC0479Hv0 hv0 = wv0.f11578a;
        Objects.requireNonNull(hv0);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder play = animatorSet.play(new AnimatorSet());
        List a2 = hv0.a();
        for (int i2 = 0; i2 < a2.size(); i2++) {
            View view = (View) a2.get(i2);
            if (view.getVisibility() == 0) {
                if (z) {
                    view.setAlpha(0.0f);
                    objectAnimator2 = ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f);
                    objectAnimator2.setStartDelay((long) ((i2 * 20) + 150));
                } else {
                    objectAnimator2 = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f);
                }
                objectAnimator2.setDuration(200L);
                play.with(objectAnimator2);
            }
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        if (z) {
            animatorSet2.setStartDelay(100);
        }
        animatorSet2.playTogether(objectAnimator, animatorSet);
        animatorSet2.addListener(new C5335vv0(wv0, runnable));
        Animator animator = wv0.i;
        if (animator != null) {
            animator.cancel();
        }
        wv0.i = animatorSet2;
        return animatorSet2;
    }

    public void b(boolean z) {
        this.j = !z;
        if (this.d) {
            this.e.dismiss();
        } else {
            this.g.b(this.f, 0);
        }
    }
}
