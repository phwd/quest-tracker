package X;

import com.facebook.messengervr.mca.MailboxMessengerVrJNI;
import com.facebook.msys.mca.Mailbox;

/* renamed from: X.26R  reason: invalid class name */
public class AnonymousClass26R implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ AnonymousClass28B A00;
    public final /* synthetic */ AnonymousClass269 A01;
    public final /* synthetic */ AnonymousClass1Zb A02;

    public AnonymousClass26R(AnonymousClass269 r1, AnonymousClass28B r2, AnonymousClass1Zb r3) {
        this.A01 = r1;
        this.A00 = r2;
        this.A02 = r3;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final void onCompletion(Mailbox mailbox) {
        MailboxMessengerVrJNI.dispatchVOO(5, mailbox, this.A00.mNativeHolder);
        this.A02.A03(null);
    }
}
