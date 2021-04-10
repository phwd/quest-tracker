package X;

import com.facebook.core.mca.MailboxCoreJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1a0  reason: invalid class name */
public class AnonymousClass1a0 implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ C06461Zy A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ AnonymousClass1Zb A02;
    public final /* synthetic */ String A03;

    public AnonymousClass1a0(C06461Zy r1, AnonymousClass1Zb r2, long j, String str) {
        this.A00 = r1;
        this.A02 = r2;
        this.A01 = j;
        this.A03 = str;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = C06461Zy.A00("MCAMailboxDidSendMessageNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass1a9(this));
        this.A02.A04(A002, A003);
        MailboxCoreJNI.dispatchVIJOOOOOOOOOOOO(6, 0, this.A01, mailbox2, this.A03, null, null, null, null, null, null, null, null, null, A003);
    }
}
