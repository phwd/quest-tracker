package X;

import android.view.View;
import androidx.appcompat.widget.Toolbar;

/* renamed from: X.1tL  reason: invalid class name */
public class AnonymousClass1tL implements View.OnClickListener {
    public final /* synthetic */ Toolbar A00;

    public AnonymousClass1tL(Toolbar toolbar) {
        this.A00 = toolbar;
    }

    public final void onClick(View view) {
        C11601sP r0;
        AnonymousClass1sS r02 = this.A00.A0B;
        if (r02 != null && (r0 = r02.A01) != null) {
            r0.collapseActionView();
        }
    }
}
