package X;

import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1ZP  reason: invalid class name */
public class AnonymousClass1ZP extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mca.Mailbox$6";
    public final /* synthetic */ int A00 = 1;
    public final /* synthetic */ Mailbox A01;
    public final /* synthetic */ NotificationScope A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1ZP(Mailbox mailbox, NotificationScope notificationScope) {
        super("setState");
        this.A01 = mailbox;
        this.A02 = notificationScope;
    }

    public final void run() {
        this.A01.setStateNative(this.A00, this.A02);
    }
}
