package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/* renamed from: X.0s6  reason: invalid class name */
public class AnonymousClass0s6 extends AnonymousClass09Q {
    public final /* synthetic */ AbstractC003209a A00;

    public AnonymousClass0s6(AbstractC003209a r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass09Q
    @NonNull
    public final Fragment A01(@NonNull ClassLoader classLoader, @NonNull String str) {
        return Fragment.instantiate(this.A00.A05.A01, str, null);
    }
}
