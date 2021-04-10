package defpackage;

import android.app.PendingIntent;
import android.os.Bundle;

/* renamed from: kA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BinderC3335kA1 extends AbstractBinderC2310eA1 {
    public final String d;

    public BinderC3335kA1(Vz1 vz1, C2140dA1 da1, String str) {
        super(vz1, new Rz1("OnRequestInstallCallback"), da1);
        this.d = str;
    }

    @Override // defpackage.AbstractBinderC2310eA1, defpackage.AbstractC4019oA1
    public final void t0(Bundle bundle) {
        this.c.c.a();
        this.f9837a.a(4, "onRequestInfo", new Object[0]);
        if (bundle.getInt("error.code", -2) == 0 || bundle.getInt("error.code", -2) == 1) {
            this.b.b(C2823hA1.a(this.d, bundle.getInt("version.code", -1), bundle.getInt("update.availability"), bundle.getInt("install.status", 0), bundle.getInt("client.version.staleness", -1), (PendingIntent) bundle.getParcelable("blocking.intent"), (PendingIntent) bundle.getParcelable("nonblocking.intent")));
        } else {
            this.b.a(new Z10(bundle.getInt("error.code", -2)));
        }
    }
}
