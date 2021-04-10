package defpackage;

import android.animation.Animator;
import java.util.Objects;
import org.chromium.components.infobars.InfoBar;

/* renamed from: o10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3991o10 extends AbstractC5354w10 {
    public D10 c;
    public final /* synthetic */ C5694y10 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3991o10(C5694y10 y10, C10 c10) {
        super(y10, null);
        this.d = y10;
        this.c = new D10(y10.getContext(), c10);
    }

    @Override // defpackage.AbstractC5354w10
    public Animator a() {
        D10 d10 = this.c;
        d10.setTranslationY((float) d10.getHeight());
        return b(this.c, 0.0f).setDuration(250L);
    }

    @Override // defpackage.AbstractC5354w10
    public int c() {
        return 0;
    }

    @Override // defpackage.AbstractC5354w10
    public void d() {
        D10 d10 = this.c;
        Objects.requireNonNull((InfoBar) d10.F);
        d10.removeView(null);
    }

    @Override // defpackage.AbstractC5354w10
    public void e() {
        C5694y10.d(this.d, this.c);
    }
}
