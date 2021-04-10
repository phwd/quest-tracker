package X;

import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.util.NotificationScope;

/* renamed from: X.1ZO  reason: invalid class name */
public class AnonymousClass1ZO extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mca.Mailbox$10";
    public final /* synthetic */ Mailbox A00;
    public final /* synthetic */ NotificationScope A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1ZO(Mailbox mailbox, NotificationScope notificationScope) {
        super("logoutAndEncrypt");
        this.A00 = mailbox;
        this.A01 = notificationScope;
    }

    public final void run() {
        this.A00.logoutAndEncryptNative(this.A01);
    }
}
