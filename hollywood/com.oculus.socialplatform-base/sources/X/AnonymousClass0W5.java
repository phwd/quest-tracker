package X;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.0W5  reason: invalid class name */
public class AnonymousClass0W5 extends AbstractC05340vL<FragmentActivity> implements AbstractC00480Al, AnonymousClass0wo {
    public final /* synthetic */ FragmentActivity A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0W5(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.A00 = fragmentActivity;
    }

    @Override // X.AnonymousClass09P, X.AbstractC05340vL
    @Nullable
    public final View A00(int i) {
        return this.A00.findViewById(i);
    }

    @Override // X.AnonymousClass09P, X.AbstractC05340vL
    public final boolean A01() {
        Window window = this.A00.getWindow();
        if (window == null || window.peekDecorView() == null) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC05340vL
    @NonNull
    public final LayoutInflater A02() {
        FragmentActivity fragmentActivity = this.A00;
        return fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
    }

    @Override // X.AbstractC05340vL
    public final void A04() {
        this.A00.supportInvalidateOptionsMenu();
    }

    @Override // X.AnonymousClass0AS
    @NonNull
    public final AnonymousClass0AQ getLifecycle() {
        return this.A00.mFragmentLifecycleRegistry;
    }

    @Override // X.AnonymousClass0wo
    @NonNull
    public final AnonymousClass01Q getOnBackPressedDispatcher() {
        return this.A00.getOnBackPressedDispatcher();
    }

    @Override // X.AbstractC00480Al
    @NonNull
    public final C00470Ak getViewModelStore() {
        return this.A00.getViewModelStore();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC05340vL
    public final FragmentActivity A03() {
        return this.A00;
    }
}
