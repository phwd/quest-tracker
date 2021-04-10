package X;

import java.util.ArrayDeque;

public final class fV implements AnonymousClass7l {
    public final int A00;
    public final AnonymousClass7l A01;
    public final /* synthetic */ AnonymousClass7p A02;

    public fV(AnonymousClass7p r1, int i, AnonymousClass7l r3) {
        this.A02 = r1;
        this.A00 = i;
        this.A01 = r3;
    }

    @Override // X.AnonymousClass7l
    public final void A48() {
        Runnable runnable;
        int i = this.A00;
        C0139Dd.A0F("UploadJobHandlerManager", "onExit jobId: %d", Integer.valueOf(i));
        AnonymousClass7p r2 = this.A02;
        synchronized (r2) {
            AnonymousClass7o r1 = (AnonymousClass7o) r2.A01.get(i);
            if (r1 != null) {
                r1.A00 = null;
                r1.A00 = null;
                ArrayDeque arrayDeque = r1.A01;
                if (!(arrayDeque == null || (runnable = (Runnable) arrayDeque.poll()) == null)) {
                    runnable.run();
                }
            }
            this.A01.A48();
        }
    }

    @Override // X.AnonymousClass7l
    public final void A4R(boolean z) {
        C0139Dd.A0F("UploadJobHandlerManager", "onVoluntaryCompletion jobId: %d", Integer.valueOf(this.A00));
        this.A01.A4R(z);
    }
}
