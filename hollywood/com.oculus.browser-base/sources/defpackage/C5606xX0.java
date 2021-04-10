package defpackage;

import J.N;
import android.os.Handler;
import android.os.Message;

/* renamed from: xX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5606xX0 extends C2900hf1 implements AbstractC5436wX0 {
    public final Handler l;
    public final boolean m;

    public C5606xX0(Handler handler, C3070if1 if1, boolean z) {
        super(if1, "SingleThreadTaskRunnerImpl", 2);
        this.l = handler;
        this.m = z;
    }

    @Override // defpackage.AbstractC5436wX0
    public boolean c() {
        Boolean bool;
        synchronized (this.h) {
            f();
        }
        if (this.f == 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(N.MdFi6sVQ(this.f));
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return this.l.getLooper().getThread() == Thread.currentThread();
    }

    @Override // defpackage.C2900hf1
    public void h() {
        Handler handler = this.l;
        if (handler != null) {
            if (this.m) {
                Message obtain = Message.obtain(handler, this.g);
                obtain.setAsynchronous(true);
                this.l.sendMessageAtFrontOfQueue(obtain);
                return;
            }
            handler.post(this.g);
        }
    }
}
