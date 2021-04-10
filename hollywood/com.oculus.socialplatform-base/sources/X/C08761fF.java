package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

@VisibleForTesting
/* renamed from: X.1fF  reason: invalid class name and case insensitive filesystem */
public final class C08761fF extends WeakReference<C08701f8<?>> {
    @Nullable
    public AnonymousClass1fR<?> A00;
    public final AbstractC06491aL A01;
    public final boolean A02;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: X.1fR<?> */
    public C08761fF(@NonNull AbstractC06491aL r3, @NonNull C08701f8<?> r4, @NonNull ReferenceQueue<? super C08701f8<?>> referenceQueue, boolean z) {
        super(r4, referenceQueue);
        AnonymousClass1fR r0;
        AnonymousClass1S2.A00(r3);
        this.A01 = r3;
        boolean z2 = r4.A03;
        if (!z2 || !z) {
            r0 = (AnonymousClass1fR<Z>) false;
        } else {
            AnonymousClass1fR r02 = (AnonymousClass1fR<Z>) r4.A02;
            AnonymousClass1S2.A00(r02);
            r0 = r02;
        }
        this.A00 = r0;
        this.A02 = z2;
    }
}
