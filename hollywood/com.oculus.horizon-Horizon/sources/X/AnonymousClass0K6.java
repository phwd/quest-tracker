package X;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.0K6  reason: invalid class name */
public class AnonymousClass0K6 extends AnonymousClass0s9<FragmentActivity> implements AbstractC00530Ak, AbstractC07560sz {
    public final /* synthetic */ FragmentActivity A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0K6(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.A00 = fragmentActivity;
    }

    @Override // X.AnonymousClass09N, X.AnonymousClass0s9
    @Nullable
    public final View A00(int i) {
        return this.A00.findViewById(i);
    }

    @Override // X.AnonymousClass09N, X.AnonymousClass0s9
    public final boolean A01() {
        Window window = this.A00.getWindow();
        if (window == null || window.peekDecorView() == null) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass0s9
    @NonNull
    public final LayoutInflater A02() {
        FragmentActivity fragmentActivity = this.A00;
        return fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
    }

    @Override // X.AnonymousClass0s9
    public final void A04() {
        this.A00.invalidateOptionsMenu();
    }

    @Override // X.AnonymousClass0s9
    public final void A05(@NonNull Fragment fragment, Intent intent, int i, @Nullable Bundle bundle) {
        this.A00.startActivityFromFragment(fragment, intent, i, bundle);
    }

    @Override // X.AnonymousClass0s9
    public final void A06(@NonNull Fragment fragment, IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        this.A00.startIntentSenderFromFragment(fragment, intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // X.AnonymousClass0s9
    public final void A07(@NonNull Fragment fragment, @NonNull String[] strArr, int i) {
        this.A00.requestPermissionsFromFragment(fragment, strArr, i);
    }

    @Override // X.AnonymousClass0s9
    public final boolean A08(@NonNull Fragment fragment) {
        return !this.A00.isFinishing();
    }

    @Override // X.AnonymousClass0s9
    public final boolean A09(@NonNull String str) {
        return C07460sp.A03(this.A00, str);
    }

    @Override // X.AnonymousClass0AR
    @NonNull
    public final AnonymousClass0AP getLifecycle() {
        return this.A00.mFragmentLifecycleRegistry;
    }

    @Override // X.AbstractC07560sz
    @NonNull
    public final AnonymousClass01Q getOnBackPressedDispatcher() {
        return this.A00.getOnBackPressedDispatcher();
    }

    @Override // X.AbstractC00530Ak
    @NonNull
    public final C00520Aj getViewModelStore() {
        return this.A00.getViewModelStore();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0s9
    public final FragmentActivity A03() {
        return this.A00;
    }
}
