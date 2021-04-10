package X;

import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.mci.Execution;

/* renamed from: X.1Zf  reason: invalid class name */
public class AnonymousClass1Zf implements AnonymousClass1Z6 {
    public final /* synthetic */ Mailbox A00;

    public AnonymousClass1Zf(Mailbox mailbox) {
        this.A00 = mailbox;
    }

    @Override // X.AnonymousClass1Z6
    public final void A9T(AnonymousClass1YZ<Mailbox> r3) {
        Execution.executePossiblySync(new C06361Zg(this, r3), 1);
    }
}
