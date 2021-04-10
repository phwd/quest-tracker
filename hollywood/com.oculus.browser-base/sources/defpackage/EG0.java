package defpackage;

import J.N;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.PowerMonitor;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.ui.base.SelectFileDialog;

/* renamed from: EG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EG0 implements Runnable {
    public final /* synthetic */ OG0 F;

    public EG0(OG0 og0) {
        this.F = og0;
    }

    public void run() {
        OG0 og0 = this.F;
        Objects.requireNonNull(og0);
        BG0 bg0 = new BG0(og0);
        Executor executor = AbstractC2032cb.f9616a;
        bg0.f();
        ((ExecutorC1463Ya) executor).execute(bg0.e);
        HD.b();
        N.M2GcaEfy();
        PartnerBrowserCustomizations.c().f(new DG0(this));
        PowerMonitor.a();
        AbstractC2032cb.b.execute(new MT0());
        String[] strArr = SelectFileDialog.b;
        C3070if1 if1 = C3070if1.b;
        PostTask.b(if1, new PR0(), 0);
        Object obj = C0462Hn.f8181a;
        C0462Hn hn = AbstractC0401Gn.f8109a;
        if (hn.d && hn.c.f("channels_version_key", -1) != hn.e) {
            Objects.requireNonNull(this.F);
            PostTask.b(if1, new RunnableC4036oG0(), 0);
        }
    }
}
