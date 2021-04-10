package defpackage;

import java.util.Iterator;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Da1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0185Da1 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC0246Ea1 f7897a;

    public C0185Da1(AbstractC0246Ea1 ea1) {
        this.f7897a = ea1;
    }

    @Override // defpackage.AbstractC5783ya1
    public void B(Tab tab, int i, int i2) {
        this.f7897a.s();
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        this.f7897a.s();
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        this.f7897a.s();
        Iterator it = this.f7897a.f.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0612Ka1) uq0.next()).f(tab, i2);
            } else {
                return;
            }
        }
    }
}
