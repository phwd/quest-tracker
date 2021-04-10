package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import java.util.Objects;

/* renamed from: sU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceConnectionC4749sU implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5089uU f11278a;

    public ServiceConnectionC4749sU(C5089uU uUVar, AbstractC4579rU rUVar) {
        this.f11278a = uUVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C5089uU uUVar = this.f11278a;
        if (uUVar.e != null) {
            uUVar.g = new Messenger(iBinder);
            try {
                Message obtain = Message.obtain((Handler) null, 2);
                C5089uU uUVar2 = this.f11278a;
                obtain.replyTo = uUVar2.f11412a;
                Objects.requireNonNull(uUVar2.d);
                Bundle bundle = new Bundle();
                bundle.putString("ssb_service:ssb_package_name", this.f11278a.e.getPackageName());
                bundle.putBoolean("ssb_service:chrome_holds_account_update_permission", C4067oU.a());
                obtain.setData(bundle);
                this.f11278a.g.send(obtain);
            } catch (RemoteException e) {
                Log.w("GSAServiceConnection", "GSAServiceConnection - remote call failed", e);
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f11278a.g = null;
    }
}
