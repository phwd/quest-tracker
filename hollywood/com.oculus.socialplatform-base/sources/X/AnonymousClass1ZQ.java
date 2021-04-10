package X;

import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1ZQ  reason: invalid class name */
public class AnonymousClass1ZQ extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mca.Mailbox$9";
    public final /* synthetic */ Mailbox A00;
    public final /* synthetic */ NotificationScope A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1ZQ(Mailbox mailbox, NotificationScope notificationScope) {
        super("delete");
        this.A00 = mailbox;
        this.A01 = notificationScope;
    }

    public final void run() {
        this.A00.logoutAndDeleteNative(this.A01);
    }
}
