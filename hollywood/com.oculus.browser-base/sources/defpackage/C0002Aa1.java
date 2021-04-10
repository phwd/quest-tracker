package defpackage;

import java.util.Map;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Aa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0002Aa1 implements AbstractC5953za1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0124Ca1 f7679a;

    public C0002Aa1(AbstractC0124Ca1 ca1) {
        this.f7679a = ca1;
    }

    public int a(int i, int i2, Tab tab) {
        if (i == 9) {
            return -1;
        }
        if (i == 0 || i == 4 || i == 5) {
            TabModel i3 = ((AbstractC0246Ea1) this.f7679a).i();
            if (i3.a() == tab.a()) {
                Tab c = AbstractC1160Ta1.c(i3);
                if (c == null) {
                    i2 = 0;
                } else {
                    int id = c.getId();
                    int e = AbstractC1160Ta1.e(i3, id);
                    if (b(i, tab.a())) {
                        Tab d = AbstractC1160Ta1.d(i3, C5383wB.q(tab).Q);
                        if (!(d == null || c == d)) {
                            i2 = AbstractC1160Ta1.e(i3, d.getId()) + 1;
                        }
                    } else {
                        TabModel i4 = ((AbstractC0246Ea1) this.f7679a).i();
                        int count = i4.getCount() - 1;
                        while (true) {
                            if (count < e) {
                                count = -1;
                                break;
                            }
                            Tab tabAt = i4.getTabAt(count);
                            if (C5383wB.q(tabAt).Q == id) {
                                Y51 c2 = Y51.c(tabAt);
                                Object obj = Boolean.TRUE;
                                if (c2.G.containsKey("isTabGroupedWithParent")) {
                                    obj = c2.e("isTabGroupedWithParent");
                                }
                                if (((Boolean) obj).booleanValue()) {
                                    break;
                                }
                            }
                            count--;
                        }
                        if (count != -1) {
                            i2 = count + 1;
                        }
                    }
                    i2 = e + 1;
                }
            } else {
                i2 = ((AbstractC0246Ea1) this.f7679a).l(tab.a()).getCount();
            }
        }
        if (b(i, tab.a())) {
            TabModel i5 = ((AbstractC0246Ea1) this.f7679a).i();
            int count2 = i5.getCount();
            for (int i6 = 0; i6 < count2; i6++) {
                Y51 c3 = Y51.c(i5.getTabAt(i6));
                Object obj2 = Boolean.FALSE;
                Map map = c3.G;
                if (obj2 == null) {
                    obj2 = Y51.F;
                }
                map.put("isTabGroupedWithParent", obj2);
            }
        }
        return i2;
    }

    public boolean b(int i, boolean z) {
        if (i == 3 || i == 9) {
            return false;
        }
        if (i != 5 || (!((AbstractC0246Ea1) this.f7679a).r() && z)) {
            return true;
        }
        return false;
    }
}
