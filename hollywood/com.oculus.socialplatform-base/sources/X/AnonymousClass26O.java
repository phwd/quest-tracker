package X;

import com.facebook.messengervr.mca.MailboxMessengerVrJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.26O  reason: invalid class name */
public class AnonymousClass26O implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ AnonymousClass269 A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ AnonymousClass1Zb A02;

    public AnonymousClass26O(AnonymousClass269 r1, AnonymousClass1Zb r2, long j) {
        this.A00 = r1;
        this.A02 = r2;
        this.A01 = j;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = AnonymousClass269.A00("MCAMailboxMessengerVrDidLoadParticipantListNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass26N(this));
        this.A02.A04(A002, A003);
        MailboxMessengerVrJNI.dispatchVJOO(0, this.A01, mailbox2, A003);
    }
}
