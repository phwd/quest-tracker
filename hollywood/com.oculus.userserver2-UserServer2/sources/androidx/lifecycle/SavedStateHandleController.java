package androidx.lifecycle;

import X.AbstractC0041Bq;
import X.Bs;
import X.C6;
import X.C7;
import X.Ds;
import X.EnumC0039Bo;
import X.EnumC0040Bp;
import X.Tc;
import X.Td;
import androidx.annotation.NonNull;
import java.util.Map;

public final class SavedStateHandleController implements Td {
    public boolean A00 = false;
    public final C6 A01;
    public final String A02;

    public static void A02(final Ds ds, final AbstractC0041Bq bq) {
        EnumC0040Bp bp = ((Tc) bq).A02;
        if (bp == EnumC0040Bp.INITIALIZED || bp.isAtLeast(EnumC0040Bp.STARTED)) {
            ds.A01();
        } else {
            bq.A05(new Td() {
                /* class androidx.lifecycle.SavedStateHandleController.AnonymousClass1 */

                @Override // X.Td
                public final void A2i(@NonNull Bs bs, @NonNull EnumC0039Bo bo) {
                    if (bo == EnumC0039Bo.ON_START) {
                        ((Tc) bq).A01.A01(this);
                        ds.A01();
                    }
                }
            });
        }
    }

    public static final void A00(SavedStateHandleController savedStateHandleController, Ds ds, AbstractC0041Bq bq) {
        if (!savedStateHandleController.A00) {
            savedStateHandleController.A00 = true;
            bq.A05(savedStateHandleController);
            if (ds.A02.A02(savedStateHandleController.A02, savedStateHandleController.A01.A01) != null) {
                throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
            }
            return;
        }
        throw new IllegalStateException("Already attached to lifecycleOwner");
    }

    public static void A01(C7 c7, Ds ds, AbstractC0041Bq bq) {
        Object obj;
        Map<String, Object> map = c7.A00;
        if (map == null) {
            obj = null;
        } else {
            synchronized (map) {
                obj = map.get("androidx.lifecycle.savedstate.vm.tag");
            }
        }
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) obj;
        if (savedStateHandleController != null && !savedStateHandleController.A00) {
            A00(savedStateHandleController, ds, bq);
            A02(ds, bq);
        }
    }

    @Override // X.Td
    public final void A2i(@NonNull Bs bs, @NonNull EnumC0039Bo bo) {
        if (bo == EnumC0039Bo.ON_DESTROY) {
            this.A00 = false;
            ((Tc) bs.getLifecycle()).A01.A01(this);
        }
    }

    public SavedStateHandleController(String str, C6 c6) {
        this.A02 = str;
        this.A01 = c6;
    }
}
