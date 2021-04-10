package defpackage;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* renamed from: kg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3413kg {

    /* renamed from: a  reason: collision with root package name */
    public Object f10293a;
    public boolean b = false;
    public final /* synthetic */ BaseGmsClient c;
    public final int d;
    public final Bundle e;
    public final /* synthetic */ BaseGmsClient f;

    public AbstractC3413kg(BaseGmsClient baseGmsClient, int i, Bundle bundle) {
        this.f = baseGmsClient;
        Boolean bool = Boolean.TRUE;
        this.c = baseGmsClient;
        this.f10293a = bool;
        this.d = i;
        this.e = bundle;
    }

    public final void a() {
        synchronized (this) {
            this.f10293a = null;
        }
        synchronized (this.c.q) {
            this.c.q.remove(this);
        }
    }

    public abstract void b(ConnectionResult connectionResult);

    public final void c(Object obj) {
        Boolean bool = (Boolean) obj;
        int i = this.d;
        PendingIntent pendingIntent = null;
        if (i != 0) {
            if (i != 10) {
                this.f.u(1, null);
                Bundle bundle = this.e;
                if (bundle != null) {
                    pendingIntent = (PendingIntent) bundle.getParcelable("pendingIntent");
                }
                b(new ConnectionResult(this.d, pendingIntent));
                return;
            }
            this.f.u(1, null);
            throw new IllegalStateException(String.format("A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. ", getClass().getSimpleName(), this.f.n(), this.f.m()));
        } else if (!d()) {
            this.f.u(1, null);
            b(new ConnectionResult(8, null));
        }
    }

    public abstract boolean d();
}
