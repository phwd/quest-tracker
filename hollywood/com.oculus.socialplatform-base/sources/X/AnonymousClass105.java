package X;

import com.google.common.annotations.GwtCompatible;
import com.oculus.library.database.contract.LibraryDBContract;

@GwtCompatible
/* renamed from: X.105  reason: invalid class name */
public final class AnonymousClass105 {
    public static int A00(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < LibraryDBContract.VERSION_NOT_INSTALLED) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }
}
