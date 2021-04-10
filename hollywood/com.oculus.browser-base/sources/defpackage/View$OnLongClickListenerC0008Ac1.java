package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* renamed from: Ac1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnLongClickListenerC0008Ac1 implements View.OnLongClickListener {
    public final C0252Ec1 F;
    public final Callback G;

    public View$OnLongClickListenerC0008Ac1(C0252Ec1 ec1, Callback callback) {
        this.F = ec1;
        this.G = callback;
    }

    public boolean onLongClick(View view) {
        C0252Ec1 ec1 = this.F;
        Callback callback = this.G;
        Context context = view.getContext();
        ListMenuButton listMenuButton = (ListMenuButton) view;
        Objects.requireNonNull(ec1);
        C4935tb0 tb0 = new C4935tb0();
        tb0.q(ec1.a(1));
        tb0.q(ec1.a(0));
        tb0.q(ec1.a(2));
        tb0.q(ec1.a(3));
        C0130Cc1 cc1 = new C0130Cc1(callback);
        ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 fv1 = new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(listMenuButton);
        fv1.L = true;
        fv1.e(0, 0, 0, (listMenuButton.getHeight() - listMenuButton.getResources().getDimensionPixelSize(R.dimen.f26340_resource_name_obfuscated_RES_2131166253)) / 2);
        C1237Ug ug = new C1237Ug(context, tb0, new C0069Bc1(cc1));
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.f25750_resource_name_obfuscated_RES_2131166194);
        ListView listView = ug.F;
        listView.setPaddingRelative(listView.getPaddingStart(), dimensionPixelOffset, listView.getPaddingEnd(), dimensionPixelOffset);
        C0191Dc1 dc1 = new C0191Dc1(ec1, ug, fv1);
        listMenuButton.d();
        listMenuButton.M = dc1;
        listMenuButton.e();
        return true;
    }
}
