package X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import com.facebook.papaya.IPapayaService;
import com.facebook.papaya.IPapayaService$Stub$Proxy;
import com.google.common.util.concurrent.SettableFuture;

public final class H6 implements ServiceConnection {
    public final SettableFuture A00;
    public final /* synthetic */ H7 A01;

    public H6(H7 h7, SettableFuture settableFuture) {
        this.A01 = h7;
        this.A00 = settableFuture;
    }

    public final void onBindingDied(ComponentName componentName) {
        C0139Dd.A04(H7.class, "onBindingDied");
    }

    public final void onNullBinding(ComponentName componentName) {
        C0139Dd.A04(H7.class, "onNullBinding");
        this.A00.setException(new IllegalStateException("PapayaService cannot be used"));
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IPapayaService iPapayaService$Stub$Proxy;
        C0139Dd.A03(H7.class, "onServiceConnected");
        SettableFuture settableFuture = this.A00;
        H7 h7 = this.A01;
        if (iBinder == null) {
            iPapayaService$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.facebook.papaya.IPapayaService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPapayaService)) {
                iPapayaService$Stub$Proxy = new IPapayaService$Stub$Proxy(iBinder);
            } else {
                iPapayaService$Stub$Proxy = (IPapayaService) queryLocalInterface;
            }
        }
        settableFuture.set(new H5(h7, iPapayaService$Stub$Proxy, this));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        C0139Dd.A04(H7.class, "onServiceDisconnected");
    }
}
