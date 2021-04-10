package defpackage;

import java.util.Iterator;

/* renamed from: qu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4481qu implements Runnable {
    public final /* synthetic */ C5331vu F;

    public RunnableC4481qu(C5331vu vuVar) {
        this.F = vuVar;
    }

    public void run() {
        Iterator it = this.F.e.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4991tu) uq0.next()).a();
            } else {
                return;
            }
        }
    }
}
