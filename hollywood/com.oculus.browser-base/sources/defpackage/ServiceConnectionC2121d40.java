package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import org.chromium.base.task.PostTask;

/* renamed from: d40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceConnectionC2121d40 extends Y30 implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9745a;
    public AbstractC1950c40 b;
    public boolean c;
    public boolean d;
    public Handler e = new Handler();
    public Intent f;

    public ServiceConnectionC2121d40(Context context, Intent intent, AbstractC1950c40 c40) {
        this.f9745a = context;
        this.b = c40;
        this.f = intent;
    }

    public final void c() {
        AbstractC1950c40 c40 = this.b;
        if (c40 != null) {
            PostTask.c(Zo1.f9374a, new R5((Z5) c40));
            this.b = null;
            if (this.c) {
                this.f9745a.unbindService(this);
                this.c = false;
            }
            this.e.removeCallbacksAndMessages(null);
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Object obj;
        if (this.b != null) {
            if (iBinder == null) {
                obj = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("org.chromium.IsReadyToPayService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof X30)) {
                    obj = new V30(iBinder);
                } else {
                    obj = (X30) queryLocalInterface;
                }
            }
            if (obj == null) {
                c();
                return;
            }
            this.d = true;
            try {
                ((V30) obj).c(this);
                this.e.postDelayed(new RunnableC1779b40(this), 400);
            } catch (Throwable unused) {
                c();
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        c();
    }
}
