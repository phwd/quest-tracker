package X;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.06M  reason: invalid class name */
public final class AnonymousClass06M {
    @GuardedBy("mLock")
    public Handler A00;
    @GuardedBy("mLock")
    public HandlerThread A01;
    @GuardedBy("mLock")
    public int A02 = 0;
    public Handler.Callback A03 = new AnonymousClass06H(this);
    public final int A04 = 10000;
    public final Object A05 = new Object();

    public static void A00(AnonymousClass06M r5, Runnable runnable) {
        synchronized (r5.A05) {
            if (r5.A01 == null) {
                HandlerThread handlerThread = new HandlerThread("fonts", 10);
                r5.A01 = handlerThread;
                handlerThread.start();
                r5.A00 = new Handler(r5.A01.getLooper(), r5.A03);
                r5.A02++;
            }
            r5.A00.removeMessages(0);
            Handler handler = r5.A00;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }
}
