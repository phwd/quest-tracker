package X;

import com.facebook.core.mca.MailboxCoreJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;
import com.oculus.messengervr.fb.Constants;

/* renamed from: X.1a3  reason: invalid class name */
public class AnonymousClass1a3 implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ C06461Zy A00;
    public final /* synthetic */ AnonymousClass1Zb A01;
    public final /* synthetic */ Number A02;
    public final /* synthetic */ String A03 = Constants.FOLDER_INBOX;

    public AnonymousClass1a3(C06461Zy r2, AnonymousClass1Zb r3, Number number) {
        this.A00 = r2;
        this.A01 = r3;
        this.A02 = number;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = C06461Zy.A00("MCAMailboxDidFetchThreadsPageNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass1a7(this));
        this.A01.A04(A002, A003);
        MailboxCoreJNI.dispatchVOOOO(38, mailbox2, this.A03, this.A02, A003);
    }
}
