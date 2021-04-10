package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
public final class UH extends AnonymousClass2e {
    @NonNull
    public static final Executor A02 = new AnonymousClass2c();
    @NonNull
    public static final Executor A03 = new AnonymousClass2b();
    public static volatile UH A04;
    @NonNull
    public AnonymousClass2e A00;
    @NonNull
    public AnonymousClass2e A01;

    @NonNull
    public static UH A00() {
        if (A04 == null) {
            synchronized (UH.class) {
                if (A04 == null) {
                    A04 = new UH();
                }
            }
        }
        return A04;
    }

    public UH() {
        UG ug = new UG();
        this.A01 = ug;
        this.A00 = ug;
    }
}
