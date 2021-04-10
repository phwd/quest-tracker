package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* renamed from: Mx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceConnectionC0786Mx implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f8513a;
    public final C0725Lx b;
    public int c = 0;
    public C3268jo1 d;
    public List e = new ArrayList();
    public Exception f;

    public ServiceConnectionC0786Mx(Runnable runnable) {
        C0725Lx lx = new C0725Lx();
        this.f8513a = runnable;
        this.b = lx;
    }

    public AbstractFutureC5208v90 a() {
        C1250Ul ul = new C1250Ul();
        C1433Xl xl = new C1433Xl(ul);
        ul.b = xl;
        ul.f9047a = AbstractC0665Kx.class;
        try {
            int i = this.c;
            if (i == 0) {
                this.e.add(ul);
            } else if (i == 1) {
                C3268jo1 jo1 = this.d;
                if (jo1 != null) {
                    ul.a(jo1);
                } else {
                    throw new IllegalStateException("ConnectionHolder state is incorrect.");
                }
            } else if (i == 2) {
                throw new IllegalStateException("Service has been disconnected.");
            } else if (i != 3) {
                throw new IllegalStateException("Connection state is invalid");
            } else {
                throw this.f;
            }
            String str = "ConnectionHolder, state = " + this.c;
            if (str != null) {
                ul.f9047a = str;
            }
        } catch (Exception e2) {
            xl.G.j(e2);
        }
        return xl;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        AbstractC4419qZ qZVar;
        Objects.requireNonNull(this.b);
        int i = AbstractBinderC4248pZ.f11073a;
        if (iBinder == null) {
            qZVar = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.customtabs.trusted.ITrustedWebActivityService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof AbstractC4419qZ)) {
                qZVar = new C4077oZ(iBinder);
            } else {
                qZVar = (AbstractC4419qZ) queryLocalInterface;
            }
        }
        this.d = new C3268jo1(qZVar, componentName);
        for (C1250Ul ul : this.e) {
            ul.a(this.d);
        }
        this.e.clear();
        this.c = 1;
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.d = null;
        this.f8513a.run();
        this.c = 2;
    }
}
