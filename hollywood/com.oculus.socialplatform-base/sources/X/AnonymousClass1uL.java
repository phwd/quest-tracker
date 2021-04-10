package X;

import com.facebook.msys.mci.Execution;

/* renamed from: X.1uL  reason: invalid class name */
public class AnonymousClass1uL implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.msys.mci.Execution$1";
    public final /* synthetic */ int A00;

    public AnonymousClass1uL(int i) {
        this.A00 = i;
    }

    public final void run() {
        Execution.nativeStartExecutor(this.A00);
    }
}
