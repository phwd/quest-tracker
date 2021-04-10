package defpackage;

import android.view.ViewGroup;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/* renamed from: Vt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1328Vt0 {

    /* renamed from: a  reason: collision with root package name */
    public final Set f9113a = new HashSet();
    public final C1322Vq0 b = new C1322Vq0();
    public AbstractC0292Et0 c;
    public Queue d = new PriorityQueue(3, new C1206Tt0(this));
    public IJ e;
    public ViewGroup f;

    public final void a(AbstractC0292Et0 et0, int i) {
        et0.j0(i);
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1267Ut0) uq0.next()).a();
            } else {
                return;
            }
        }
    }
}
