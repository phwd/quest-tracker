package defpackage;

import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* renamed from: I9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I9 extends AbstractC3942nl0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ J9 f8202a;

    public I9(J9 j9, H9 h9) {
        this.f8202a = j9;
    }

    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        ModalDialogView modalDialogView = (ModalDialogView) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = AbstractC3258jl0.m;
        if (qh0 == kh0) {
            this.f8202a.I.setCanceledOnTouchOutside(uh0.h(qh0));
        } else {
            super.b(uh0, modalDialogView, kh0);
        }
    }
}
