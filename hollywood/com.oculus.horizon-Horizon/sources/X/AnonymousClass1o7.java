package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1o7  reason: invalid class name */
public final class AnonymousClass1o7 implements ThreadFactory {
    public final int A00 = 10;
    public final String A01;
    public final AtomicInteger A02 = new AtomicInteger(1);
    public final boolean A03;

    public AnonymousClass1o7(String str) {
        this.A01 = str;
        this.A03 = true;
    }

    public final Thread newThread(Runnable runnable) {
        String str;
        AnonymousClass1o8 r3 = new AnonymousClass1o8(this, runnable);
        if (this.A03) {
            str = AnonymousClass006.A06(this.A01, "-", this.A02.getAndIncrement());
        } else {
            str = this.A01;
        }
        return new Thread(r3, str);
    }
}
