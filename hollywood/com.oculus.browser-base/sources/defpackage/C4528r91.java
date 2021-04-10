package defpackage;

import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: r91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4528r91 implements D91 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ I91 f11186a;

    public C4528r91(I91 i91) {
        this.f11186a = i91;
    }

    @Override // defpackage.D91
    public void a(int i) {
        Tab d;
        Tab tab;
        int x;
        int i2 = -1;
        if (this.f11186a.g.x(i) != -1 && (d = AbstractC1160Ta1.d(((AbstractC0246Ea1) this.f11186a.i).i(), i)) != null) {
            StringBuilder i3 = AbstractC2531fV.i("MobileTabClosed.");
            i3.append(this.f11186a.o);
            AbstractC3535lK0.a(i3.toString());
            I91 i91 = this.f11186a;
            if (i91.s) {
                List g = i91.g(i);
                if (g.size() > 1) {
                    Objects.requireNonNull(this.f11186a);
                    I91.b.put(Integer.valueOf(i), 3);
                    ((AbstractC0246Ea1) this.f11186a.i).i().l(g, true);
                    return;
                }
            }
            I91 i912 = this.f11186a;
            String str = i912.o;
            Objects.requireNonNull(i912);
            int i4 = 0;
            if (!str.equals("TabStrip")) {
                if (str.equals("GridTabSwitcher")) {
                    i4 = 2;
                } else {
                    AbstractC1220Ua0.f("TabListMediator", "Attempting to close tab from Unknown UI", new Object[0]);
                    if (((AbstractC0246Ea1) this.f11186a.i).j() == d || (x = this.f11186a.g.x(i)) == -1) {
                        tab = null;
                    } else {
                        if (this.f11186a.g.size() > 1) {
                            if (x == 0) {
                                K91 k91 = this.f11186a.g;
                                while (true) {
                                    x++;
                                    if (x < k91.size()) {
                                        if (((C4765sb0) k91.get(x)).b.f(J91.f8274a) == 0) {
                                            break;
                                        }
                                    } else {
                                        x = -1;
                                        break;
                                    }
                                }
                            } else {
                                x = this.f11186a.g.w(x);
                            }
                            if (x != -1) {
                                i2 = ((C4765sb0) this.f11186a.g.get(x)).b.f(AbstractC5106ub1.f11420a);
                            }
                        }
                        tab = AbstractC1160Ta1.d(((AbstractC0246Ea1) this.f11186a.i).i(), i2);
                    }
                    ((AbstractC0246Ea1) this.f11186a.i).i().e(d, tab, false, false, true);
                }
            }
            I91.b.put(Integer.valueOf(i), Integer.valueOf(i4));
            if (((AbstractC0246Ea1) this.f11186a.i).j() == d) {
            }
            tab = null;
            ((AbstractC0246Ea1) this.f11186a.i).i().e(d, tab, false, false, true);
        }
    }
}
