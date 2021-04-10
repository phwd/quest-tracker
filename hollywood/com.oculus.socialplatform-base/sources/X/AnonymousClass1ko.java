package X;

import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.infer.annotation.Nullsafe;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1ko  reason: invalid class name */
public class AnonymousClass1ko<INFO> {
    public static final AnonymousClass1ko A00 = new AnonymousClass1ko();

    public final void A01(String str, @Nullable AnonymousClass1lF r8) {
        if (this instanceof C09981ka) {
            C09981ka r5 = (C09981ka) this;
            List<ControllerListener2<I>> list = r5.A00;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                try {
                    AnonymousClass1ko r0 = list.get(i);
                    if (r0 != null) {
                        r0.A01(str, r8);
                    }
                } catch (Exception e) {
                    C09981ka.A00(r5, "ForwardingControllerListener2 exception in onRelease", e);
                }
            }
        }
    }

    public final void A02(String str, @Nullable INFO info, AnonymousClass1lF r9) {
        if (this instanceof C09981ka) {
            C09981ka r5 = (C09981ka) this;
            List<ControllerListener2<I>> list = r5.A00;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                try {
                    AnonymousClass1ko r0 = list.get(i);
                    if (r0 != null) {
                        r0.A02(str, info, r9);
                    }
                } catch (Exception e) {
                    C09981ka.A00(r5, "ForwardingControllerListener2 exception in onFinalImageSet", e);
                }
            }
        }
    }

    public final void A03(String str, Object obj, @Nullable AnonymousClass1lF r9) {
        if (this instanceof C09981ka) {
            C09981ka r5 = (C09981ka) this;
            List<ControllerListener2<I>> list = r5.A00;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                try {
                    AnonymousClass1ko r0 = list.get(i);
                    if (r0 != null) {
                        r0.A03(str, obj, r9);
                    }
                } catch (Exception e) {
                    C09981ka.A00(r5, "ForwardingControllerListener2 exception in onSubmit", e);
                }
            }
        }
    }

    public final void A04(String str, Throwable th, @Nullable AnonymousClass1lF r9) {
        if (this instanceof C09981ka) {
            C09981ka r5 = (C09981ka) this;
            List<ControllerListener2<I>> list = r5.A00;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                try {
                    AnonymousClass1ko r0 = list.get(i);
                    if (r0 != null) {
                        r0.A04(str, th, r9);
                    }
                } catch (Exception e) {
                    C09981ka.A00(r5, "ForwardingControllerListener2 exception in onFailure", e);
                }
            }
        }
    }
}
