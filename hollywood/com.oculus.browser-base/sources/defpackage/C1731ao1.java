package defpackage;

import android.os.Bundle;

/* renamed from: ao1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1731ao1 extends AbstractC2073co1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f9490a;
    public final /* synthetic */ Tn1 b;
    public final /* synthetic */ C2414eo1 c;

    public C1731ao1(C2414eo1 eo1, boolean z, Tn1 tn1) {
        this.c = eo1;
        this.f9490a = z;
        this.b = tn1;
    }

    @Override // defpackage.AbstractC2073co1
    public void a(C4649rt0 rt0, C3268jo1 jo1) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("enableHighAccuracy", this.f9490a);
        Bundle a2 = jo1.a("startLocation", bundle, this.b);
        if (a2 == null || !a2.getBoolean("success")) {
            C2414eo1.a(this.c, this.b, "Failed to request location updates");
        }
    }

    @Override // defpackage.AbstractC2073co1
    public void b() {
        C2414eo1.a(this.c, this.b, "NoTwaFound");
    }
}
