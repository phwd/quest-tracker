package defpackage;

import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: Fm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Fm1 implements Runnable {
    public final Lm1 F;

    public Fm1(Lm1 lm1) {
        this.F = lm1;
    }

    public void run() {
        Lm1 lm1 = this.F;
        Objects.requireNonNull(lm1);
        PostTask.b(C3070if1.b, new Km1(lm1.f), 0);
    }
}
