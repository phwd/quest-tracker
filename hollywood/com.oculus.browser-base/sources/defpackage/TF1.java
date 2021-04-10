package defpackage;

import java.util.Objects;

/* renamed from: TF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class TF1 implements Runnable {
    public final QF1 F;

    public TF1(QF1 qf1) {
        this.F = qf1;
    }

    public final void run() {
        QF1 qf1 = this.F;
        Objects.requireNonNull(qf1);
        synchronized (QF1.b) {
            if (qf1.e != -1) {
                qf1.d(15);
            }
        }
    }
}
