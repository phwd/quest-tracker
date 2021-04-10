package X;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.Iq  reason: case insensitive filesystem */
public class C0104Iq extends AbstractC0286a9<FragmentActivity> implements AbstractC0047Ak, AbstractC0305ad {
    public final /* synthetic */ FragmentActivity A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0104Iq(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.A00 = fragmentActivity;
    }

    @Override // X.AnonymousClass9O, X.AbstractC0286a9
    @Nullable
    public final View A00(int i) {
        return this.A00.findViewById(i);
    }

    @Override // X.AnonymousClass9O, X.AbstractC0286a9
    public final boolean A01() {
        Window window = this.A00.getWindow();
        if (window == null || window.peekDecorView() == null) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC0286a9
    @NonNull
    public final LayoutInflater A02() {
        FragmentActivity fragmentActivity = this.A00;
        return fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
    }

    @Override // X.AbstractC0286a9
    public final void A04() {
        this.A00.invalidateOptionsMenu();
    }

    @Override // X.AR
    @NonNull
    public final AP getLifecycle() {
        return this.A00.mFragmentLifecycleRegistry;
    }

    @Override // X.AbstractC0305ad
    @NonNull
    public final AnonymousClass1Q getOnBackPressedDispatcher() {
        return this.A00.getOnBackPressedDispatcher();
    }

    @Override // X.AbstractC0047Ak
    @NonNull
    public final C0046Aj getViewModelStore() {
        return this.A00.getViewModelStore();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0286a9
    public final FragmentActivity A03() {
        return this.A00;
    }
}
