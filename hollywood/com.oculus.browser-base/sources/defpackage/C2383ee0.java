package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: ee0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2383ee0 {

    /* renamed from: a  reason: collision with root package name */
    public final C2725ge0 f9868a;
    public final ArrayList b = new ArrayList();

    public C2383ee0(C2725ge0 ge0) {
        this.f9868a = ge0;
    }

    public void a() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.b.clear();
    }
}
