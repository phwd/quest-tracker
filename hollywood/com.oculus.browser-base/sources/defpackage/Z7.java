package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Z7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Z7 extends AbstractC2264dv1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1610a8 f9320a;

    public Z7(C1610a8 a8Var) {
        this.f9320a = a8Var;
    }

    @Override // defpackage.AbstractC2094cv1
    public void b(View view) {
        this.f9320a.b.Y.setVisibility(8);
        LayoutInflater$Factory2C3156j8 j8Var = this.f9320a.b;
        PopupWindow popupWindow = j8Var.Z;
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else if (j8Var.Y.getParent() instanceof View) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            ((View) this.f9320a.b.Y.getParent()).requestApplyInsets();
        }
        this.f9320a.b.Y.removeAllViews();
        this.f9320a.b.b0.d(null);
        LayoutInflater$Factory2C3156j8 j8Var2 = this.f9320a.b;
        j8Var2.b0 = null;
        ViewGroup viewGroup = j8Var2.e0;
        AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
        viewGroup.requestApplyInsets();
    }
}
