package X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.internal.IGmsServiceBroker;
import com.google.android.gms.common.internal.IGmsServiceBroker$Stub$zza;

public final class RN implements ServiceConnection {
    public final int A00;
    public final /* synthetic */ RO A01;

    public RN(RO ro, int i) {
        this.A01 = ro;
        this.A00 = i;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IGmsServiceBroker iGmsServiceBroker$Stub$zza;
        boolean z;
        RO ro = this.A01;
        if (iBinder == null) {
            synchronized (ro.A0D) {
                z = false;
                if (ro.A00 == 3) {
                    z = true;
                }
            }
            int i = 4;
            if (z) {
                i = 5;
                ro.A05 = true;
            }
            Handler handler = ro.A0A;
            handler.sendMessage(handler.obtainMessage(i, ro.A04.get(), 16));
            return;
        }
        synchronized (ro.A0E) {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) {
                iGmsServiceBroker$Stub$zza = new IGmsServiceBroker$Stub$zza(iBinder);
            } else {
                iGmsServiceBroker$Stub$zza = (IGmsServiceBroker) queryLocalInterface;
            }
            ro.A03 = iGmsServiceBroker$Stub$zza;
        }
        int i2 = this.A00;
        Handler handler2 = ro.A0A;
        handler2.sendMessage(handler2.obtainMessage(7, i2, -1, new GG(ro, 0)));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        RO ro = this.A01;
        synchronized (ro.A0E) {
            ro.A03 = null;
        }
        Handler handler = ro.A0A;
        handler.sendMessage(handler.obtainMessage(6, this.A00, 1));
    }
}
