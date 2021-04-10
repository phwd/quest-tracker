package defpackage;

import android.os.Parcel;
import com.oculus.os.Version;

/* renamed from: UC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class UC1 extends AbstractBinderC2487fC1 {

    /* renamed from: a  reason: collision with root package name */
    public final TS0 f9011a;
    public final Class b;

    public UC1(TS0 ts0, Class cls) {
        super("com.google.android.gms.cast.framework.ISessionManagerListener");
        this.f9011a = ts0;
        this.b = cls;
    }

    @Override // defpackage.AbstractBinderC2487fC1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        TS0 ts0;
        TS0 ts02;
        TS0 ts03;
        TS0 ts04;
        TS0 ts05;
        TS0 ts06;
        TS0 ts07;
        TS0 ts08;
        TS0 ts09;
        boolean z = false;
        switch (i) {
            case 1:
                BinderC0773Mq0 mq0 = new BinderC0773Mq0(this.f9011a);
                parcel2.writeNoException();
                AbstractC4376qF1.b(parcel2, mq0);
                return true;
            case 2:
                PS0 ps0 = (PS0) BinderC0773Mq0.f(BinderC0773Mq0.d(parcel.readStrongBinder()));
                if (this.b.isInstance(ps0) && (ts0 = this.f9011a) != null) {
                    ts0.j((PS0) this.b.cast(ps0));
                }
                parcel2.writeNoException();
                return true;
            case 3:
                VY d = BinderC0773Mq0.d(parcel.readStrongBinder());
                String readString = parcel.readString();
                PS0 ps02 = (PS0) BinderC0773Mq0.f(d);
                if (this.b.isInstance(ps02) && (ts02 = this.f9011a) != null) {
                    ts02.i((PS0) this.b.cast(ps02), readString);
                }
                parcel2.writeNoException();
                return true;
            case 4:
                VY d2 = BinderC0773Mq0.d(parcel.readStrongBinder());
                int readInt = parcel.readInt();
                PS0 ps03 = (PS0) BinderC0773Mq0.f(d2);
                if (this.b.isInstance(ps03) && (ts03 = this.f9011a) != null) {
                    ts03.a((PS0) this.b.cast(ps03), readInt);
                }
                parcel2.writeNoException();
                return true;
            case 5:
                PS0 ps04 = (PS0) BinderC0773Mq0.f(BinderC0773Mq0.d(parcel.readStrongBinder()));
                if (this.b.isInstance(ps04) && (ts04 = this.f9011a) != null) {
                    ts04.c((PS0) this.b.cast(ps04));
                }
                parcel2.writeNoException();
                return true;
            case 6:
                VY d3 = BinderC0773Mq0.d(parcel.readStrongBinder());
                int readInt2 = parcel.readInt();
                PS0 ps05 = (PS0) BinderC0773Mq0.f(d3);
                if (this.b.isInstance(ps05) && (ts05 = this.f9011a) != null) {
                    ts05.b((PS0) this.b.cast(ps05), readInt2);
                }
                parcel2.writeNoException();
                return true;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                VY d4 = BinderC0773Mq0.d(parcel.readStrongBinder());
                String readString2 = parcel.readString();
                PS0 ps06 = (PS0) BinderC0773Mq0.f(d4);
                if (this.b.isInstance(ps06) && (ts06 = this.f9011a) != null) {
                    ts06.k((PS0) this.b.cast(ps06), readString2);
                }
                parcel2.writeNoException();
                return true;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                VY d5 = BinderC0773Mq0.d(parcel.readStrongBinder());
                int i3 = AbstractC4376qF1.f11128a;
                if (parcel.readInt() != 0) {
                    z = true;
                }
                PS0 ps07 = (PS0) BinderC0773Mq0.f(d5);
                if (this.b.isInstance(ps07) && (ts07 = this.f9011a) != null) {
                    ts07.g((PS0) this.b.cast(ps07), z);
                }
                parcel2.writeNoException();
                return true;
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                VY d6 = BinderC0773Mq0.d(parcel.readStrongBinder());
                int readInt3 = parcel.readInt();
                PS0 ps08 = (PS0) BinderC0773Mq0.f(d6);
                if (this.b.isInstance(ps08) && (ts08 = this.f9011a) != null) {
                    ts08.e((PS0) this.b.cast(ps08), readInt3);
                }
                parcel2.writeNoException();
                return true;
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                VY d7 = BinderC0773Mq0.d(parcel.readStrongBinder());
                int readInt4 = parcel.readInt();
                PS0 ps09 = (PS0) BinderC0773Mq0.f(d7);
                if (this.b.isInstance(ps09) && (ts09 = this.f9011a) != null) {
                    ts09.l((PS0) this.b.cast(ps09), readInt4);
                }
                parcel2.writeNoException();
                return true;
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                parcel2.writeNoException();
                parcel2.writeInt(12451009);
                return true;
            default:
                return false;
        }
    }
}
