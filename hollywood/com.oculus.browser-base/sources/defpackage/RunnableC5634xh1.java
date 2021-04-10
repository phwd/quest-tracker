package defpackage;

import android.os.Handler;
import android.view.View;
import java.util.Objects;

/* renamed from: xh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5634xh1 implements Runnable {
    public final /* synthetic */ RunnableC5804yh1 F;

    public RunnableC5634xh1(RunnableC5804yh1 yh1) {
        this.F = yh1;
    }

    public void run() {
        RunnableC5804yh1 yh1 = this.F;
        C0145Ch1 ch1 = yh1.G;
        View view = yh1.F;
        C0023Ah1 ah1 = ch1.d;
        Objects.requireNonNull(ch1);
        Handler handler = view.getHandler();
        if (handler != null) {
            handler.post(new RunnableC5974zh1(ch1, view, ah1, 1));
        }
    }
}
