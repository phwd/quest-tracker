package X;

import com.facebook.core.mca.MailboxCoreJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1a2  reason: invalid class name */
public class AnonymousClass1a2 implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ C06461Zy A00;
    public final /* synthetic */ AnonymousClass1Zb A01;
    public final /* synthetic */ Number A02;
    public final /* synthetic */ Number A03;

    public AnonymousClass1a2(C06461Zy r1, AnonymousClass1Zb r2, Number number, Number number2) {
        this.A00 = r1;
        this.A01 = r2;
        this.A03 = number;
        this.A02 = number2;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = C06461Zy.A00("MCAMailboxDidRemoveParticipantFromGroupNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass1a6(this));
        this.A01.A04(A002, A003);
        MailboxCoreJNI.dispatchVOOOO(33, mailbox2, this.A03, this.A02, A003);
    }
}
