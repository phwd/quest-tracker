package defpackage;

import java.util.List;

/* renamed from: cM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2001cM extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final List f9602a;
    public final C3811my1 b;
    public final C5232vH0 c;

    public C2001cM(List list, C3811my1 my1, C5232vH0 vh0) {
        this.f9602a = list;
        this.b = my1;
        this.c = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        List list = this.f9602a;
        C3811my1 my1 = this.b;
        C5232vH0 vh0 = this.c;
        if (((Boolean) obj).booleanValue()) {
            list.add(my1);
            vh0.b(null);
            return;
        }
        vh0.e(null);
    }
}
