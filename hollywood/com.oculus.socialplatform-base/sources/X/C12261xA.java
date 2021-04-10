package X;

import io.reactivex.annotations.NonNull;

/* renamed from: X.1xA  reason: invalid class name and case insensitive filesystem */
public final class C12261xA {
    public static void A00(@NonNull Throwable th) {
        if (th instanceof VirtualMachineError) {
            throw th;
        } else if (th instanceof ThreadDeath) {
            throw th;
        } else if (th instanceof LinkageError) {
            throw th;
        }
    }
}
