package X;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.1OP  reason: invalid class name */
public class AnonymousClass1OP implements ThreadFactory {
    public AtomicInteger A00 = new AtomicInteger();
    public final /* synthetic */ AnonymousClass1O7 A01;

    public AnonymousClass1OP(AnonymousClass1O7 r2) {
        this.A01 = r2;
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, AnonymousClass006.A03("Default network thread ", this.A00.getAndIncrement()));
    }
}
