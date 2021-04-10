package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import java.util.Objects;

/* renamed from: Pz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Pz1 implements N9 {

    /* renamed from: a  reason: collision with root package name */
    public final Vz1 f8728a;
    public final Lz1 b;
    public final Context c;

    public Pz1(Vz1 vz1, Context context) {
        this.f8728a = vz1;
        this.b = new Lz1(context);
        this.c = context;
    }

    @Override // defpackage.N9
    public final C3506lA1 a() {
        Vz1 vz1 = this.f8728a;
        String packageName = this.c.getPackageName();
        Objects.requireNonNull(vz1);
        Vz1.f9121a.a(4, "completeUpdate(%s)", new Object[]{packageName});
        C2140dA1 da1 = new C2140dA1();
        vz1.c.c(new Xz1(vz1, da1, da1, packageName));
        return da1.f9754a;
    }

    @Override // defpackage.N9
    public final synchronized void b(C5390wD0 wd0) {
        this.b.e(wd0);
    }

    @Override // defpackage.N9
    public final boolean c(C2823hA1 ha1, int i, Activity activity, int i2) {
        PendingIntent pendingIntent;
        if (!ha1.b(i)) {
            return false;
        }
        if (i == 0) {
            pendingIntent = ha1.g;
        } else {
            pendingIntent = i == 1 ? ha1.f : null;
        }
        activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i2, null, 0, 0, 0);
        return true;
    }

    @Override // defpackage.N9
    public final C3506lA1 d() {
        Vz1 vz1 = this.f8728a;
        String packageName = this.c.getPackageName();
        Objects.requireNonNull(vz1);
        Vz1.f9121a.a(4, "requestUpdateInfo(%s)", new Object[]{packageName});
        C2140dA1 da1 = new C2140dA1();
        vz1.c.c(new Zz1(vz1, da1, packageName, da1));
        return da1.f9754a;
    }

    @Override // defpackage.N9
    public final synchronized void e(C5390wD0 wd0) {
        this.b.b(wd0);
    }
}
