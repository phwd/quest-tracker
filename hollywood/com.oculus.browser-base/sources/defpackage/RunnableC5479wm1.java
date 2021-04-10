package defpackage;

import android.os.Looper;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: wm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5479wm1 implements Runnable {
    public final C5649xm1 F;

    public RunnableC5479wm1(C5649xm1 xm1) {
        this.F = xm1;
    }

    public void run() {
        C5649xm1 xm1 = this.F;
        Objects.requireNonNull(xm1);
        Object obj = ThreadUtils.f10596a;
        Looper.myQueue().addIdleHandler(xm1);
        xm1.b();
    }
}
