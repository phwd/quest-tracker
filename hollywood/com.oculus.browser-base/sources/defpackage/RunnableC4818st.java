package defpackage;

import J.N;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;

/* renamed from: st  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4818st implements Runnable {
    public final AbstractActivityC2601fu F;

    public RunnableC4818st(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    public void run() {
        AbstractActivityC2601fu fuVar = this.F;
        Objects.requireNonNull(fuVar);
        TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.maybeGetFeedAppLifecycleAndMaybeCreatePageViewObserver");
        try {
            if (!FeedStreamSurface.b) {
                FeedStreamSurface.b = true;
                N.MECoVgji();
                HashSet hashSet = FeedStreamSurface.c;
                if (hashSet != null) {
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        ((FeedStreamSurface) it.next()).d();
                    }
                }
            }
            if (Jr1.b()) {
                Jr1 a2 = Jr1.a();
                C0551Ja1 ja1 = fuVar.l1;
                Objects.requireNonNull(a2);
                Object obj = ThreadUtils.f10596a;
                a2.h.add(new WeakReference(new C0905Ov0(fuVar, ja1, a2.c, a2.f, a2.e)));
            }
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
