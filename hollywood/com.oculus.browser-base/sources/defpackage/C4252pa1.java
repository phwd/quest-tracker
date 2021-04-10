package defpackage;

import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: pa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4252pa1 implements N81 {

    /* renamed from: a  reason: collision with root package name */
    public final List f11074a = new ArrayList();
    public final /* synthetic */ C4423qa1 b;

    public C4252pa1(C4423qa1 qa1, AbstractC4081oa1 oa1) {
        this.b = qa1;
    }

    public boolean A() {
        return this.b.o() && this.f11074a.size() > this.b.getCount();
    }

    public void B() {
        this.f11074a.clear();
        if (this.b.o()) {
            for (int i = 0; i < this.b.getCount(); i++) {
                this.f11074a.add(this.b.getTabAt(i));
            }
        }
    }

    @Override // defpackage.N81
    public boolean a() {
        return this.b.f10777a;
    }

    @Override // defpackage.N81
    public int getCount() {
        return this.f11074a.size();
    }

    @Override // defpackage.N81
    public Tab getTabAt(int i) {
        if (i < 0 || i >= this.f11074a.size()) {
            return null;
        }
        return (Tab) this.f11074a.get(i);
    }

    @Override // defpackage.N81
    public int i(Tab tab) {
        return this.f11074a.indexOf(tab);
    }

    @Override // defpackage.N81
    public int index() {
        C4423qa1 qa1 = this.b;
        if (qa1.p != -1) {
            return this.f11074a.indexOf(AbstractC1160Ta1.c(qa1));
        }
        if (!this.f11074a.isEmpty()) {
            return 0;
        }
        return -1;
    }

    @Override // defpackage.N81
    public boolean t(int i) {
        return this.b.t(i);
    }

    public Tab y() {
        if (!A()) {
            return null;
        }
        int i = 0;
        while (i < this.f11074a.size()) {
            Tab tabAt = i < this.b.getCount() ? this.b.getTabAt(i) : null;
            Tab tab = (Tab) this.f11074a.get(i);
            if (tabAt == null || tab.getId() != tabAt.getId()) {
                return tab;
            }
            i++;
        }
        return null;
    }

    public Tab z(int i) {
        if (this.b.o() && AbstractC1160Ta1.d(this.b, i) == null) {
            return AbstractC1160Ta1.d(this, i);
        }
        return null;
    }
}
