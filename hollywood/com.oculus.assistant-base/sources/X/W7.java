package X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.oculus.aidl.IAuthService2;

public final class W7 implements ServiceConnection {
    public final /* synthetic */ W9 A00;

    public W7(W9 w9) {
        this.A00 = w9;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C0139Dd.A09("AuthServiceClient", "onServiceConnected");
        W9 w9 = this.A00;
        w9.A00 = IAuthService2.Stub.asInterface(iBinder);
        w9.A05.set(false);
        try {
            w9.A00();
            w9.A04.A3w();
        } catch (SecurityException e) {
            Log.e("AuthServiceClient", "onServiceConnected: Could not get credentials. Device out of date.", e);
            w9.A04.onError(e);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        C0139Dd.A09("AuthServiceClient", "onServiceDisconnected");
        W9 w9 = this.A00;
        w9.A00 = null;
        w9.A05.set(false);
    }
}
