package defpackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: QF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QF extends AbstractC0750Mg0 {

    /* renamed from: a  reason: collision with root package name */
    public final RF f8747a;
    public final C0629Kg0 b;
    public Set c = new HashSet();
    public List d;

    public QF(String str, List list, RF rf, C0629Kg0 kg0) {
        ArrayList arrayList = new ArrayList();
        this.d = arrayList;
        arrayList.addAll(list);
        this.f8747a = rf;
        this.b = kg0;
        k(str);
    }

    @Override // defpackage.AbstractC0750Mg0
    public void d(C3246jh0 jh0, C2392eh0 eh0) {
        if (eh0.i(this.b)) {
            C1363Wh0 a2 = C1363Wh0.a(eh0);
            if (!this.d.contains(a2)) {
                this.d.add(a2);
                l();
            }
        }
    }

    @Override // defpackage.AbstractC0750Mg0
    public void e(C3246jh0 jh0, C2392eh0 eh0) {
        if (eh0.i(this.b)) {
            d(jh0, eh0);
        } else {
            f(jh0, eh0);
        }
    }

    @Override // defpackage.AbstractC0750Mg0
    public void f(C3246jh0 jh0, C2392eh0 eh0) {
        C1363Wh0 a2 = C1363Wh0.a(eh0);
        if (this.d.contains(a2)) {
            this.d.remove(a2);
            l();
        }
    }

    public void k(String str) {
        if (this.c.add(str)) {
            ((AbstractC5474wl) this.f8747a).s(str, new ArrayList(this.d));
        }
    }

    public final void l() {
        for (String str : this.c) {
            ((AbstractC5474wl) this.f8747a).s(str, new ArrayList(this.d));
        }
    }
}
