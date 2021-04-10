package X;

import com.facebook.msys.mci.AppState;
import com.facebook.msys.mci.NetworkSession;
import com.facebook.msys.mci.NotificationCenter;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1Ys  reason: invalid class name and case insensitive filesystem */
public final class C06311Ys {
    public static final C06311Ys A04 = new C06311Ys();
    @DoNotStrip
    public static final Lock lock = new ReentrantLock();
    public final HashSet<String> A00 = new HashSet<>();
    @Nullable
    public volatile AppState A01;
    @Nullable
    public volatile NetworkSession A02;
    @Nullable
    public volatile NotificationCenter A03;
}
