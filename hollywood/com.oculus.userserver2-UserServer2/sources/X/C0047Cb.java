package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.Cb  reason: case insensitive filesystem */
public class C0047Cb extends AbstractC0144Tp<FragmentActivity> implements CC, UI {
    public final /* synthetic */ FragmentActivity A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0047Cb(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.A00 = fragmentActivity;
    }

    @Override // X.Bs
    @NonNull
    public final AbstractC0041Bq getLifecycle() {
        return this.A00.mFragmentLifecycleRegistry;
    }

    @Override // X.UI
    @NonNull
    public final AnonymousClass1R getOnBackPressedDispatcher() {
        return this.A00.getOnBackPressedDispatcher();
    }

    @Override // X.CC
    @NonNull
    public final CB getViewModelStore() {
        return this.A00.getViewModelStore();
    }
}
