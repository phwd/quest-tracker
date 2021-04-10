package androidx.lifecycle;

import X.AbstractC01030Da;
import X.AbstractC03550cd;
import X.AnonymousClass0DW;
import X.AnonymousClass0DX;
import X.AnonymousClass0DY;
import X.AnonymousClass0Do;
import X.AnonymousClass0GJ;
import X.C01110Dn;
import androidx.annotation.NonNull;
import java.util.Map;

public final class SavedStateHandleController implements AbstractC03550cd {
    public boolean A00 = false;
    public final C01110Dn A01;
    public final String A02;

    public static final void A00(SavedStateHandleController savedStateHandleController, AnonymousClass0GJ r4, AnonymousClass0DY r5) {
        if (!savedStateHandleController.A00) {
            savedStateHandleController.A00 = true;
            r5.A06(savedStateHandleController);
            if (r4.A02.A02(savedStateHandleController.A02, savedStateHandleController.A01.A01) != null) {
                throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
            }
            return;
        }
        throw new IllegalStateException("Already attached to lifecycleOwner");
    }

    public static void A01(AnonymousClass0Do r2, AnonymousClass0GJ r3, AnonymousClass0DY r4) {
        Object obj;
        Map<String, Object> map = r2.A00;
        if (map == null) {
            obj = null;
        } else {
            synchronized (map) {
                obj = map.get("androidx.lifecycle.savedstate.vm.tag");
            }
        }
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) obj;
        if (savedStateHandleController != null && !savedStateHandleController.A00) {
            A00(savedStateHandleController, r3, r4);
            A02(r3, r4);
        }
    }

    @Override // X.AbstractC03550cd
    public final void A6c(@NonNull AbstractC01030Da r2, @NonNull AnonymousClass0DW r3) {
        if (r3 == AnonymousClass0DW.ON_DESTROY) {
            this.A00 = false;
            r2.getLifecycle().A07(this);
        }
    }

    public SavedStateHandleController(String str, C01110Dn r3) {
        this.A02 = str;
        this.A01 = r3;
    }

    public static void A02(final AnonymousClass0GJ r2, final AnonymousClass0DY r3) {
        AnonymousClass0DX A05 = r3.A05();
        if (A05 == AnonymousClass0DX.INITIALIZED || A05.isAtLeast(AnonymousClass0DX.STARTED)) {
            r2.A01();
        } else {
            r3.A06(new AbstractC03550cd() {
                /* class androidx.lifecycle.SavedStateHandleController.AnonymousClass1 */

                @Override // X.AbstractC03550cd
                public final void A6c(@NonNull AbstractC01030Da r2, @NonNull AnonymousClass0DW r3) {
                    if (r3 == AnonymousClass0DW.ON_START) {
                        r3.A07(this);
                        r2.A01();
                    }
                }
            });
        }
    }
}
