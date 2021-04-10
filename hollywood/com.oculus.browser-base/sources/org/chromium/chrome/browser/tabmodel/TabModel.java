package org.chromium.chrome.browser.tabmodel;

import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface TabModel extends N81 {
    Profile b();

    void c(boolean z);

    void d();

    void destroy();

    boolean e(Tab tab, Tab tab2, boolean z, boolean z2, boolean z3);

    void f(Tab tab, int i, int i2, int i3);

    void g(boolean z, boolean z2);

    boolean h(Tab tab);

    boolean isActiveModel();

    N81 j();

    void k();

    void l(List list, boolean z);

    void m(int i, int i2);

    void n(AbstractC5783ya1 ya1);

    boolean o();

    void p();

    boolean q(Tab tab, boolean z, boolean z2, boolean z3);

    Tab r(int i);

    void s(int i);

    void u(Tab tab);

    void v(int i);

    void w(AbstractC5783ya1 ya1);

    void x(int i, int i2);
}
