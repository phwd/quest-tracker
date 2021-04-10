package X;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.0MK  reason: invalid class name */
public class AnonymousClass0MK extends AbstractC03650cs<FragmentActivity> implements AbstractC01160Dt, AnonymousClass0f4 {
    public final /* synthetic */ FragmentActivity A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0MK(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.A00 = fragmentActivity;
    }

    @Override // X.AnonymousClass0CX, X.AbstractC03650cs
    @Nullable
    public final View A00(int i) {
        return this.A00.findViewById(i);
    }

    @Override // X.AnonymousClass0CX, X.AbstractC03650cs
    public final boolean A01() {
        Window window = this.A00.getWindow();
        if (window == null || window.peekDecorView() == null) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC03650cs
    @NonNull
    public final LayoutInflater A02() {
        FragmentActivity fragmentActivity = this.A00;
        return fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC03650cs
    public final FragmentActivity A03() {
        return this.A00;
    }

    @Override // X.AbstractC03650cs
    public final void A04() {
        this.A00.supportInvalidateOptionsMenu();
    }

    @Override // X.AbstractC01030Da
    @NonNull
    public final AnonymousClass0DY getLifecycle() {
        return this.A00.mFragmentLifecycleRegistry;
    }

    @Override // X.AnonymousClass0f4
    @NonNull
    public final AnonymousClass01R getOnBackPressedDispatcher() {
        return this.A00.getOnBackPressedDispatcher();
    }

    @Override // X.AbstractC01160Dt
    @NonNull
    public final C01150Ds getViewModelStore() {
        return this.A00.getViewModelStore();
    }
}
