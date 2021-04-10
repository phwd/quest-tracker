package defpackage;

import java.util.Objects;
import java.util.concurrent.Callable;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* renamed from: Vw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Vw1 implements Runnable {
    public final Zw1 F;
    public final Callable G;
    public final Callback H;

    public Vw1(Zw1 zw1, Callable callable, Callback callback) {
        this.F = zw1;
        this.G = callable;
        this.H = callback;
    }

    public void run() {
        Zw1 zw1 = this.F;
        Callable callable = this.G;
        Callback callback = this.H;
        Objects.requireNonNull(zw1);
        Boolean bool = Boolean.FALSE;
        try {
            bool = (Boolean) callable.call();
        } catch (Exception unused) {
        }
        PostTask.b(zw1.c, new Ww1(zw1, callback, bool), 0);
    }
}
