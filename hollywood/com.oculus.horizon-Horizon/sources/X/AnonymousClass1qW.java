package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.imagepipeline.producers.ProducerContext$ExtraKeys;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1qW  reason: invalid class name */
public abstract class AnonymousClass1qW<K, T extends Closeable> implements AnonymousClass1pP<T> {
    public final AnonymousClass1pP<T> A00;
    public final String A01;
    public final String A02;
    @GuardedBy("this")
    @VisibleForTesting
    public final Map<K, AnonymousClass1qW<K, T>.Multiplexer> A03 = new HashMap();

    public final synchronized void A00(K k, AnonymousClass1qW<K, T>.Multiplexer multiplexer) {
        Map<K, AnonymousClass1qW<K, T>.Multiplexer> map = this.A03;
        if (map.get(k) == multiplexer) {
            map.remove(k);
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1pP<TT;>;Ljava/lang/String;Ljava/lang/String;Z)V */
    public AnonymousClass1qW(AnonymousClass1pP r2, String str, @ProducerContext$ExtraKeys String str2) {
        this.A00 = r2;
        this.A02 = str;
        this.A01 = str2;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for r0v6, resolved type: java.util.Map<K, X.1qW<K, T>$Multiplexer> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006e, code lost:
        r3.A06.add(r5);
        r7 = X.C09931qV.A02(r3);
        r6 = X.C09931qV.A03(r3);
        r0 = X.C09931qV.A01(r3);
        r4 = r3.A04;
        r2 = r3.A00;
        r1 = r3.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0086, code lost:
        X.AnonymousClass1qU.A01(r7);
        X.AnonymousClass1qU.A02(r6);
        X.AnonymousClass1qU.A00(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008f, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0093, code lost:
        if (r4 == r3.A04) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0097, code lost:
        if (r4 == null) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009b, code lost:
        if ((r8 instanceof X.C09871ps) != false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009d, code lost:
        r4 = X.AnonymousClass1qa.A00((X.AnonymousClass1qa) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a5, code lost:
        r4 = X.AnonymousClass1qQ.A02((X.AnonymousClass1qQ) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d9, code lost:
        r0 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047 A[SYNTHETIC, Splitter:B:20:0x0047] */
    @Override // X.AnonymousClass1pP
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A7a(com.facebook.imagepipeline.producers.Consumer<T> r11, com.facebook.imagepipeline.producers.ProducerContext r12) {
        /*
        // Method dump skipped, instructions count: 237
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qW.A7a(X.1qD, X.1qU):void");
    }
}
