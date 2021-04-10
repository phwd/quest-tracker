package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: ia1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3056ia1 extends AbstractC2575fl0 {
    public final Context H;
    public ViewGroup I;

    /* renamed from: J  reason: collision with root package name */
    public ModalDialogView f10146J;
    public ZH0 K;
    public boolean L;

    public AbstractC3056ia1(Context context) {
        this.H = context;
    }

    @Override // defpackage.AbstractC2575fl0
    public void b(UH0 uh0) {
        if (this.I == null) {
            C3965nt ntVar = (C3965nt) this;
            ViewStub viewStub = (ViewStub) ntVar.M.findViewById(R.id.tab_modal_dialog_container_stub);
            viewStub.setLayoutResource(R.layout.f39290_resource_name_obfuscated_RES_2131624238);
            ViewGroup viewGroup = (ViewGroup) viewStub.inflate();
            viewGroup.setVisibility(8);
            viewGroup.setClickable(true);
            ntVar.S = (ViewGroup) viewGroup.getParent();
            ntVar.V = ntVar.M.findViewById(R.id.tab_modal_dialog_container_sibling_view);
            Resources resources = ntVar.M.getResources();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
            marginLayoutParams.width = -1;
            marginLayoutParams.height = -1;
            marginLayoutParams.topMargin = C3965nt.m(resources, ntVar.P);
            marginLayoutParams.bottomMargin = ((C1551Zj) ntVar.P).O;
            viewGroup.setLayoutParams(marginLayoutParams);
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f25670_resource_name_obfuscated_RES_2131166186);
            View findViewById = viewGroup.findViewById(R.id.scrim);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
            marginLayoutParams2.width = -1;
            marginLayoutParams2.height = -1;
            marginLayoutParams2.topMargin = dimensionPixelSize;
            findViewById.setLayoutParams(marginLayoutParams2);
            this.I = viewGroup;
        }
        ModalDialogView modalDialogView = (ModalDialogView) AbstractC2471f70.a(new ContextThemeWrapper(this.H, uh0.h(AbstractC3258jl0.q) ? R.style.f72760_resource_name_obfuscated_RES_2132017849 : R.style.f72770_resource_name_obfuscated_RES_2132017850), R.layout.f39310_resource_name_obfuscated_RES_2131624240, null);
        this.f10146J = modalDialogView;
        this.K = ZH0.a(uh0, modalDialogView, new C2885ha1(this, null));
        i(true);
        C3965nt ntVar2 = (C3965nt) this;
        if (ntVar2.X) {
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) ntVar2.I.getLayoutParams();
            marginLayoutParams3.topMargin = C3965nt.m(ntVar2.M.getResources(), ntVar2.P);
            marginLayoutParams3.bottomMargin = ntVar2.W;
            ntVar2.I.setLayoutParams(marginLayoutParams3);
            ntVar2.X = false;
        }
        if (AbstractC2571fk.a(ntVar2.P)) {
            ntVar2.f();
        } else {
            ntVar2.U = true;
        }
        ntVar2.Y = ((C1343Wa1) ntVar2.N.get()).a();
    }

    public void f() {
        this.I.animate().cancel();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 17);
        this.f10146J.setBackgroundResource(R.drawable.f34630_resource_name_obfuscated_RES_2131231503);
        this.I.addView(this.f10146J, layoutParams);
        this.I.setAlpha(0.0f);
        this.I.setVisibility(0);
        this.I.animate().setDuration(200).alpha(1.0f).setInterpolator(animation.InterpolatorC5286vf.g).setListener(new C2372ea1(this)).start();
    }

    public void g(WebContents webContents, boolean z) {
        if (z) {
            SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(webContents);
            r.j0 = true;
            webContents.F().getContainerView().clearFocus();
            r.I(false);
            this.L = true;
        } else if (this.L) {
            this.L = false;
            SelectionPopupControllerImpl.r(webContents).I(true);
        }
    }

    public abstract void i(boolean z);

    public abstract void l(boolean z);
}
