package defpackage;

import org.chromium.components.page_info.PageInfoController;

/* renamed from: Vu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1330Vu0 implements AbstractC3467ky {

    /* renamed from: a  reason: collision with root package name */
    public final PageInfoController f9115a;

    public C1330Vu0(PageInfoController pageInfoController) {
        this.f9115a = pageInfoController;
    }

    @Override // defpackage.AbstractC3467ky
    public void a(Object obj) {
        PageInfoController pageInfoController = this.f9115a;
        pageInfoController.S = (Runnable) obj;
        pageInfoController.M.b(true);
    }
}
