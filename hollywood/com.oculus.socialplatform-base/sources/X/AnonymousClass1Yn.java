package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mediamanager.MediaManager;
import com.facebook.msys.mci.AuthDataContext;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.NetworkSession;
import com.facebook.msys.mci.NotificationCenter;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
@Deprecated
/* renamed from: X.1Yn  reason: invalid class name */
public final class AnonymousClass1Yn {
    public static final AnonymousClass1Yn A03 = new AnonymousClass1Yn();
    @Nullable
    public MediaManager A00;
    @Nullable
    public AuthDataContext A01;
    @Nullable
    public Database A02;

    @GuardedBy("this")
    @Nullable
    public final synchronized NetworkSession A00() {
        NetworkSession networkSession;
        synchronized (C06311Ys.class) {
            networkSession = C06311Ys.A04.A02;
        }
        return networkSession;
    }

    @GuardedBy("this")
    @Nullable
    public final synchronized NotificationCenter A01() {
        NotificationCenter notificationCenter;
        synchronized (C06311Ys.class) {
            notificationCenter = C06311Ys.A04.A03;
        }
        return notificationCenter;
    }
}
