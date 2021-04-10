package X;

import com.facebook.messengervr.mca.MailboxMessengerVr$LoadThreadViewDataOptions;
import com.facebook.messengervr.mca.MailboxMessengerVrJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.26E  reason: invalid class name */
public class AnonymousClass26E implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ AnonymousClass269 A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ long A02;
    public final /* synthetic */ long A03;
    public final /* synthetic */ MailboxMessengerVr$LoadThreadViewDataOptions A04;
    public final /* synthetic */ AnonymousClass1Zb A05;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = AnonymousClass269.A00("MCAMailboxMessengerVrDidLoadThreadViewDataNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass26F(this));
        this.A05.A04(A002, A003);
        MailboxMessengerVrJNI.dispatchVIJJOOO(3, this.A01, this.A03, this.A02, mailbox2, A003, this.A04);
    }

    public AnonymousClass26E(AnonymousClass269 r1, AnonymousClass1Zb r2, int i, long j, long j2, MailboxMessengerVr$LoadThreadViewDataOptions mailboxMessengerVr$LoadThreadViewDataOptions) {
        this.A00 = r1;
        this.A05 = r2;
        this.A01 = i;
        this.A03 = j;
        this.A02 = j2;
        this.A04 = mailboxMessengerVr$LoadThreadViewDataOptions;
    }
}
