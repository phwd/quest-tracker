package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.internal.BaseGmsClient;

/* renamed from: ng  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ServiceConnectionC3926ng implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final int f10507a;
    public final /* synthetic */ BaseGmsClient b;

    public ServiceConnectionC3926ng(BaseGmsClient baseGmsClient, int i) {
        this.b = baseGmsClient;
        this.f10507a = i;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HY hy;
        if (iBinder == null) {
            this.b.w();
            return;
        }
        synchronized (this.b.m) {
            BaseGmsClient baseGmsClient = this.b;
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof HY)) {
                hy = new HY(iBinder);
            } else {
                hy = (HY) queryLocalInterface;
            }
            baseGmsClient.n = hy;
        }
        this.b.t(0, this.f10507a);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        BaseGmsClient baseGmsClient;
        synchronized (this.b.m) {
            baseGmsClient = this.b;
            baseGmsClient.n = null;
        }
        Handler handler = baseGmsClient.k;
        handler.sendMessage(handler.obtainMessage(6, this.f10507a, 1));
    }
}
