package androidx.lifecycle;

import X.AbstractC05230uw;
import X.AnonymousClass0AO;
import X.AnonymousClass0AP;
import X.AnonymousClass0AQ;
import X.AnonymousClass0AS;
import X.AnonymousClass0Af;
import X.AnonymousClass0Ag;
import X.AnonymousClass0C4;
import androidx.annotation.NonNull;
import java.util.Map;

public final class SavedStateHandleController implements AbstractC05230uw {
    public boolean A00 = false;
    public final AnonymousClass0Af A01;
    public final String A02;

    public static final void A00(SavedStateHandleController savedStateHandleController, AnonymousClass0C4 r4, AnonymousClass0AQ r5) {
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

    public static void A01(AnonymousClass0Ag r2, AnonymousClass0C4 r3, AnonymousClass0AQ r4) {
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

    @Override // X.AbstractC05230uw
    public final void A87(@NonNull AnonymousClass0AS r2, @NonNull AnonymousClass0AO r3) {
        if (r3 == AnonymousClass0AO.ON_DESTROY) {
            this.A00 = false;
            r2.getLifecycle().A07(this);
        }
    }

    public SavedStateHandleController(String str, AnonymousClass0Af r3) {
        this.A02 = str;
        this.A01 = r3;
    }

    public static void A02(final AnonymousClass0C4 r2, final AnonymousClass0AQ r3) {
        AnonymousClass0AP A05 = r3.A05();
        if (A05 == AnonymousClass0AP.INITIALIZED || A05.isAtLeast(AnonymousClass0AP.STARTED)) {
            r2.A01();
        } else {
            r3.A06(new AbstractC05230uw() {
                /* class androidx.lifecycle.SavedStateHandleController.AnonymousClass1 */

                @Override // X.AbstractC05230uw
                public final void A87(@NonNull AnonymousClass0AS r2, @NonNull AnonymousClass0AO r3) {
                    if (r3 == AnonymousClass0AO.ON_START) {
                        r3.A07(this);
                        r2.A01();
                    }
                }
            });
        }
    }
}
