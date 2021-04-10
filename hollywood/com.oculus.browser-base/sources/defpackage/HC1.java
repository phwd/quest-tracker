package defpackage;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.location.zzad;
import java.util.Objects;

/* renamed from: HC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HC1 extends AbstractBinderC3518lE1 implements AbstractC3857nD1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC4609rg f8144a;

    public HC1(AbstractC4609rg rgVar) {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
        this.f8144a = rgVar;
    }

    @Override // defpackage.AbstractBinderC3518lE1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        AbstractC4609rg rgVar = this.f8144a;
        Status status = ((zzad) PE1.a(parcel, zzad.CREATOR)).F;
        AbstractC4439qg qgVar = (AbstractC4439qg) rgVar;
        Objects.requireNonNull(qgVar);
        qgVar.f(status);
        return true;
    }
}
