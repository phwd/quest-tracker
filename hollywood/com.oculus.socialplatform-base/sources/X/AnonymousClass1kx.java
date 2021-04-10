package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.imagepipeline.producers.ProducerContext$ExtraKeys;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1kx  reason: invalid class name */
public abstract class AnonymousClass1kx<K, T extends Closeable> implements AnonymousClass1j8<T> {
    public final AnonymousClass1j8<T> A00;
    public final String A01;
    public final String A02;
    @GuardedBy("this")
    @VisibleForTesting
    public final Map<K, AnonymousClass1kx<K, T>.Multiplexer> A03 = new HashMap();

    public final synchronized void A00(K k, AnonymousClass1kx<K, T>.Multiplexer multiplexer) {
        Map<K, AnonymousClass1kx<K, T>.Multiplexer> map = this.A03;
        if (map.get(k) == multiplexer) {
            map.remove(k);
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1j8<TT;>;Ljava/lang/String;Ljava/lang/String;Z)V */
    public AnonymousClass1kx(AnonymousClass1j8 r2, String str, @ProducerContext$ExtraKeys String str2) {
        this.A00 = r2;
        this.A02 = str;
        this.A01 = str2;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: java.util.Map<K, X.1kx<K, T>$Multiplexer> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0089, code lost:
        X.C10161kv.A01(r7);
        X.C10161kv.A02(r6);
        X.C10161kv.A00(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0092, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0096, code lost:
        if (r4 == r3.A04) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0098, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009a, code lost:
        if (r4 == null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009e, code lost:
        if ((r8 instanceof X.C10071kl) != false) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a0, code lost:
        r4 = X.AbstractC00820Ju.A00((X.AbstractC00820Ju) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a8, code lost:
        r4 = X.AnonymousClass0PZ.A03((X.AnonymousClass0PZ) r4);
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0047 A[SYNTHETIC] */
    @Override // X.AnonymousClass1j8
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A8d(com.facebook.imagepipeline.producers.Consumer<T> r11, com.facebook.imagepipeline.producers.ProducerContext r12) {
        /*
        // Method dump skipped, instructions count: 240
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1kx.A8d(X.1kf, X.1kv):void");
    }
}
