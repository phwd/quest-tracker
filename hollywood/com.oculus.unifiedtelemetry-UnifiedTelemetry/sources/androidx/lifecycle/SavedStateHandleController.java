package androidx.lifecycle;

import X.AN;
import X.AO;
import X.AP;
import X.AR;
import X.Ae;
import X.Af;
import X.Bz;
import X.Zx;
import androidx.annotation.NonNull;
import java.util.Map;

public final class SavedStateHandleController implements Zx {
    public boolean A00 = false;
    public final Ae A01;
    public final String A02;

    public static final void A00(SavedStateHandleController savedStateHandleController, Bz bz, AP ap) {
        if (!savedStateHandleController.A00) {
            savedStateHandleController.A00 = true;
            ap.A06(savedStateHandleController);
            if (bz.A02.A02(savedStateHandleController.A02, savedStateHandleController.A01.A01) != null) {
                throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
            }
            return;
        }
        throw new IllegalStateException("Already attached to lifecycleOwner");
    }

    public static void A01(Af af, Bz bz, AP ap) {
        Object obj;
        Map<String, Object> map = af.A00;
        if (map == null) {
            obj = null;
        } else {
            synchronized (map) {
                obj = map.get("androidx.lifecycle.savedstate.vm.tag");
            }
        }
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) obj;
        if (savedStateHandleController != null && !savedStateHandleController.A00) {
            A00(savedStateHandleController, bz, ap);
            A02(bz, ap);
        }
    }

    @Override // X.Zx
    public final void A42(@NonNull AR ar, @NonNull AN an) {
        if (an == AN.ON_DESTROY) {
            this.A00 = false;
            ar.getLifecycle().A07(this);
        }
    }

    public SavedStateHandleController(String str, Ae ae) {
        this.A02 = str;
        this.A01 = ae;
    }

    public static void A02(final Bz bz, final AP ap) {
        AO A05 = ap.A05();
        if (A05 == AO.INITIALIZED || A05.isAtLeast(AO.STARTED)) {
            bz.A01();
        } else {
            ap.A06(new Zx() {
                /* class androidx.lifecycle.SavedStateHandleController.AnonymousClass1 */

                @Override // X.Zx
                public final void A42(@NonNull AR ar, @NonNull AN an) {
                    if (an == AN.ON_START) {
                        ap.A07(this);
                        bz.A01();
                    }
                }
            });
        }
    }
}
