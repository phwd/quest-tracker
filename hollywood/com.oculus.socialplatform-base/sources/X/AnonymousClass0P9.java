package X;

import androidx.annotation.VisibleForTesting;

@VisibleForTesting
/* renamed from: X.0P9  reason: invalid class name */
public class AnonymousClass0P9<K, V> {
    public int A00;
    public boolean A01;
    public final AbstractC00820Ju<V> A02;
    public final K A03;

    /* JADX WARN: Incorrect args count in method signature: (TK;LX/0Ju<TV;>;Lcom/facebook/imagepipeline/cache/CountingMemoryCache$EntryStateObserver<TK;>;)V */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public AnonymousClass0P9(Object obj, AbstractC00820Ju r3) {
        this.A03 = obj;
        AbstractC00820Ju<V> A002 = AbstractC00820Ju.A00(r3);
        if (A002 != null) {
            this.A02 = A002;
            this.A00 = 0;
            this.A01 = false;
            return;
        }
        throw null;
    }
}
