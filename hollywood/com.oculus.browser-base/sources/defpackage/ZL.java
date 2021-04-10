package defpackage;

import java.util.Iterator;
import java.util.List;

/* renamed from: ZL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ZL extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final List f9339a;
    public final List b;
    public final C5232vH0 c;

    public ZL(List list, List list2, C5232vH0 vh0) {
        this.f9339a = list;
        this.b = list2;
        this.c = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        List list = this.f9339a;
        List list2 = this.b;
        C5232vH0 vh0 = this.c;
        if (((Boolean) obj).booleanValue()) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                if (list.contains(((C3811my1) it.next()).b)) {
                    it.remove();
                }
            }
            vh0.b(null);
            return;
        }
        vh0.e(null);
    }
}
