package defpackage;

import android.util.Pair;
import java.util.Iterator;

/* renamed from: qn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4460qn implements Runnable {
    public final /* synthetic */ C4630rn F;

    public RunnableC4460qn(C4630rn rnVar) {
        this.F = rnVar;
    }

    public void run() {
        Iterator it = this.F.f11221a.iterator();
        while (it.hasNext()) {
            ((Runnable) ((Pair) it.next()).second).run();
        }
        this.F.f11221a.clear();
    }
}
