package defpackage;

import android.animation.Animator;
import java.util.ArrayList;

/* renamed from: x10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5524x10 extends AbstractC5354w10 {
    public D10 c;
    public final /* synthetic */ C5694y10 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5524x10(C5694y10 y10, AbstractC3820n10 n10) {
        super(y10, null);
        this.d = y10;
    }

    @Override // defpackage.AbstractC5354w10
    public Animator a() {
        D10 d10 = this.c;
        return b(d10, (float) d10.getHeight()).setDuration(250L);
    }

    @Override // defpackage.AbstractC5354w10
    public int c() {
        return 2;
    }

    @Override // defpackage.AbstractC5354w10
    public void d() {
        this.c.removeAllViews();
        C5694y10 y10 = this.d;
        D10 d10 = this.c;
        y10.removeView(d10);
        y10.I.remove(d10);
        y10.g();
    }

    @Override // defpackage.AbstractC5354w10
    public void e() {
        ArrayList arrayList = this.d.I;
        this.c = (D10) arrayList.get(arrayList.size() - 1);
    }
}
