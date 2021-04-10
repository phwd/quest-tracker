package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: ri  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceConnectionC4615ri implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11214a = false;
    public final BlockingQueue b = new LinkedBlockingQueue();

    public IBinder a() {
        SE0.h("BlockingServiceConnection.getService() called on main thread");
        if (!this.f11214a) {
            this.f11214a = true;
            return (IBinder) this.b.take();
        }
        throw new IllegalStateException("Cannot call get on this connection more than once");
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.b.add(iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }
}
