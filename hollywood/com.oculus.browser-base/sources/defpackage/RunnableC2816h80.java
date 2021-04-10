package defpackage;

import J.N;
import org.chromium.base.TraceEvent;

/* renamed from: h80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2816h80 implements Runnable {
    public final boolean F;

    public RunnableC2816h80(boolean z) {
        this.F = z;
    }

    public void run() {
        boolean z = this.F;
        int MdFgVRJJ = N.MdFgVRJJ();
        TraceEvent k0 = TraceEvent.k0("LibraryPrefetcher.asyncPrefetchLibrariesToMemory", Integer.toString(MdFgVRJJ));
        if (z && MdFgVRJJ < 90) {
            try {
                N.MUjpxN8d();
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        if (MdFgVRJJ != -1) {
            StringBuilder sb = new StringBuilder();
            sb.append("LibraryLoader.PercentageOfResidentCodeBeforePrefetch");
            sb.append(z ? ".ColdStartup" : ".WarmStartup");
            AbstractC3364kK0.g(sb.toString(), MdFgVRJJ, 101);
        }
        if (k0 != null) {
            k0.close();
        }
        AbstractC3983nz.f10523a.edit().remove("dont_prefetch_libraries").apply();
        return;
        throw th;
    }
}
