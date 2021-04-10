package defpackage;

import org.chromium.base.Callback;

/* renamed from: Ei  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0268Ei extends OK {
    public final /* synthetic */ C0329Fi F;

    public C0268Ei(C0329Fi fi) {
        this.F = fi;
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void k(int i) {
        C0329Fi fi = this.F;
        Callback callback = fi.d;
        if (callback != null) {
            callback.onResult(null);
            fi.d = null;
        }
        ((C5638xj) this.F.f8034a).r(this);
    }
}
