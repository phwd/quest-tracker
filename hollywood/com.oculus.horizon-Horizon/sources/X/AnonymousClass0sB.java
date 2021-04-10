package X;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/* renamed from: X.0sB  reason: invalid class name */
public class AnonymousClass0sB extends AnonymousClass09N {
    public final /* synthetic */ Fragment A00;

    public AnonymousClass0sB(Fragment fragment) {
        this.A00 = fragment;
    }

    @Override // X.AnonymousClass09N
    @Nullable
    public final View A00(int i) {
        View view = this.A00.mView;
        if (view != null) {
            return view.findViewById(i);
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" does not have a view");
        throw new IllegalStateException(sb.toString());
    }

    @Override // X.AnonymousClass09N
    public final boolean A01() {
        if (this.A00.mView != null) {
            return true;
        }
        return false;
    }
}
