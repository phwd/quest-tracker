package X;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/* renamed from: X.0vN  reason: invalid class name and case insensitive filesystem */
public class C05350vN extends AnonymousClass09P {
    public final /* synthetic */ Fragment A00;

    public C05350vN(Fragment fragment) {
        this.A00 = fragment;
    }

    @Override // X.AnonymousClass09P
    @Nullable
    public final View A00(int i) {
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" does not have a view");
        throw new IllegalStateException(sb.toString());
    }

    @Override // X.AnonymousClass09P
    public final boolean A01() {
        return false;
    }
}
