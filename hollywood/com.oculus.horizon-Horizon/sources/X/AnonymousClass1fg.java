package X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.facebook.mobileconfigservice.service.IMobileConfig;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nullable;

/* renamed from: X.1fg  reason: invalid class name */
public class AnonymousClass1fg implements ServiceConnection {
    public final String A00;
    public final CountDownLatch A01 = new CountDownLatch(1);
    @Nullable
    public volatile IMobileConfig A02;
    public final /* synthetic */ AnonymousClass1e9 A03;

    public AnonymousClass1fg(AnonymousClass1e9 r3, String str) {
        this.A03 = r3;
        this.A00 = str;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.A02 = IMobileConfig.Stub.asInterface(iBinder);
        this.A01.countDown();
    }

    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
