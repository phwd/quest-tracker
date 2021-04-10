package defpackage;

import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: E61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E61 extends AbstractC5431wV {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC4751sV f7936a;
    public final /* synthetic */ F61 b;

    public E61(F61 f61, AbstractC4751sV sVVar) {
        this.b = f61;
        this.f7936a = sVVar;
    }

    @Override // defpackage.AbstractC5601xV
    public void a(int i, int i2) {
        g();
    }

    @Override // defpackage.AbstractC5601xV, defpackage.AbstractC5431wV
    public void b(int i, int i2) {
        g();
    }

    @Override // defpackage.AbstractC5601xV
    public void e(int i, int i2) {
        g();
    }

    @Override // defpackage.AbstractC5601xV, defpackage.AbstractC5431wV
    public void f(int i, int i2) {
        g();
    }

    public final void g() {
        AbstractC4751sV sVVar = this.f7936a;
        boolean isScrollInProgress = sVVar != null ? sVVar.isScrollInProgress() : false;
        C1261Uq0 Y = ((TabImpl) this.b.G).Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).o(isScrollInProgress);
        }
    }
}
