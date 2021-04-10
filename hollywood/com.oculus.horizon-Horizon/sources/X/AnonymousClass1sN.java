package X;

import androidx.annotation.VisibleForTesting;
import java.util.LinkedList;
import javax.annotation.Nullable;

@VisibleForTesting
/* renamed from: X.1sN  reason: invalid class name */
public class AnonymousClass1sN<I> {
    public int A00;
    @Nullable
    public AnonymousClass1sN<I> A01;
    @Nullable
    public AnonymousClass1sN<I> A02 = null;
    public LinkedList<I> A03;

    /* JADX WARN: Incorrect args count in method signature: (LX/1sN<TI;>;ILjava/util/LinkedList<TI;>;LX/1sN<TI;>;)V */
    public AnonymousClass1sN(@Nullable int i, LinkedList linkedList) {
        this.A00 = i;
        this.A03 = linkedList;
        this.A01 = null;
    }

    public final String toString() {
        return AnonymousClass006.A02("LinkedEntry(key: ", this.A00, ")");
    }
}
