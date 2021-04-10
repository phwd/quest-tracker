package defpackage;

import android.os.Parcel;
import java.util.Objects;

/* renamed from: YI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class YI1 implements CL0 {

    /* renamed from: a  reason: collision with root package name */
    public final GI1 f9267a;

    public YI1(GI1 gi1) {
        this.f9267a = gi1;
    }

    @Override // defpackage.CL0
    public final void a(Object obj, Object obj2) {
        BinderC3191jJ1 jj1 = new BinderC3191jJ1((C0563Je1) obj2);
        C4211pH1 ph1 = (C4211pH1) ((C2337eJ1) obj).l();
        Objects.requireNonNull(ph1);
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(ph1.b);
        int i = DF1.f7875a;
        obtain.writeStrongBinder(jj1);
        ph1.c(6, obtain);
    }
}
