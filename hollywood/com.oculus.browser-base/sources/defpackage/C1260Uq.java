package defpackage;

import android.app.Activity;
import android.os.Process;
import java.util.Locale;

/* renamed from: Uq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1260Uq implements Z9 {
    public final /* synthetic */ C1321Vq F;

    public C1260Uq(C1321Vq vq) {
        this.F = vq;
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if ((i == 1 || i == 6) && !this.F.c.equals(Locale.getDefault())) {
            AbstractC1220Ua0.a("BrowserInitializer", "Killing process because of locale change.", new Object[0]);
            Process.killProcess(Process.myPid());
        }
    }
}
