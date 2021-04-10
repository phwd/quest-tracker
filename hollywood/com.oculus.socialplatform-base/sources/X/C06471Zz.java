package X;

import com.facebook.core.mca.MailboxCoreJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1Zz  reason: invalid class name and case insensitive filesystem */
public class C06471Zz implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ C06461Zy A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ long A02;
    public final /* synthetic */ AnonymousClass1Zb A03;
    public final /* synthetic */ String A04 = "";
    public final /* synthetic */ String A05 = "";

    public C06471Zz(C06461Zy r2, AnonymousClass1Zb r3, long j, long j2) {
        this.A00 = r2;
        this.A03 = r3;
        this.A02 = j;
        this.A01 = j2;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = C06461Zy.A00("MCAMailboxDidSendMessageNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass1a8(this));
        this.A03.A04(A002, A003);
        MailboxCoreJNI.dispatchVIIIJJOOOOOOOOOOOOOOOOOOOO(10, 0, 0, 0, this.A02, this.A01, mailbox2, null, null, null, null, this.A04, null, null, this.A05, null, null, null, null, null, null, null, null, null, null, A003);
    }
}
