package defpackage;

import java.util.List;

/* renamed from: aM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1650aM extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final long f9426a;
    public final long b;
    public final List c;
    public final C5232vH0 d;

    public C1650aM(long j, long j2, List list, C5232vH0 vh0) {
        this.f9426a = j;
        this.b = j2;
        this.c = list;
        this.d = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        long j = this.f9426a;
        long j2 = this.b;
        List list = this.c;
        C5232vH0 vh0 = this.d;
        if (((Boolean) obj).booleanValue()) {
            list.subList(C3538lM.b(j, list), C3538lM.b(j2, list)).clear();
            vh0.b(null);
            return;
        }
        vh0.e(null);
    }
}
