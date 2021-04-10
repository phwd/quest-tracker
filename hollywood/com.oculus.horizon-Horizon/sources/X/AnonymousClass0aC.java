package X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import com.facebook.push.fbns.ipc.IFbnsAIDLService;

/* renamed from: X.0aC  reason: invalid class name */
public class AnonymousClass0aC implements ServiceConnection {
    public final /* synthetic */ C02320aG A00;

    public AnonymousClass0aC(C02320aG r1) {
        this.A00 = r1;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            AnonymousClass0NO.A08("FbnsAIDLClientManager", "This operation should be run on UI thread");
        }
        C02320aG r1 = this.A00;
        IFbnsAIDLService asInterface = IFbnsAIDLService.Stub.asInterface(iBinder);
        synchronized (r1) {
            r1.A01 = asInterface;
            r1.A02 = AnonymousClass007.A0C;
            r1.notifyAll();
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            AnonymousClass0NO.A08("FbnsAIDLClientManager", "This operation should be run on UI thread");
        }
        C02320aG r1 = this.A00;
        synchronized (r1) {
            r1.A01 = null;
            r1.A02 = AnonymousClass007.A00;
        }
    }
}
