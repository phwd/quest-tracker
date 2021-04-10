package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1S2  reason: invalid class name */
public final class AnonymousClass1S2 {
    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;)TT; */
    @NonNull
    public static void A00(@Nullable Object obj) {
        if (obj == null) {
            throw new NullPointerException("Argument must not be null");
        }
    }

    public static void A01(boolean z, @NonNull String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }
}
