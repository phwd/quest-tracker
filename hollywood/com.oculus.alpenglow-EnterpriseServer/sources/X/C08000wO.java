package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0wO  reason: invalid class name and case insensitive filesystem */
public final class C08000wO {
    public static final int A00 = 5;
    public static final BlockingQueue<Runnable> A01 = new LinkedBlockingQueue(10);
    public static final ThreadFactory A02 = new ThreadFactoryC08010wP();
    @Nullable
    public static volatile Executor A03;
}
