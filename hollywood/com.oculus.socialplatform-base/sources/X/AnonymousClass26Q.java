package X;

import com.facebook.messengervr.mca.MailboxMessengerVr$ThreadViewDataObserverCallback;
import com.facebook.messengervr.mca.MailboxMessengerVrJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.simplejni.NativeHolder;

/* renamed from: X.26Q  reason: invalid class name */
public class AnonymousClass26Q implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ long A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ MailboxMessengerVr$ThreadViewDataObserverCallback A02;
    public final /* synthetic */ AnonymousClass269 A03;
    public final /* synthetic */ AnonymousClass1Zb A04;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final void onCompletion(Mailbox mailbox) {
        this.A04.A03(new AnonymousClass28B(this.A03, (NativeHolder) MailboxMessengerVrJNI.dispatchOIJJOOZZZZ(4, 0, this.A01, this.A00, mailbox, this.A02, false, false, false, false)));
    }

    public AnonymousClass26Q(AnonymousClass269 r1, long j, long j2, MailboxMessengerVr$ThreadViewDataObserverCallback mailboxMessengerVr$ThreadViewDataObserverCallback, AnonymousClass1Zb r7) {
        this.A03 = r1;
        this.A01 = j;
        this.A00 = j2;
        this.A02 = mailboxMessengerVr$ThreadViewDataObserverCallback;
        this.A04 = r7;
    }
}
