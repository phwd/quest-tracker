package defpackage;

import java.util.Iterator;

/* renamed from: ld0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3576ld0 implements AbstractC1021Qs0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3747md0 f10358a;

    public C3576ld0(C3747md0 md0) {
        this.f10358a = md0;
    }

    @Override // defpackage.AbstractC1021Qs0
    public void a(Object obj) {
        Iterator it = this.f10358a.y0.iterator();
        while (it.hasNext()) {
            ((AbstractC1021Qs0) it.next()).a(obj);
        }
    }
}
