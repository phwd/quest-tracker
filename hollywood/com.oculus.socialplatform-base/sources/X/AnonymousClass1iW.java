package X;

import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import javax.annotation.Nullable;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1iW  reason: invalid class name */
public final class AnonymousClass1iW<T> {
    @Nullable
    @VisibleForTesting
    public AnonymousClass1iX<T> A00;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1iX<T> A01;
    public final SparseArray<AnonymousClass1iX<T>> A02 = new SparseArray<>();

    /* JADX WARN: Incorrect args count in method signature: (LX/1iX<TT;>;)V */
    public static synchronized void A00(AnonymousClass1iW r3, AnonymousClass1iX r4) {
        synchronized (r3) {
            AnonymousClass1iX r2 = (AnonymousClass1iX<I>) r4.A02;
            AnonymousClass1iX r1 = (AnonymousClass1iX<I>) r4.A01;
            if (r2 != null) {
                r2.A01 = r1;
            }
            if (r1 != null) {
                r1.A02 = r2;
            }
            r4.A02 = null;
            r4.A01 = null;
            if (r4 == r3.A00) {
                r3.A00 = r1;
            }
            if (r4 == r3.A01) {
                r3.A01 = r2;
            }
        }
    }
}
