package X;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.facebook.push.fbns.ipc.IFbnsAIDLService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.0aG  reason: invalid class name and case insensitive filesystem */
public final class C02320aG {
    @GuardedBy("this")
    public int A00 = 0;
    @GuardedBy("this")
    @Nullable
    public IFbnsAIDLService A01 = null;
    @GuardedBy("this")
    public Integer A02 = AnonymousClass007.A00;
    public final Context A03;
    public final ExecutorService A04 = Executors.newFixedThreadPool(5);
    public final ServiceConnection A05 = new AnonymousClass0aC(this);

    public static void A00(C02320aG r2) {
        synchronized (r2) {
            int i = r2.A00 - 1;
            r2.A00 = i;
            if (i == 0) {
                r2.A01 = null;
                r2.A02 = AnonymousClass007.A00;
                r2.A03.unbindService(r2.A05);
            }
        }
        Thread.currentThread().getId();
    }

    public static synchronized boolean A01(C02320aG r5, Intent intent) {
        boolean z;
        synchronized (r5) {
            z = false;
            try {
                AnonymousClass1Di A08 = C02600ao.A00().A08(C02880bg.A01(((AbstractC01570Vx) AnonymousClass0W2.A00).A05()));
                ServiceConnection serviceConnection = r5.A05;
                Context context = r5.A03;
                if (A08.A04(intent, serviceConnection, context)) {
                    r5.A02 = AnonymousClass007.A01;
                } else {
                    AnonymousClass0NO.A08("FbnsAIDLClientManager", "open failed: bindService failure, do unbind to let service shutdown");
                    context.unbindService(serviceConnection);
                }
            } catch (SecurityException e) {
                AnonymousClass0NO.A0B("FbnsAIDLClientManager", "open failed: bindService throw SecurityException", e);
                z = true;
            }
        }
        return z;
    }

    public final void finalize() throws Throwable {
        this.A04.shutdown();
    }

    public C02320aG(Context context) {
        this.A03 = context;
    }
}
