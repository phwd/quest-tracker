package X;

import android.view.View;
import androidx.appcompat.widget.Toolbar;

/* renamed from: X.05a  reason: invalid class name and case insensitive filesystem */
public class View$OnClickListenerC004405a implements View.OnClickListener {
    public final /* synthetic */ Toolbar A00;

    public View$OnClickListenerC004405a(Toolbar toolbar) {
        this.A00 = toolbar;
    }

    public final void onClick(View view) {
        C04250eW r0;
        C04050dt r02 = this.A00.A0B;
        if (r02 != null && (r0 = r02.A01) != null) {
            r0.collapseActionView();
        }
    }
}
