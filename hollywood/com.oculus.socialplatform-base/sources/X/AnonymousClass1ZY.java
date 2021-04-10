package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.NotificationCenter;
import com.facebook.msys.util.NotificationScope;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1ZY  reason: invalid class name */
public final class AnonymousClass1ZY {
    public final C05700wg<NotificationScope, AnonymousClass1ZW> A00 = new C05700wg<>();
    public final NotificationCenter.NotificationCallback A01 = new AnonymousClass1ZZ(this);
    public final NotificationCenter A02;

    public final NotificationScope A00(String str, @Nullable AnonymousClass1ZW r5) {
        NotificationScope notificationScope = new NotificationScope();
        this.A02.addObserver(this.A01, str, notificationScope);
        this.A00.put(notificationScope, r5);
        return notificationScope;
    }

    public AnonymousClass1ZY(NotificationCenter notificationCenter) {
        this.A02 = notificationCenter;
    }
}
