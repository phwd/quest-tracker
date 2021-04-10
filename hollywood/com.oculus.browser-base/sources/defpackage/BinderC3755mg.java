package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.zza;
import java.util.Objects;

/* renamed from: mg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BinderC3755mg extends AbstractBinderC2658gC1 implements GY {

    /* renamed from: a  reason: collision with root package name */
    public BaseGmsClient f10437a;
    public final int b;

    public BinderC3755mg(BaseGmsClient baseGmsClient, int i) {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
        this.f10437a = baseGmsClient;
        this.b = i;
    }

    @Override // defpackage.AbstractBinderC2658gC1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            SE0.i(this.f10437a, "onPostInitComplete can be called only once per call to getRemoteService");
            this.f10437a.r(parcel.readInt(), parcel.readStrongBinder(), (Bundle) AbstractC4546rF1.a(parcel, Bundle.CREATOR), this.b);
            this.f10437a = null;
        } else if (i == 2) {
            parcel.readInt();
            Bundle bundle = (Bundle) AbstractC4546rF1.a(parcel, Bundle.CREATOR);
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        } else if (i != 3) {
            return false;
        } else {
            int readInt = parcel.readInt();
            IBinder readStrongBinder = parcel.readStrongBinder();
            zza zza = (zza) AbstractC4546rF1.a(parcel, zza.CREATOR);
            SE0.i(this.f10437a, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Objects.requireNonNull(zza, "null reference");
            this.f10437a.z = zza;
            Bundle bundle2 = zza.F;
            SE0.i(this.f10437a, "onPostInitComplete can be called only once per call to getRemoteService");
            this.f10437a.r(readInt, readStrongBinder, bundle2, this.b);
            this.f10437a = null;
        }
        parcel2.writeNoException();
        return true;
    }
}
