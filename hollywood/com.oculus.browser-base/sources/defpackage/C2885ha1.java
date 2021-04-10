package defpackage;

import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* renamed from: ha1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2885ha1 extends AbstractC3942nl0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC3056ia1 f10082a;

    public C2885ha1(AbstractC3056ia1 ia1, C2372ea1 ea1) {
        this.f10082a = ia1;
    }

    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        ModalDialogView modalDialogView = (ModalDialogView) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = AbstractC3258jl0.m;
        if (qh0 != kh0) {
            super.b(uh0, modalDialogView, kh0);
        } else if (uh0.h(qh0)) {
            this.f10082a.I.setOnClickListener(new View$OnClickListenerC2714ga1(this));
        } else {
            this.f10082a.I.setOnClickListener(null);
        }
    }
}
