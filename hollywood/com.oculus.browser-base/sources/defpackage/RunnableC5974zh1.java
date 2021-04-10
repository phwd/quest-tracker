package defpackage;

import android.os.Handler;
import android.view.View;
import java.util.Objects;

/* renamed from: zh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5974zh1 implements Runnable {
    public final /* synthetic */ View F;
    public final /* synthetic */ C0023Ah1 G;
    public final /* synthetic */ int H;
    public final /* synthetic */ C0145Ch1 I;

    public RunnableC5974zh1(C0145Ch1 ch1, View view, C0023Ah1 ah1, int i) {
        this.I = ch1;
        this.F = view;
        this.G = ah1;
        this.H = i;
    }

    public void run() {
        C0145Ch1 ch1 = this.I;
        View view = this.F;
        C0023Ah1 ah1 = this.G;
        int i = this.H;
        if (!ch1.f7830a.c(ch1.b)) {
            if (i > 0) {
                int i2 = i - 1;
                Handler handler = view.getHandler();
                if (handler != null) {
                    handler.post(new RunnableC5974zh1(ch1, view, ah1, i2));
                    return;
                }
                return;
            }
            Objects.requireNonNull(ah1);
            XZ.a();
            if (!ah1.f7687a) {
                AbstractC1220Ua0.f("Ime", "onRegisterProxyViewFailure", new Object[0]);
            }
        }
    }
}
