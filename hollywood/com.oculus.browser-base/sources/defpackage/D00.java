package defpackage;

import java.util.Iterator;

/* renamed from: D00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D00 {

    /* renamed from: a  reason: collision with root package name */
    public final C1322Vq0 f7854a = new C1322Vq0();
    public final AbstractC0612Ka1 b = new B00(this);
    public AbstractC0124Ca1 c;

    public final void a(boolean z) {
        Iterator it = this.f7854a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((C00) uq0.next()).c(z);
            } else {
                return;
            }
        }
    }

    public boolean b() {
        AbstractC0124Ca1 ca1 = this.c;
        if (ca1 != null) {
            return ((AbstractC0246Ea1) ca1).r();
        }
        return false;
    }
}
