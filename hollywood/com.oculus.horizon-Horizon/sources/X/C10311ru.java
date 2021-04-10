package X;

import androidx.annotation.VisibleForTesting;

@VisibleForTesting
/* renamed from: X.1ru  reason: invalid class name and case insensitive filesystem */
public class C10311ru<K, V> {
    public int A00;
    public boolean A01;
    public final AnonymousClass1qa<V> A02;
    public final K A03;

    /* JADX WARN: Incorrect args count in method signature: (TK;LX/1qa<TV;>;Lcom/facebook/imagepipeline/cache/CountingMemoryCache$EntryStateObserver<TK;>;)V */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public C10311ru(Object obj, AnonymousClass1qa r3) {
        this.A03 = obj;
        AnonymousClass1qa<V> A002 = AnonymousClass1qa.A00(r3);
        if (A002 != null) {
            this.A02 = A002;
            this.A00 = 0;
            this.A01 = false;
            return;
        }
        throw null;
    }
}
