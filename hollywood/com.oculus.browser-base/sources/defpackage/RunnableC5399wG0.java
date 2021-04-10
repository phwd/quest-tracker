package defpackage;

import java.util.Objects;

/* renamed from: wG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5399wG0 implements Runnable {
    public final C1207Tu F;

    public RunnableC5399wG0(C1207Tu tu) {
        this.F = tu;
    }

    public void run() {
        C1207Tu tu = this.F;
        Objects.requireNonNull(tu);
        P21 f0 = P21.f0();
        try {
            tu.a("twa_dialog_number_of_dismissals_on_uninstall", true);
            tu.a("twa_dialog_number_of_dismissals_on_clear_data", false);
            f0.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
