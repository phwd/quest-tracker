package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: wA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5380wA extends AbstractView$OnLayoutChangeListenerC1450Xt0 {
    public TextView Z;

    public C5380wA(AbstractC0292Et0 et0, Context context, ViewGroup viewGroup, IJ ij) {
        super(et0, R.layout.f37550_resource_name_obfuscated_RES_2131624064, R.id.contextual_search_term_view, context, viewGroup, ij, R.dimen.f17780_resource_name_obfuscated_RES_2131165397, R.dimen.f17790_resource_name_obfuscated_RES_2131165398);
    }

    @Override // defpackage.AbstractC1389Wt0, defpackage.AbstractView$OnLayoutChangeListenerC1450Xt0, defpackage.AbstractC3631lv1
    public void j() {
        super.j();
        this.Z = (TextView) this.L.findViewById(R.id.contextual_search_term);
    }

    @Override // defpackage.AbstractView$OnLayoutChangeListenerC1450Xt0
    public TextView o() {
        return this.Z;
    }
}
