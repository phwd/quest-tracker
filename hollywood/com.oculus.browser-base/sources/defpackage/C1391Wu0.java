package defpackage;

import org.chromium.components.page_info.PageInfoController;

/* renamed from: Wu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1391Wu0 implements AbstractC3467ky {

    /* renamed from: a  reason: collision with root package name */
    public final PageInfoController f9180a;

    public C1391Wu0(PageInfoController pageInfoController) {
        this.f9180a = pageInfoController;
    }

    @Override // defpackage.AbstractC3467ky
    public void a(Object obj) {
        PageInfoController pageInfoController = this.f9180a;
        pageInfoController.S = (Runnable) obj;
        pageInfoController.M.b(true);
    }
}
