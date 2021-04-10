package defpackage;

import org.chromium.base.task.PostTask;

/* renamed from: NP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NP extends AbstractC2032cb {
    public final /* synthetic */ OP i;

    public NP(OP op) {
        this.i = op;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return this.i.b();
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        PostTask.c(Zo1.f9374a, new MP(this, (byte[]) obj));
        this.i.e.g();
    }
}
