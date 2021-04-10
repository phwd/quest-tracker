package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.oculus.os.Version;
import java.util.Objects;

/* renamed from: OS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OS0 extends AbstractBinderC2487fC1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PS0 f8625a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OS0(PS0 ps0, JC1 jc1) {
        super("com.google.android.gms.cast.framework.ISessionProxy");
        this.f8625a = ps0;
    }

    @Override // defpackage.AbstractBinderC2487fC1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        long j;
        switch (i) {
            case 1:
                BinderC0773Mq0 mq0 = new BinderC0773Mq0(this.f8625a);
                parcel2.writeNoException();
                AbstractC4376qF1.b(parcel2, mq0);
                return true;
            case 2:
                ((C2922hn) this.f8625a).m((Bundle) AbstractC4376qF1.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 3:
                ((C2922hn) this.f8625a).m((Bundle) AbstractC4376qF1.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 4:
                int i3 = AbstractC4376qF1.f11128a;
                int i4 = parcel.readInt() != 0 ? 1 : 0;
                C2922hn hnVar = (C2922hn) this.f8625a;
                Objects.requireNonNull(hnVar);
                try {
                    NH1 nh1 = (NH1) hnVar.g;
                    Parcel c = nh1.c();
                    c.writeInt(i4);
                    c.writeInt(0);
                    nh1.f(6, c);
                } catch (RemoteException unused) {
                    NF1 nf1 = C2922hn.d;
                    Object[] objArr = {"disconnectFromDevice", AbstractC5403wH1.class.getSimpleName()};
                    if (nf1.d()) {
                        nf1.c("Unable to call %s on %s.", objArr);
                    }
                }
                hnVar.b(0);
                parcel2.writeNoException();
                return true;
            case 5:
                C2922hn hnVar2 = (C2922hn) this.f8625a;
                Objects.requireNonNull(hnVar2);
                SE0.e("Must be called from the main thread.");
                ML0 ml0 = hnVar2.l;
                if (ml0 == null) {
                    j = 0;
                } else {
                    j = ml0.f() - hnVar2.l.b();
                }
                parcel2.writeNoException();
                parcel2.writeLong(j);
                return true;
            case 6:
                parcel2.writeNoException();
                parcel2.writeInt(12451009);
                return true;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                C2922hn hnVar3 = (C2922hn) this.f8625a;
                Objects.requireNonNull(hnVar3);
                hnVar3.m = CastDevice.x((Bundle) AbstractC4376qF1.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                C2922hn hnVar4 = (C2922hn) this.f8625a;
                Objects.requireNonNull(hnVar4);
                hnVar4.m = CastDevice.x((Bundle) AbstractC4376qF1.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
