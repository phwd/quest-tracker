package X;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: X.Bz  reason: case insensitive filesystem */
public final class RunnableC0128Bz implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.common.gcmcompat.GcmTaskService$Job";
    public final Bundle A00;
    public final C2 A01;
    public final String A02;
    public final /* synthetic */ C0 A03;

    public RunnableC0128Bz(C0 c0, String str, C2 c2, Bundle bundle) {
        this.A03 = c0;
        this.A02 = str;
        this.A01 = c2;
        this.A00 = bundle;
    }

    private void A00(int i) {
        String str;
        C0 c0 = this.A03;
        synchronized (c0.A03) {
            try {
                this.A01.A3d(i);
                str = this.A02;
            } catch (RemoteException e) {
                str = this.A02;
                C0139Dd.A0O("GcmTaskService", "Error reporting result of operation to scheduler for %s", str, e);
            } catch (Throwable th) {
                C0.A02(c0, this.A02);
                throw th;
            }
            C0.A02(c0, str);
        }
    }

    public final void A01() {
        try {
            this.A03.A04().execute(this);
        } catch (RejectedExecutionException e) {
            C0139Dd.A0L("GcmTaskService", "Executor is shutdown. onDestroy was called but main looper had an unprocessed start task message. The task will be retried with backoff delay.", e);
            A00(1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 214
        */
        throw new UnsupportedOperationException("Method not decompiled: X.RunnableC0128Bz.run():void");
    }
}
