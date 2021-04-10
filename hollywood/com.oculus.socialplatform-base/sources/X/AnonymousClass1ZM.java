package X;

import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.mci.Execution;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1ZM  reason: invalid class name */
public class AnonymousClass1ZM extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mca.Mailbox$7";
    public final /* synthetic */ Mailbox A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1ZM(Mailbox mailbox) {
        super("setMailboxStateActive");
        this.A00 = mailbox;
    }

    public final void run() {
        Mailbox mailbox = this.A00;
        AnonymousClass1Z6 r0 = mailbox.mSynchronousMailboxProvider;
        if (r0 == null) {
            r0 = new AnonymousClass1ZR(mailbox);
            mailbox.mSynchronousMailboxProvider = r0;
        }
        AnonymousClass1Zb r3 = new AnonymousClass1Zb(r0);
        NotificationScope A002 = mailbox.mNotificationCenterCallbackManager.A00("MCAMailboxDidSetStateNotification", new AnonymousClass1ZN(mailbox, r3));
        r3.A04("MCAMailboxDidSetStateNotification", A002);
        Execution.executePossiblySync(new AnonymousClass1ZP(mailbox, A002), 1);
    }
}
