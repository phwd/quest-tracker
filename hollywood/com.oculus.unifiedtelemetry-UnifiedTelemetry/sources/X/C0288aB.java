package X;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/* renamed from: X.aB  reason: case insensitive filesystem */
public class C0288aB extends AnonymousClass9O {
    public final /* synthetic */ Fragment A00;

    public C0288aB(Fragment fragment) {
        this.A00 = fragment;
    }

    @Override // X.AnonymousClass9O
    @Nullable
    public final View A00(int i) {
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" does not have a view");
        throw new IllegalStateException(sb.toString());
    }

    @Override // X.AnonymousClass9O
    public final boolean A01() {
        return false;
    }
}
