package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import java.util.Objects;
import org.chromium.components.infobars.InfoBar;

/* renamed from: s10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4674s10 extends AbstractC5354w10 {
    public D10 c;
    public D10 d;
    public View e;
    public final /* synthetic */ C5694y10 f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4674s10(C5694y10 y10, AbstractC3820n10 n10) {
        super(y10, null);
        this.f = y10;
    }

    @Override // defpackage.AbstractC5354w10
    public Animator a() {
        int height = (this.d.getHeight() - this.f.G) - this.c.getHeight();
        int max = Math.max(height, 0);
        int max2 = Math.max(-height, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        float f2 = (float) max;
        this.c.setTranslationY(f2);
        D10 d10 = this.c;
        animatorSet.play(b(d10, (float) (d10.getHeight() + max)).setDuration(250L));
        for (int i = 1; i < this.f.I.size(); i++) {
            ((D10) this.f.I.get(i)).setTranslationY(f2);
            animatorSet.play(b((D10) this.f.I.get(i), (float) max2).setDuration(250L));
        }
        this.e.setAlpha(0.0f);
        animatorSet.play(ObjectAnimator.ofFloat(this.e, View.ALPHA, 1.0f).setDuration(100L)).after(250);
        return animatorSet;
    }

    @Override // defpackage.AbstractC5354w10
    public int c() {
        return 2;
    }

    @Override // defpackage.AbstractC5354w10
    public void d() {
        this.c.removeAllViews();
        C5694y10 y10 = this.f;
        D10 d10 = this.c;
        y10.removeView(d10);
        y10.I.remove(d10);
        y10.g();
        for (int i = 0; i < this.f.I.size(); i++) {
            ((D10) this.f.I.get(i)).setTranslationY(0.0f);
        }
        this.f.announceForAccessibility(this.d.F.b());
    }

    @Override // defpackage.AbstractC5354w10
    public void e() {
        this.c = (D10) this.f.I.get(0);
        D10 d10 = (D10) this.f.I.get(1);
        this.d = d10;
        Objects.requireNonNull((InfoBar) d10.F);
        this.e = null;
        d10.addView(null);
    }
}
