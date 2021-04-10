package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import java.util.Objects;
import org.chromium.components.infobars.InfoBar;

/* renamed from: p10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4162p10 extends AbstractC5354w10 {
    public C10 c;
    public D10 d;
    public View e;
    public final /* synthetic */ C5694y10 f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4162p10(C5694y10 y10, C10 c10) {
        super(y10, null);
        this.f = y10;
        this.c = c10;
    }

    @Override // defpackage.AbstractC5354w10
    public Animator a() {
        D10 d10 = this.d;
        d10.setTranslationY((float) d10.getHeight());
        this.e.setAlpha(0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(b(this.d, 0.0f).setDuration(250L), ObjectAnimator.ofFloat(this.e, View.ALPHA, 1.0f).setDuration(100L));
        return animatorSet;
    }

    @Override // defpackage.AbstractC5354w10
    public int c() {
        return 0;
    }

    @Override // defpackage.AbstractC5354w10
    public void d() {
        this.f.announceForAccessibility(this.c.b());
    }

    @Override // defpackage.AbstractC5354w10
    public void e() {
        Objects.requireNonNull((InfoBar) this.c);
        this.e = null;
        D10 d10 = new D10(this.f.getContext(), this.c);
        this.d = d10;
        d10.addView(this.e);
        C5694y10.d(this.f, this.d);
    }
}
