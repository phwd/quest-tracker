package defpackage;

import java.util.Map;

/* renamed from: lj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3595lj1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Map f10367a;
    public final String b;
    public final C5232vH0 c;

    public C3595lj1(Map map, String str, C5232vH0 vh0) {
        this.f10367a = map;
        this.b = str;
        this.c = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Map map = this.f10367a;
        String str = this.b;
        C5232vH0 vh0 = this.c;
        if (((Boolean) obj).booleanValue()) {
            map.remove(str);
            vh0.b(null);
            return;
        }
        vh0.e(null);
    }
}
