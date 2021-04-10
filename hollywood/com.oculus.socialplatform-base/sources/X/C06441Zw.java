package X;

import com.facebook.core.mca.MailboxCoreJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;
import java.util.Date;

/* renamed from: X.1Zw  reason: invalid class name and case insensitive filesystem */
public class C06441Zw implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ C06461Zy A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ AnonymousClass1Zb A02;
    public final /* synthetic */ Date A03;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = C06461Zy.A00("MCAMailboxDidMuteThreadNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new C06451Zx(this));
        this.A02.A04(A002, A003);
        MailboxCoreJNI.dispatchVJOOO(24, this.A01, mailbox2, this.A03, A003);
    }

    public C06441Zw(C06461Zy r1, AnonymousClass1Zb r2, long j, Date date) {
        this.A00 = r1;
        this.A02 = r2;
        this.A01 = j;
        this.A03 = date;
    }
}
