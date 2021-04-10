package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.atomic.AtomicInteger;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1lM  reason: invalid class name */
public abstract class AnonymousClass1lM<T> implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.common.executors.StatefulRunnable";
    public final AtomicInteger A00 = new AtomicInteger(0);

    public void A01(Exception exc) {
    }

    public void A02(T t) {
    }

    public void A03(T t) {
    }

    public final void A00() {
        if (this.A00.compareAndSet(0, 2) && (this instanceof AnonymousClass1pm)) {
            AnonymousClass1pm r4 = (AnonymousClass1pm) this;
            AnonymousClass1qE r3 = r4.A02;
            AnonymousClass1qU r2 = r4.A01;
            String str = r4.A03;
            r3.A8K(r2, str);
            r3.A6V(r2, str, null);
            r4.A00.A03();
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:188:0x03cc */
    /* JADX DEBUG: Multi-variable search result rejected for r17v0, resolved type: X.1lM<T> */
    /* JADX DEBUG: Multi-variable search result rejected for r9v1, resolved type: android.content.ContentResolver */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v3, types: [X.1qa] */
    /* JADX WARN: Type inference failed for: r14v5, types: [X.1qQ] */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v14, types: [X.1qQ] */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v20 */
    /* JADX WARN: Type inference failed for: r14v21 */
    /* JADX WARN: Type inference failed for: r14v22 */
    /* JADX WARN: Type inference failed for: r14v23 */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02da, code lost:
        if (r10 != null) goto L_0x02dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02df, code lost:
        r9 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x032a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x032b, code lost:
        if (r10 != null) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:83:0x019a, B:129:0x02c1] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x032a A[ExcHandler: all (r0v41 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r10 
      PHI: (r10v4 android.content.res.AssetFileDescriptor) = (r10v5 android.content.res.AssetFileDescriptor), (r10v7 android.content.res.AssetFileDescriptor), (r10v9 android.content.res.AssetFileDescriptor), (r10v11 android.content.res.AssetFileDescriptor) binds: [B:129:0x02c1, B:130:?, B:83:0x019a, B:84:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:83:0x019a] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x03ce  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c9  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 1061
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1lM.run():void");
    }
}
