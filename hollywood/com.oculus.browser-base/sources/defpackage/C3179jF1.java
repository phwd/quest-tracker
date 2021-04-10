package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;

/* renamed from: jF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3179jF1 extends KV {
    public C3179jF1(Context context, Looper looper, C3800mv mvVar, WV wv, XV xv) {
        super(context, looper, 161, mvVar, wv, xv);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface e(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastService");
        if (queryLocalInterface instanceof LF1) {
            return (LF1) queryLocalInterface;
        }
        return new LF1(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] g() {
        return AbstractC1627aD1.b;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.AbstractC2129d7
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String m() {
        return "com.google.android.gms.cast.internal.ICastService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String n() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }
}
