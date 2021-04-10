package defpackage;

import java.util.HashSet;

/* renamed from: DS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DS {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ KS f7893a;

    public DS(KS ks) {
        this.f7893a = ks;
    }

    public void a(AbstractComponentCallbacksC3550lS lSVar, C3089im imVar) {
        boolean z;
        synchronized (imVar) {
            z = imVar.f10160a;
        }
        if (!z) {
            KS ks = this.f7893a;
            HashSet hashSet = (HashSet) ks.j.get(lSVar);
            if (hashSet != null && hashSet.remove(imVar) && hashSet.isEmpty()) {
                ks.j.remove(lSVar);
                if (lSVar.G < 3) {
                    ks.j(lSVar);
                    ks.a0(lSVar, lSVar.N());
                }
            }
        }
    }

    public void b(AbstractComponentCallbacksC3550lS lSVar, C3089im imVar) {
        KS ks = this.f7893a;
        if (ks.j.get(lSVar) == null) {
            ks.j.put(lSVar, new HashSet());
        }
        ((HashSet) ks.j.get(lSVar)).add(imVar);
    }
}
