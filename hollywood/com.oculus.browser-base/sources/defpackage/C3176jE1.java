package defpackage;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.util.Log;

/* renamed from: jE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3176jE1 extends KV {
    public final String D;
    public final IH1 E;
    public final SD1 F;

    public C3176jE1(Context context, Looper looper, WV wv, XV xv, String str, C3800mv mvVar) {
        super(context, looper, 23, mvVar, wv, xv);
        IH1 ih1 = new IH1(this);
        this.E = ih1;
        this.D = str;
        this.F = new SD1(context, ih1);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.AbstractC2129d7
    public final void disconnect() {
        synchronized (this.F) {
            if (a()) {
                try {
                    this.F.a();
                    this.F.c();
                } catch (Exception e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
            }
            super.disconnect();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public /* synthetic */ IInterface e(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof JD1 ? (JD1) queryLocalInterface : new JD1(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.AbstractC2129d7
    public /* bridge */ /* synthetic */ int getMinApkVersion() {
        return 11925000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public Bundle i() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.D);
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public /* bridge */ /* synthetic */ String m() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public /* bridge */ /* synthetic */ String n() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    public final Location z() {
        SD1 sd1 = this.F;
        sd1.f8885a.f8215a.c();
        JD1 jd1 = (JD1) sd1.f8885a.a();
        String packageName = sd1.b.getPackageName();
        Parcel c = jd1.c();
        c.writeString(packageName);
        c = Parcel.obtain();
        try {
            jd1.f10053a.transact(21, c, c, 0);
            c.readException();
            c.recycle();
            return (Location) PE1.a(c, Location.CREATOR);
        } catch (RuntimeException e) {
            throw e;
        } finally {
            c.recycle();
        }
    }
}
