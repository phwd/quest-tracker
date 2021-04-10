package X;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.AnyThread;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0oL  reason: invalid class name and case insensitive filesystem */
public final class C03710oL extends AbstractC00890Mb {
    public ArrayList<AbstractC00880Ma> A00 = new ArrayList<>();
    public ArrayList<AbstractC00880Ma> A01 = new ArrayList<>();
    public final Object A02 = new Object();
    public final Handler A03 = new Handler(Looper.getMainLooper());
    public final Runnable A04 = new RunnableC00900Mc(this);

    @Override // X.AbstractC00890Mb
    @AnyThread
    public final void A00(AbstractC00880Ma r3) {
        synchronized (this.A02) {
            this.A00.remove(r3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        r3.A03.post(r3.A04);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    @Override // X.AbstractC00890Mb
    @androidx.annotation.AnyThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(X.AbstractC00880Ma r4) {
        /*
            r3 = this;
            android.os.Looper r0 = android.os.Looper.getMainLooper()
            java.lang.Thread r1 = r0.getThread()
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            if (r1 != r0) goto L_0x0036
            java.lang.Object r2 = r3.A02
            monitor-enter(r2)
            java.util.ArrayList<X.0Ma> r0 = r3.A00     // Catch:{ all -> 0x0033 }
            boolean r0 = r0.contains(r4)     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x001b
            monitor-exit(r2)     // Catch:{ all -> 0x0033 }
            return
        L_0x001b:
            java.util.ArrayList<X.0Ma> r0 = r3.A00     // Catch:{ all -> 0x0033 }
            r0.add(r4)     // Catch:{ all -> 0x0033 }
            int r1 = r0.size()     // Catch:{ all -> 0x0033 }
            r0 = 1
            if (r1 == r0) goto L_0x0028
            r0 = 0
        L_0x0028:
            monitor-exit(r2)     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0032
            android.os.Handler r1 = r3.A03
            java.lang.Runnable r0 = r3.A04
            r1.post(r0)
        L_0x0032:
            return
        L_0x0033:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x0036:
            r4.A8x()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03710oL.A01(X.0Ma):void");
    }
}
