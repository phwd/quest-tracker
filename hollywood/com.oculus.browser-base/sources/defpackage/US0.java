package defpackage;

import android.content.Context;
import android.os.Parcel;
import com.google.android.gms.cast.framework.CastOptions;
import java.util.Objects;

/* renamed from: US0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class US0 extends AbstractBinderC2487fC1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GG1 f9029a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public US0(GG1 gg1, VC1 vc1) {
        super("com.google.android.gms.cast.framework.ISessionProvider");
        this.f9029a = gg1;
    }

    @Override // defpackage.AbstractBinderC2487fC1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            String readString = parcel.readString();
            GG1 gg1 = this.f9029a;
            Objects.requireNonNull(gg1);
            Context context = gg1.f8080a;
            String str = gg1.b;
            CastOptions castOptions = gg1.d;
            VY c = new C2922hn(context, str, readString, castOptions, AbstractC1435Xm.c, new C4549rG1(), new C2490fD1(context, castOptions, gg1.e)).c();
            parcel2.writeNoException();
            AbstractC4376qF1.b(parcel2, c);
            return true;
        } else if (i == 2) {
            boolean z = this.f9029a.d.f9648J;
            parcel2.writeNoException();
            int i3 = AbstractC4376qF1.f11128a;
            parcel2.writeInt(z ? 1 : 0);
            return true;
        } else if (i == 3) {
            String str2 = this.f9029a.b;
            parcel2.writeNoException();
            parcel2.writeString(str2);
            return true;
        } else if (i != 4) {
            return false;
        } else {
            parcel2.writeNoException();
            parcel2.writeInt(12451009);
            return true;
        }
    }
}
