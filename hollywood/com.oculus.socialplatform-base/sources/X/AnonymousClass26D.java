package X;

import com.facebook.messengervr.mca.MailboxMessengerVrJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.26D  reason: invalid class name */
public class AnonymousClass26D implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ AnonymousClass269 A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ AnonymousClass1Zb A02;
    public final /* synthetic */ Number A03;
    public final /* synthetic */ Number A04;
    public final /* synthetic */ boolean A05 = true;

    public AnonymousClass26D(AnonymousClass269 r2, AnonymousClass1Zb r3, int i, Number number, Number number2) {
        this.A00 = r2;
        this.A02 = r3;
        this.A01 = i;
        this.A03 = number;
        this.A04 = number2;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = AnonymousClass269.A00("MCAMailboxMessengerVrDidLoadContactsNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass26P(this));
        this.A02.A04(A002, A003);
        MailboxMessengerVrJNI.dispatchVIOOOOOOOOOZZ(2, this.A01, mailbox2, null, null, this.A03, null, this.A04, null, null, A003, false, this.A05);
    }
}
