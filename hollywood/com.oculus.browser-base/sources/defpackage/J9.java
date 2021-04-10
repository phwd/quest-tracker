package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* renamed from: J9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class J9 extends AbstractC2575fl0 {
    public final Context H;
    public Dialog I;

    /* renamed from: J  reason: collision with root package name */
    public ZH0 f8273J;

    public J9(Context context) {
        this.H = context;
    }

    @Override // defpackage.AbstractC2575fl0
    public void b(UH0 uh0) {
        Dialog dialog = new Dialog(this.H, uh0.h(AbstractC3258jl0.q) ? R.style.f72760_resource_name_obfuscated_RES_2132017849 : R.style.f72770_resource_name_obfuscated_RES_2132017850);
        this.I = dialog;
        dialog.setOnCancelListener(new G9(this));
        this.I.setCanceledOnTouchOutside(false);
        ModalDialogView modalDialogView = (ModalDialogView) AbstractC2471f70.a(this.I.getContext(), R.layout.f39310_resource_name_obfuscated_RES_2131624240, null);
        this.f8273J = ZH0.a(uh0, modalDialogView, new I9(this, null));
        P21 f0 = P21.f0();
        try {
            this.I.setContentView(modalDialogView);
            f0.close();
            try {
                this.I.show();
                modalDialogView.announceForAccessibility(AbstractC2575fl0.d(uh0));
                return;
            } catch (WindowManager.BadTokenException unused) {
                c(9);
                return;
            }
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractC2575fl0
    public void e(UH0 uh0) {
        ZH0 zh0 = this.f8273J;
        if (zh0 != null) {
            zh0.b();
            this.f8273J = null;
        }
        Dialog dialog = this.I;
        if (dialog != null) {
            dialog.dismiss();
            this.I = null;
        }
    }
}
