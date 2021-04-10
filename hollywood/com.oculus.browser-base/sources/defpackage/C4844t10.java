package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import java.util.Objects;
import org.chromium.components.infobars.InfoBar;

/* renamed from: t10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4844t10 extends AbstractC5354w10 {
    public D10 c;
    public View d;
    public View e;
    public final /* synthetic */ C5694y10 f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4844t10(C5694y10 y10, AbstractC3820n10 n10) {
        super(y10, null);
        this.f = y10;
    }

    @Override // defpackage.AbstractC5354w10
    public Animator a() {
        int height = this.e.getHeight() - this.d.getHeight();
        this.f.setTranslationY((float) Math.max(0, height));
        this.e.setAlpha(0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(ObjectAnimator.ofFloat(this.d, View.ALPHA, 0.0f).setDuration(200L), ObjectAnimator.ofFloat(this.f, View.TRANSLATION_Y, (float) Math.max(0, -height)).setDuration(250L), ObjectAnimator.ofFloat(this.e, View.ALPHA, 1.0f).setDuration(200L));
        return animatorSet;
    }

    @Override // defpackage.AbstractC5354w10
    public int c() {
        return 1;
    }

    @Override // defpackage.AbstractC5354w10
    public void d() {
        this.c.removeViewAt(0);
        this.f.setTranslationY(0.0f);
        C10 c10 = this.c.F;
        ((InfoBar) c10).G = true;
        this.f.announceForAccessibility(c10.b());
    }

    @Override // defpackage.AbstractC5354w10
    public void e() {
        D10 d10 = (D10) this.f.I.get(0);
        this.c = d10;
        this.d = d10.getChildAt(0);
        D10 d102 = this.c;
        Objects.requireNonNull((InfoBar) d102.F);
        this.e = null;
        d102.addView(null);
    }
}
