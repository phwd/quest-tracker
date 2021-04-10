package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: Xw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Xw1 implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public Zw1 f9244a;
    public ArrayList b = new ArrayList();
    public IBinder c;

    public Xw1(Zw1 zw1) {
        this.f9244a = zw1;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.c = iBinder;
        String.format("Got IBinder Service: %s", iBinder);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((Yw1) it.next()).a(this.c);
        }
        this.b.clear();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.c = null;
        Zw1 zw1 = this.f9244a;
        zw1.f.remove(componentName.getPackageName());
        if (zw1.f.isEmpty() && zw1.e == 0 && zw1.d != null) {
            zw1.d = null;
        }
    }
}
