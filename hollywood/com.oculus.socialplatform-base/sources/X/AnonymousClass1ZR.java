package X;

import com.facebook.msys.mca.Mailbox;

/* renamed from: X.1ZR  reason: invalid class name */
public class AnonymousClass1ZR implements AnonymousClass1Z6 {
    public final /* synthetic */ Mailbox A00;

    public AnonymousClass1ZR(Mailbox mailbox) {
        this.A00 = mailbox;
    }

    @Override // X.AnonymousClass1Z6
    public final void A9T(AnonymousClass1YZ<Mailbox> r2) {
        r2.onCompletion(this.A00);
    }
}
