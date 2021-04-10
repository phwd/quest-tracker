package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: vD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceConnectionC5219vD implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BinderC5899zD f11466a;

    public ServiceConnectionC5219vD(BinderC5899zD zDVar) {
        this.f11466a = zDVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        CY cy;
        BinderC5899zD zDVar = this.f11466a;
        int i = AZ.f7676a;
        if (iBinder == null) {
            cy = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("org.chromium.components.browser_ui.photo_picker.IDecoderService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof CY)) {
                cy = new BY(iBinder);
            } else {
                cy = (CY) queryLocalInterface;
            }
        }
        zDVar.r = cy;
        for (AbstractC5559xD xDVar : this.f11466a.q) {
            EC0 ec0 = (EC0) xDVar;
            ec0.j0 = true;
            if (ec0.f7944J != null) {
                ec0.O.g();
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        AbstractC1220Ua0.a("ImageDecoderHost", "Service has unexpectedly disconnected", new Object[0]);
        this.f11466a.r = null;
    }
}
