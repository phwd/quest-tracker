package defpackage;

import android.os.Parcel;
import java.util.Objects;

/* renamed from: GC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class GC1 implements CL0 {

    /* renamed from: a  reason: collision with root package name */
    public final C5388wC1 f8074a;
    public final String b;

    public GC1(C5388wC1 wc1, String str) {
        this.f8074a = wc1;
        this.b = str;
    }

    @Override // defpackage.CL0
    public final void a(Object obj, Object obj2) {
        String str = this.b;
        C4211pH1 ph1 = (C4211pH1) ((C2337eJ1) obj).l();
        SC1 sc1 = new SC1((C0563Je1) obj2);
        Objects.requireNonNull(ph1);
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(ph1.b);
        obtain.writeString(str);
        int i = DF1.f7875a;
        obtain.writeStrongBinder(sc1);
        ph1.c(2, obtain);
    }
}
