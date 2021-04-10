package com.google.android.gms.cast.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ReconnectionService extends Service {
    public static final NF1 F = new NF1("ReconnectionService");
    public AbstractC5576xI1 G;

    public IBinder onBind(Intent intent) {
        try {
            JI1 ji1 = (JI1) this.G;
            Parcel c = ji1.c();
            AbstractC4376qF1.c(c, intent);
            Parcel d = ji1.d(3, c);
            IBinder readStrongBinder = d.readStrongBinder();
            d.recycle();
            return readStrongBinder;
        } catch (RemoteException unused) {
            NF1 nf1 = F;
            Object[] objArr = {"onBind", AbstractC5576xI1.class.getSimpleName()};
            if (!nf1.d()) {
                return null;
            }
            nf1.c("Unable to call %s on %s.", objArr);
            return null;
        }
    }

    public void onCreate() {
        VY vy;
        VY vy2;
        AbstractC5576xI1 xi1;
        C1557Zm c = C1557Zm.c(this);
        SS0 b = c.b();
        Objects.requireNonNull(b);
        JI1 ji1 = null;
        try {
            WI1 wi1 = b.b;
            Parcel d = wi1.d(7, wi1.c());
            vy = BinderC0773Mq0.d(d.readStrongBinder());
            d.recycle();
        } catch (RemoteException unused) {
            NF1 nf1 = SS0.f8894a;
            Object[] objArr = {"getWrappedThis", WI1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
            vy = null;
        }
        SE0.e("Must be called from the main thread.");
        C5397wF1 wf1 = c.f;
        Objects.requireNonNull(wf1);
        try {
            C3018iI1 ii1 = wf1.b;
            Parcel d2 = ii1.d(5, ii1.c());
            vy2 = BinderC0773Mq0.d(d2.readStrongBinder());
            d2.recycle();
        } catch (RemoteException unused2) {
            NF1 nf12 = C5397wF1.f11533a;
            Object[] objArr2 = {"getWrappedThis", C3018iI1.class.getSimpleName()};
            if (nf12.d()) {
                nf12.c("Unable to call %s on %s.", objArr2);
            }
            vy2 = null;
        }
        NF1 nf13 = AbstractC2499fG1.f9911a;
        OG1 a2 = AbstractC2499fG1.a(getApplicationContext());
        BinderC0773Mq0 mq0 = new BinderC0773Mq0(this);
        try {
            Parcel c2 = a2.c();
            AbstractC4376qF1.b(c2, mq0);
            AbstractC4376qF1.b(c2, vy);
            AbstractC4376qF1.b(c2, vy2);
            Parcel d3 = a2.d(5, c2);
            IBinder readStrongBinder = d3.readStrongBinder();
            int i = AbstractBinderC5406wI1.f11537a;
            if (readStrongBinder == null) {
                xi1 = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.cast.framework.IReconnectionService");
                if (queryLocalInterface instanceof AbstractC5576xI1) {
                    xi1 = (AbstractC5576xI1) queryLocalInterface;
                } else {
                    xi1 = new JI1(readStrongBinder);
                }
            }
            d3.recycle();
            ji1 = xi1;
        } catch (RemoteException unused3) {
            NF1 nf14 = AbstractC2499fG1.f9911a;
            Object[] objArr3 = {"newReconnectionServiceImpl", OG1.class.getSimpleName()};
            if (nf14.d()) {
                nf14.c("Unable to call %s on %s.", objArr3);
            }
        }
        this.G = ji1;
        try {
            JI1 ji12 = ji1;
            ji12.f(1, ji12.c());
        } catch (RemoteException unused4) {
            NF1 nf15 = F;
            Object[] objArr4 = {"onCreate", AbstractC5576xI1.class.getSimpleName()};
            if (nf15.d()) {
                nf15.c("Unable to call %s on %s.", objArr4);
            }
        }
        super.onCreate();
    }

    public void onDestroy() {
        try {
            JI1 ji1 = (JI1) this.G;
            ji1.f(4, ji1.c());
        } catch (RemoteException unused) {
            NF1 nf1 = F;
            Object[] objArr = {"onDestroy", AbstractC5576xI1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            JI1 ji1 = (JI1) this.G;
            Parcel c = ji1.c();
            AbstractC4376qF1.c(c, intent);
            c.writeInt(i);
            c.writeInt(i2);
            Parcel d = ji1.d(2, c);
            int readInt = d.readInt();
            d.recycle();
            return readInt;
        } catch (RemoteException unused) {
            NF1 nf1 = F;
            Object[] objArr = {"onStartCommand", AbstractC5576xI1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
            return 1;
        }
    }
}
