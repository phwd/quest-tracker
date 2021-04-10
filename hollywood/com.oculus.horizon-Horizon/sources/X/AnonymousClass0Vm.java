package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Vm  reason: invalid class name */
public final class AnonymousClass0Vm {
    public static final BlockingQueue<Runnable> A00 = new LinkedBlockingQueue(10);
    public static final ThreadFactory A01 = new AnonymousClass0Vl();
    @Nullable
    public static volatile Executor A02;
}
