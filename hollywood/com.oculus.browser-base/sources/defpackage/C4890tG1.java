package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: tG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4890tG1 implements AbstractC4040oH1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f11333a;
    public final /* synthetic */ Bundle b;

    public C4890tG1(String str, Bundle bundle) {
        this.f11333a = str;
        this.b = bundle;
    }

    @Override // defpackage.AbstractC4040oH1
    public final Object a(IBinder iBinder) {
        AbstractC2328eG1 c = AbstractBinderC4379qG1.c(iBinder);
        String str = this.f11333a;
        Bundle bundle = this.b;
        FG1 fg1 = (FG1) c;
        Parcel c2 = fg1.c();
        c2.writeString(str);
        AbstractC1984cF1.b(c2, bundle);
        Parcel d = fg1.d(2, c2);
        Bundle bundle2 = (Bundle) AbstractC1984cF1.a(d, Bundle.CREATOR);
        d.recycle();
        EF1.d(bundle2);
        Bundle bundle3 = bundle2;
        String string = bundle3.getString("Error");
        if (bundle3.getBoolean("booleanResult")) {
            return null;
        }
        throw new C2192dW(string);
    }
}
