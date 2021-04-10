package defpackage;

import android.util.Base64;
import org.chromium.base.task.PostTask;

/* renamed from: Vm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Vm1 implements Runnable {
    public final C5232vH0 F;

    public Vm1(C5232vH0 vh0) {
        this.F = vh0;
    }

    public void run() {
        C5232vH0 vh0 = this.F;
        C4300pq1 pq1 = null;
        String string = AbstractC2425es0.e().getString("UpdateProtos_Tracking", null);
        if (string != null) {
            try {
                pq1 = (C4300pq1) AbstractC2360eV.k(C4300pq1.e, Base64.decode(string, 0));
            } catch (L30 unused) {
            }
        }
        PostTask.b(Zo1.f9374a, new Ym1(vh0, pq1), 0);
    }
}
