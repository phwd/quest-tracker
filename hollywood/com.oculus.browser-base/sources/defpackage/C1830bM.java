package defpackage;

import java.util.List;

/* renamed from: bM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1830bM extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final List f9536a;
    public final C5232vH0 b;

    public C1830bM(List list, C5232vH0 vh0) {
        this.f9536a = list;
        this.b = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        List list = this.f9536a;
        C5232vH0 vh0 = this.b;
        if (((Boolean) obj).booleanValue()) {
            list.clear();
            vh0.b(null);
            return;
        }
        vh0.e(null);
    }
}
