package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.browser.customtabs.CustomTabsSessionToken;

/* renamed from: KE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class KE0 implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8352a = new Object();
    public final AbstractC5947zY b;
    public AbstractC3222jZ c;
    public String d;
    public boolean e;

    public KE0(CustomTabsSessionToken customTabsSessionToken) {
        IBinder iBinder;
        AbstractC5947zY zYVar = customTabsSessionToken.f9465a;
        if (zYVar == null) {
            iBinder = null;
        } else {
            iBinder = ((C5607xY) zYVar).f11613a;
        }
        if (iBinder != null) {
            this.b = AbstractBinderC5777yY.c(iBinder);
            return;
        }
        throw new IllegalArgumentException("Provided session must have binder.");
    }

    public final boolean a(Bundle bundle) {
        if (this.c == null) {
            return false;
        }
        synchronized (this.f8352a) {
            try {
                this.c.r0(this.b, bundle);
            } catch (RemoteException unused) {
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        AbstractC3222jZ jZVar;
        int i = AbstractBinderC3052iZ.f10143a;
        if (iBinder == null) {
            jZVar = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.customtabs.IPostMessageService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof AbstractC3222jZ)) {
                jZVar = new C2881hZ(iBinder);
            } else {
                jZVar = (AbstractC3222jZ) queryLocalInterface;
            }
        }
        this.c = jZVar;
        if (this.e) {
            a(null);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.c = null;
    }
}
