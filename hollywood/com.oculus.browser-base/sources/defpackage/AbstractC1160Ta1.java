package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Ta1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1160Ta1 {
    public static boolean a(TabModel tabModel, int i, boolean z) {
        Tab d = d(tabModel, i);
        if (d == null) {
            return false;
        }
        return tabModel.q(d, true, false, z);
    }

    public static boolean b(TabModel tabModel, int i) {
        Tab tabAt = tabModel.getTabAt(i);
        if (tabAt == null) {
            return false;
        }
        return tabModel.h(tabAt);
    }

    public static Tab c(N81 n81) {
        int index = n81.index();
        if (index == -1) {
            return null;
        }
        return n81.getTabAt(index);
    }

    public static Tab d(N81 n81, int i) {
        int e = e(n81, i);
        if (e == -1) {
            return null;
        }
        return n81.getTabAt(e);
    }

    public static int e(N81 n81, int i) {
        int count = n81.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            if (n81.getTabAt(i2).getId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public static void f(TabModel tabModel, int i) {
        ((C4423qa1) tabModel).x(i, 3);
    }
}
