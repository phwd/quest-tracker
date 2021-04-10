package defpackage;

import J.N;
import org.chromium.components.page_info.PageInfoController;

/* renamed from: dv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2263dv0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3117iv0 f9817a;

    public C2263dv0(C3117iv0 iv0) {
        this.f9817a = iv0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3117iv0 iv0 = this.f9817a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        ((PageInfoController) iv0.F).k(booleanValue ? 12 : 11);
        long j = iv0.H.f10833a;
        if (j != 0) {
            N.MTF7msU_(j, booleanValue);
        }
    }
}
