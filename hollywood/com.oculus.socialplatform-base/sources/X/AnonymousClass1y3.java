package X;

import io.reactivex.annotations.NonNull;
import java.util.concurrent.Callable;

/* renamed from: X.1y3  reason: invalid class name */
public final class AnonymousClass1y3 {
    public static void A01(@NonNull Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!(th instanceof AnonymousClass1YA) && !(th instanceof C13611zs) && !(th instanceof IllegalStateException) && !(th instanceof NullPointerException) && !(th instanceof IllegalArgumentException) && !(th instanceof AnonymousClass1Ox)) {
            th = new C05841Bj(th);
        }
        th.printStackTrace();
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    @NonNull
    public static AbstractC12361xL A00(@NonNull Callable<AbstractC12361xL> callable) {
        try {
            AbstractC12361xL call = callable.call();
            AnonymousClass219.A01(call, "Scheduler Callable result can't be null");
            return call;
        } catch (Throwable th) {
            throw C12301xE.A00(th);
        }
    }
}
