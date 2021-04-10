package defpackage;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: CS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CS0 extends C2900hf1 implements BS0 {
    public AtomicInteger l = new AtomicInteger();
    public volatile boolean m;

    public CS0(C3070if1 if1) {
        super(if1, "SequencedTaskRunnerImpl", 1);
    }

    @Override // defpackage.C2900hf1
    public void e() {
        this.m = true;
        if (this.l.getAndIncrement() == 0) {
            super.e();
        }
    }

    @Override // defpackage.C2900hf1
    public void g() {
        super.g();
        if (this.l.decrementAndGet() <= 0) {
            return;
        }
        if (!this.m) {
            super.h();
        } else {
            super.e();
        }
    }

    @Override // defpackage.C2900hf1
    public void h() {
        if (this.l.getAndIncrement() == 0) {
            super.h();
        }
    }
}
