package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Messenger;
import android.util.Log;
import java.util.Objects;

/* renamed from: Gq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceConnectionC0408Gq0 implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0469Hq0 f8115a;

    public ServiceConnectionC0408Gq0(C0469Hq0 hq0) {
        this.f8115a = hq0;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        AbstractC0347Fq0 fq0;
        synchronized (this.f8115a.i) {
            C0591Jq0 jq0 = this.f8115a.k;
            new Messenger(iBinder);
            Object obj = C0591Jq0.f8315a;
            Objects.requireNonNull(jq0);
            C0591Jq0 jq02 = this.f8115a.k;
            int i = AbstractBinderC0286Eq0.f7979a;
            if (iBinder == null) {
                fq0 = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.oculus.aidl.OVRServiceInterface");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof AbstractC0347Fq0)) {
                    fq0 = new C0225Dq0(iBinder);
                } else {
                    fq0 = (AbstractC0347Fq0) queryLocalInterface;
                }
            }
            jq02.d = fq0;
            this.f8115a.i.notify();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Log.e("OVRServiceManager", "Service Disconnected");
        this.f8115a.k.c();
    }
}
