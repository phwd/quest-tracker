package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import org.chromium.webapk.lib.runtime_library.IWebApkApi;

/* renamed from: Pw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Pw1 implements Yw1 {
    public Pw1(Nw1 nw1) {
    }

    @Override // defpackage.Yw1
    public void a(IBinder iBinder) {
        AbstractC5099uZ uZVar;
        if (iBinder == null) {
            AbstractC3100ip1.f10165a.a("WebApk.WebApkService.BindSuccess", false);
            return;
        }
        try {
            int i = AbstractBinderC4929tZ.f11349a;
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IWebApkApi.Stub.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof AbstractC5099uZ)) {
                uZVar = new C4759sZ(iBinder);
            } else {
                uZVar = (AbstractC5099uZ) queryLocalInterface;
            }
            b(uZVar);
            AbstractC3100ip1.f10165a.a("WebApk.WebApkService.BindSuccess", true);
        } catch (RemoteException e) {
            AbstractC1220Ua0.f("WebApk", "WebApkAPI use failed.", e);
        }
    }

    public abstract void b(AbstractC5099uZ uZVar);
}
