package defpackage;

import android.view.View;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Py1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Py1 extends AbstractC2264dv1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Ty1 f8726a;

    public Py1(Ty1 ty1) {
        this.f8726a = ty1;
    }

    @Override // defpackage.AbstractC2094cv1
    public void b(View view) {
        View view2;
        Ty1 ty1 = this.f8726a;
        if (ty1.r && (view2 = ty1.i) != null) {
            view2.setTranslationY(0.0f);
            this.f8726a.f.setTranslationY(0.0f);
        }
        this.f8726a.f.setVisibility(8);
        ActionBarContainer actionBarContainer = this.f8726a.f;
        actionBarContainer.F = false;
        actionBarContainer.setDescendantFocusability(262144);
        Ty1 ty12 = this.f8726a;
        ty12.w = null;
        AbstractC5526x2 x2Var = ty12.m;
        if (x2Var != null) {
            x2Var.d(ty12.l);
            ty12.l = null;
            ty12.m = null;
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f8726a.e;
        if (actionBarOverlayLayout != null) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            actionBarOverlayLayout.requestApplyInsets();
        }
    }
}
