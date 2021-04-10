package X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import com.facebook.mobileconfigservice.service.IMobileConfig;
import com.facebook.mobileconfigservice.service.IMobileConfig$Stub$Proxy;
import java.util.concurrent.CountDownLatch;

public final class GM implements ServiceConnection {
    public final CountDownLatch A00 = new CountDownLatch(1);
    public volatile IMobileConfig A01;
    public final /* synthetic */ GO A02;

    public GM(GO go) {
        this.A02 = go;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IMobileConfig iMobileConfig$Stub$Proxy;
        Log.d("MobileConfigBaseClient", "Connected to MobileConfigService");
        if (iBinder == null) {
            iMobileConfig$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.facebook.mobileconfigservice.service.IMobileConfig");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMobileConfig)) {
                iMobileConfig$Stub$Proxy = new IMobileConfig$Stub$Proxy(iBinder);
            } else {
                iMobileConfig$Stub$Proxy = (IMobileConfig) queryLocalInterface;
            }
        }
        this.A01 = iMobileConfig$Stub$Proxy;
        this.A00.countDown();
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        Log.d("MobileConfigBaseClient", "Disconnected from MobileConfigService");
    }
}
