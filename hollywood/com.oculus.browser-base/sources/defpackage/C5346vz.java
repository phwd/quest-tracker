package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: vz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5346vz extends AbstractC1389Wt0 {
    public TextView Y;
    public TextView Z;

    public C5346vz(AbstractC0292Et0 et0, Context context, ViewGroup viewGroup, IJ ij) {
        super(et0, R.layout.f37520_resource_name_obfuscated_RES_2131624061, R.id.contextual_search_context_view, context, viewGroup, ij, R.dimen.f17780_resource_name_obfuscated_RES_2131165397, R.dimen.f17790_resource_name_obfuscated_RES_2131165398);
    }

    @Override // defpackage.AbstractC1389Wt0, defpackage.AbstractC3631lv1
    public void j() {
        super.j();
        View view = this.L;
        this.Y = (TextView) view.findViewById(R.id.selected_text);
        this.Z = (TextView) view.findViewById(R.id.surrounding_text_end);
    }

    public void o(String str, String str2) {
        e();
        this.Y.setText(AbstractC1145St0.l(str));
        this.Z.setText(AbstractC1145St0.l(str2));
        m(true);
    }
}
