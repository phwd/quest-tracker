package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Objects;
import org.chromium.components.infobars.InfoBar;

/* renamed from: r10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4503r10 extends AbstractC5354w10 {
    public C10 c;
    public D10 d;
    public D10 e;
    public View f;
    public final /* synthetic */ C5694y10 g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4503r10(C5694y10 y10, C10 c10) {
        super(y10, null);
        this.g = y10;
        this.c = c10;
    }

    @Override // defpackage.AbstractC5354w10
    public Animator a() {
        int i;
        int height = (this.d.getHeight() + this.g.G) - this.e.getHeight();
        int height2 = this.d.getHeight();
        if (height < 0) {
            height2 -= height;
            i = 0 - height;
        } else {
            i = 0;
        }
        this.d.setTranslationY((float) height2);
        this.f.setAlpha(0.0f);
        this.g.M.run();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(b(this.d, (float) i).setDuration(250L));
        int max = Math.max(0, height);
        int max2 = Math.max(-height, 0);
        for (int i2 = 1; i2 < this.g.I.size(); i2++) {
            ((D10) this.g.I.get(i2)).setTranslationY((float) max);
            animatorSet.play(b((D10) this.g.I.get(i2), (float) max2).setDuration(250L));
        }
        animatorSet.play(ObjectAnimator.ofFloat(this.f, View.ALPHA, 1.0f).setDuration(100L)).after(250);
        return animatorSet;
    }

    @Override // defpackage.AbstractC5354w10
    public int c() {
        return 0;
    }

    @Override // defpackage.AbstractC5354w10
    public void d() {
        this.e.removeAllViews();
        for (int i = 0; i < this.g.I.size(); i++) {
            ((D10) this.g.I.get(i)).setTranslationY(0.0f);
        }
        this.g.g();
        this.g.announceForAccessibility(this.c.b());
    }

    @Override // defpackage.AbstractC5354w10
    public void e() {
        this.e = (D10) this.g.I.get(0);
        Objects.requireNonNull((InfoBar) this.c);
        this.f = null;
        D10 d10 = new D10(this.g.getContext(), this.c);
        this.d = d10;
        d10.addView(this.f);
        C5694y10 y10 = this.g;
        D10 d102 = this.d;
        Objects.requireNonNull(y10);
        y10.addView(d102, new FrameLayout.LayoutParams(-1, -2));
        y10.I.add(0, d102);
        y10.g();
    }
}
