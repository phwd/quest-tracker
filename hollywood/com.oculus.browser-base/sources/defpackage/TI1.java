package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.oculus.os.Version;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: TI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class TI1 extends AbstractBinderC2487fC1 implements AbstractC4382qH1 {

    /* renamed from: a  reason: collision with root package name */
    public final C3246jh0 f8949a;
    public final Map b = new HashMap();

    public TI1(C3246jh0 jh0) {
        super("com.google.android.gms.cast.framework.internal.IMediaRouter");
        this.f8949a = jh0;
    }

    @Override // defpackage.AbstractBinderC2487fC1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        HH1 hh1 = null;
        Bundle bundle = null;
        switch (i) {
            case 1:
                Bundle bundle2 = (Bundle) AbstractC4376qF1.a(parcel, Bundle.CREATOR);
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
                    if (queryLocalInterface instanceof HH1) {
                        hh1 = (HH1) queryLocalInterface;
                    } else {
                        hh1 = new HH1(readStrongBinder);
                    }
                }
                C0629Kg0 b2 = C0629Kg0.b(bundle2);
                if (!this.b.containsKey(b2)) {
                    this.b.put(b2, new HashSet());
                }
                ((Set) this.b.get(b2)).add(new ZI1(hh1));
                parcel2.writeNoException();
                break;
            case 2:
                int readInt = parcel.readInt();
                C0629Kg0 b3 = C0629Kg0.b((Bundle) AbstractC4376qF1.a(parcel, Bundle.CREATOR));
                for (AbstractC0750Mg0 mg0 : (Set) this.b.get(b3)) {
                    this.f8949a.a(b3, mg0, readInt);
                }
                parcel2.writeNoException();
                break;
            case 3:
                for (AbstractC0750Mg0 mg02 : (Set) this.b.get(C0629Kg0.b((Bundle) AbstractC4376qF1.a(parcel, Bundle.CREATOR)))) {
                    this.f8949a.j(mg02);
                }
                parcel2.writeNoException();
                break;
            case 4:
                boolean i3 = this.f8949a.i(C0629Kg0.b((Bundle) AbstractC4376qF1.a(parcel, Bundle.CREATOR)), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(i3 ? 1 : 0);
                break;
            case 5:
                String readString = parcel.readString();
                Iterator it = this.f8949a.g().iterator();
                while (true) {
                    if (it.hasNext()) {
                        C2392eh0 eh0 = (C2392eh0) it.next();
                        if (eh0.c.equals(readString)) {
                            this.f8949a.k(eh0);
                        }
                    }
                }
                parcel2.writeNoException();
                break;
            case 6:
                C3246jh0 jh0 = this.f8949a;
                jh0.k(jh0.d());
                parcel2.writeNoException();
                break;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                boolean equals = this.f8949a.h().c.equals(this.f8949a.d().c);
                parcel2.writeNoException();
                int i4 = AbstractC4376qF1.f11128a;
                parcel2.writeInt(equals ? 1 : 0);
                break;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                String readString2 = parcel.readString();
                Iterator it2 = this.f8949a.g().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        C2392eh0 eh02 = (C2392eh0) it2.next();
                        if (eh02.c.equals(readString2)) {
                            bundle = eh02.r;
                        }
                    }
                }
                parcel2.writeNoException();
                int i5 = AbstractC4376qF1.f11128a;
                if (bundle == null) {
                    parcel2.writeInt(0);
                    break;
                } else {
                    parcel2.writeInt(1);
                    bundle.writeToParcel(parcel2, 1);
                    break;
                }
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                String str = this.f8949a.h().c;
                parcel2.writeNoException();
                parcel2.writeString(str);
                break;
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                parcel2.writeNoException();
                parcel2.writeInt(12451009);
                break;
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                for (Set<AbstractC0750Mg0> set : this.b.values()) {
                    for (AbstractC0750Mg0 mg03 : set) {
                        this.f8949a.j(mg03);
                    }
                }
                this.b.clear();
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
