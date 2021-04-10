package defpackage;

import android.graphics.Rect;

/* renamed from: ak  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1708ak extends C1078Rq0 implements AbstractC2230dk, AbstractC4541rE {

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC2400ek f9447J;

    public C1708ak(AbstractC2400ek ekVar) {
        this.f9447J = ekVar;
        ((C1551Zj) ekVar).Y.b(this);
        n();
    }

    @Override // defpackage.AbstractC2230dk
    public void h(int i, int i2) {
        n();
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        n();
    }

    @Override // defpackage.AbstractC2230dk
    public void k(int i, int i2) {
        n();
    }

    public final void n() {
        AbstractC2400ek ekVar = this.f9447J;
        m(new Rect(0, ((C1551Zj) ekVar).M + ((C1551Zj) ekVar).R, 0, ((C1551Zj) ekVar).O - ((C1551Zj) ekVar).b()));
    }
}
