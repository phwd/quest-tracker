package X;

import java.util.concurrent.Executor;

/* renamed from: X.0Hi  reason: invalid class name and case insensitive filesystem */
public class ExecutorC01460Hi implements Executor {
    public ThreadLocal<Integer> A00 = new ThreadLocal<>();

    private void A00() {
        Integer num = this.A00.get();
        if (num == null) {
            num = 0;
        }
        int intValue = num.intValue() - 1;
        ThreadLocal<Integer> threadLocal = this.A00;
        if (intValue == 0) {
            threadLocal.remove();
        } else {
            threadLocal.set(Integer.valueOf(intValue));
        }
    }

    public final void execute(Runnable runnable) {
        Integer num = this.A00.get();
        if (num == null) {
            num = 0;
        }
        int intValue = num.intValue() + 1;
        this.A00.set(Integer.valueOf(intValue));
        if (intValue <= 15) {
            try {
                runnable.run();
            } catch (Throwable th) {
                A00();
                throw th;
            }
        } else {
            AnonymousClass0Hj.A03.A01.execute(runnable);
        }
        A00();
    }
}
