package X;

import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1Za  reason: invalid class name */
public class AnonymousClass1Za implements AnonymousClass1YZ<Mailbox> {
    public final /* synthetic */ AnonymousClass1Zb A00;

    public AnonymousClass1Za(AnonymousClass1Zb r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1YZ
    public final void onCompletion(Mailbox mailbox) {
        AnonymousClass1ZY r4 = mailbox.mNotificationCenterCallbackManager;
        AnonymousClass1Zb r0 = this.A00;
        String str = r0.A03;
        NotificationScope notificationScope = r0.A01;
        r4.A00.remove(notificationScope);
        r4.A02.removeObserver(r4.A01, str, notificationScope);
    }
}
