package X;

import java.util.Locale;

/* renamed from: X.0w7  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractRunnableC08170w7 implements Runnable {
    public static final String __redex_internal_original_name = "okhttp3.internal.NamedRunnable";
    public final String A00;

    public abstract void A00();

    public AbstractRunnableC08170w7(String str, Object... objArr) {
        this.A00 = String.format(Locale.US, str, objArr);
    }

    public final void run() {
        Thread currentThread = Thread.currentThread();
        String name = currentThread.getName();
        currentThread.setName(this.A00);
        try {
            A00();
        } finally {
            currentThread.setName(name);
        }
    }
}
