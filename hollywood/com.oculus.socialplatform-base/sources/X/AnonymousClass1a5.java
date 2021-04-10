package X;

import com.facebook.core.mca.MailboxCoreJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1a5  reason: invalid class name */
public class AnonymousClass1a5 implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ C06461Zy A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ AnonymousClass1Zb A02;

    public AnonymousClass1a5(C06461Zy r1, AnonymousClass1Zb r2, long j) {
        this.A00 = r1;
        this.A02 = r2;
        this.A01 = j;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = C06461Zy.A00("MCAMailboxDidMarkThreadAsReadNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass1aC(this));
        this.A02.A04(A002, A003);
        MailboxCoreJNI.dispatchVJOO(27, this.A01, mailbox2, A003);
    }
}
