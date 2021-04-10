package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: na1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3910na1 extends VK {
    public List F = Collections.emptyList();
    public final List G = new ArrayList();

    public void a(AbstractC5783ya1 ya1) {
        if (this.F.isEmpty()) {
            this.G.add(ya1);
            return;
        }
        for (int i = 0; i < this.F.size(); i++) {
            ((AbstractC3568la1) this.F.get(i)).c.b(ya1);
        }
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void b() {
        for (AbstractC3568la1 la1 : this.F) {
            la1.e = true;
        }
    }

    public AbstractC3568la1 d() {
        for (int i = 0; i < this.F.size(); i++) {
            if (((AbstractC3568la1) this.F.get(i)).b.isActiveModel()) {
                return (AbstractC3568la1) this.F.get(i);
            }
        }
        return null;
    }

    public AbstractC3568la1 g(boolean z) {
        for (int i = 0; i < this.F.size(); i++) {
            if (((AbstractC3568la1) this.F.get(i)).a() == z) {
                return (AbstractC3568la1) this.F.get(i);
            }
        }
        return null;
    }

    public void h(AbstractC5783ya1 ya1) {
        if (!this.F.isEmpty() || this.G.isEmpty()) {
            for (int i = 0; i < this.F.size(); i++) {
                ((AbstractC3568la1) this.F.get(i)).c.c(ya1);
            }
            return;
        }
        this.G.remove(ya1);
    }
}
