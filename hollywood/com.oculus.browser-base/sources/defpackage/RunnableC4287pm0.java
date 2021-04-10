package defpackage;

import java.util.Objects;

/* renamed from: pm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4287pm0 implements Runnable {
    public final /* synthetic */ boolean F;
    public final /* synthetic */ Runnable G;
    public final /* synthetic */ Runnable H;
    public final /* synthetic */ AbstractC4798sm0 I;

    public RunnableC4287pm0(AbstractC4798sm0 sm0, boolean z, Runnable runnable, Runnable runnable2) {
        this.I = sm0;
        this.F = z;
        this.G = runnable;
        this.H = runnable2;
    }

    public void run() {
        AbstractC4798sm0 sm0 = this.I;
        if (!sm0.b) {
            if (!this.F) {
                AbstractC2556ff d = sm0.d();
                AbstractC4798sm0 sm02 = this.I;
                int i = sm02.f11299a;
                boolean z = sm02.c;
                C5116uf ufVar = (C5116uf) d;
                int b = AbstractC2556ff.b(i);
                ufVar.c("Android.BackgroundTaskScheduler.TaskLoadedNative", b);
                if (z) {
                    ufVar.c("Android.BackgroundTaskScheduler.TaskLoadedNative.ReducedMode", b);
                } else {
                    ufVar.c("Android.BackgroundTaskScheduler.TaskLoadedNative.FullBrowser", b);
                }
            }
            AbstractC4798sm0 sm03 = this.I;
            C3107is isVar = sm03.e;
            int i2 = sm03.f11299a;
            boolean z2 = sm03.c;
            Runnable runnable = this.G;
            Runnable runnable2 = this.H;
            Objects.requireNonNull(isVar);
            C2937hs hsVar = new C2937hs(isVar, runnable, i2, z2, runnable2);
            try {
                C1321Vq.b().d(hsVar);
                C1321Vq.b().c(true, hsVar, null);
            } catch (C2840hG0 e) {
                AbstractC1220Ua0.a("BTS_NativeBkgrdTask", "Background Launch Error", e);
                runnable2.run();
            }
        }
    }
}
