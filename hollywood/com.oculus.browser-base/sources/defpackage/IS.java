package defpackage;

import java.util.ArrayList;

/* renamed from: IS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IS implements HS {

    /* renamed from: a  reason: collision with root package name */
    public final int f8226a;
    public final int b;
    public final /* synthetic */ KS c;

    public IS(KS ks, String str, int i, int i2) {
        this.c = ks;
        this.f8226a = i;
        this.b = i2;
    }

    @Override // defpackage.HS
    public boolean a(ArrayList arrayList, ArrayList arrayList2) {
        AbstractComponentCallbacksC3550lS lSVar = this.c.q;
        if (lSVar == null || this.f8226a >= 0 || !lSVar.w().c0()) {
            return this.c.e0(arrayList, arrayList2, null, this.f8226a, this.b);
        }
        return false;
    }
}
