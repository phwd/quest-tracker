package defpackage;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: bI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1821bI0 {

    /* renamed from: a  reason: collision with root package name */
    public final C1322Vq0 f9530a = new C1322Vq0();

    public abstract Collection a();

    public void b(Object obj) {
        Iterator it = this.f9530a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1641aI0) uq0.next()).b(this, obj);
            } else {
                return;
            }
        }
    }
}
