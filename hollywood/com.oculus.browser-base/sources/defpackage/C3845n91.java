package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: n91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3845n91 implements D91 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ I91 f10476a;

    public C3845n91(I91 i91) {
        this.f10476a = i91;
    }

    @Override // defpackage.D91
    public void a(int i) {
        if (this.f10476a.g.x(i) != -1) {
            I91 i91 = this.f10476a;
            i91.v = i;
            if (!i91.s || AbstractC4772sd1.a()) {
                Tab j = ((AbstractC0246Ea1) this.f10476a.i).j();
                Tab d = AbstractC1160Ta1.d(((AbstractC0246Ea1) this.f10476a.i).i(), i);
                int i2 = ((AbstractC0246Ea1) this.f10476a.i).c.d().i(j);
                int i3 = ((AbstractC0246Ea1) this.f10476a.i).c.d().i(d);
                StringBuilder i4 = AbstractC2531fV.i("MobileTabSwitched.");
                i4.append(this.f10476a.o);
                AbstractC3535lK0.a(i4.toString());
                if (AbstractC4772sd1.a()) {
                    StringBuilder i5 = AbstractC2531fV.i("Tabs.TabOffsetOfSwitch.");
                    i5.append(this.f10476a.o);
                    AbstractC3100ip1.f10165a.d(i5.toString(), i2 - i3);
                } else if (i2 == i3) {
                    int e = AbstractC1160Ta1.e(((AbstractC0246Ea1) this.f10476a.i).i(), j.getId());
                    int e2 = AbstractC1160Ta1.e(((AbstractC0246Ea1) this.f10476a.i).i(), d.getId());
                    StringBuilder i6 = AbstractC2531fV.i("Tabs.TabOffsetOfSwitch.");
                    i6.append(this.f10476a.o);
                    AbstractC3100ip1.f10165a.d(i6.toString(), e - e2);
                }
            }
            I91 i912 = this.f10476a;
            AbstractC5209v91 v91 = i912.m;
            if (v91 != null) {
                ((C3919nd1) v91).m(i, true);
            } else {
                ((AbstractC0246Ea1) i912.i).i().x(AbstractC1160Ta1.e(((AbstractC0246Ea1) this.f10476a.i).i(), i), 3);
            }
        }
    }
}
