package X;

import com.facebook.messengervr.mca.MailboxMessengerVrJNI;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;
import com.oculus.messengervr.fb.Constants;

/* renamed from: X.26I  reason: invalid class name */
public class AnonymousClass26I implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ AnonymousClass269 A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ AnonymousClass1Zb A02;
    public final /* synthetic */ String A03 = Constants.FOLDER_INBOX;

    public AnonymousClass26I(AnonymousClass269 r2, AnonymousClass1Zb r3, int i) {
        this.A00 = r2;
        this.A02 = r3;
        this.A01 = i;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final /* bridge */ /* synthetic */ void onCompletion(Mailbox mailbox) {
        Mailbox mailbox2 = mailbox;
        String A002 = AnonymousClass269.A00("MCAMailboxMessengerVrDidLoadThreadListNotification");
        NotificationScope A003 = mailbox2.mNotificationCenterCallbackManager.A00(A002, new AnonymousClass26J(this));
        this.A02.A04(A002, A003);
        MailboxMessengerVrJNI.dispatchVIOOO(1, this.A01, mailbox2, this.A03, A003);
    }
}
