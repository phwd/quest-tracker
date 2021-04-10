package defpackage;

import android.os.Parcel;
import com.google.android.gms.cast.LaunchOptions;
import java.util.Objects;

/* renamed from: dn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BinderC2239dn extends AbstractBinderC2487fC1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2922hn f9806a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BinderC2239dn(C2922hn hnVar, VE1 ve1) {
        super("com.google.android.gms.cast.framework.ICastConnectionController");
        this.f9806a = hnVar;
    }

    @Override // defpackage.AbstractBinderC2487fC1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            C2922hn hnVar = this.f9806a;
            YV yv = hnVar.k;
            if (yv != null) {
                Objects.requireNonNull(hnVar.i);
                yv.f(new C2331eH1(yv, readString, readString2)).b(new C2409en(this.f9806a, "joinApplication"));
            }
            parcel2.writeNoException();
            return true;
        } else if (i == 2) {
            String readString3 = parcel.readString();
            LaunchOptions launchOptions = (LaunchOptions) AbstractC4376qF1.a(parcel, LaunchOptions.CREATOR);
            C2922hn hnVar2 = this.f9806a;
            YV yv2 = hnVar2.k;
            if (yv2 != null) {
                Objects.requireNonNull(hnVar2.i);
                yv2.f(new C5740yG1(yv2, readString3, launchOptions)).b(new C2409en(this.f9806a, "launchApplication"));
            }
            parcel2.writeNoException();
            return true;
        } else if (i == 3) {
            String readString4 = parcel.readString();
            C2922hn hnVar3 = this.f9806a;
            YV yv3 = hnVar3.k;
            if (yv3 != null) {
                Objects.requireNonNull(hnVar3.i);
                yv3.f(new C5233vH1(yv3, readString4));
            }
            parcel2.writeNoException();
            return true;
        } else if (i == 4) {
            C2922hn.l(this.f9806a, parcel.readInt());
            parcel2.writeNoException();
            return true;
        } else if (i != 5) {
            return false;
        } else {
            parcel2.writeNoException();
            parcel2.writeInt(12451009);
            return true;
        }
    }
}
