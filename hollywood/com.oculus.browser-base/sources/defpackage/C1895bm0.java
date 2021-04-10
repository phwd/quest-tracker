package defpackage;

import android.hardware.display.DisplayManager;
import java.util.List;
import java.util.Objects;

/* renamed from: bm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1895bm0 implements DisplayManager.DisplayListener {
    public final /* synthetic */ C2066cm0 F;

    public C1895bm0(C2066cm0 cm0) {
        this.F = cm0;
    }

    public void onDisplayAdded(int i) {
    }

    public void onDisplayChanged(int i) {
        C2066cm0 cm0 = this.F;
        if (i != cm0.Q) {
            List e = AbstractC3153j7.e(cm0.G);
            if (e.size() == 1 && ((Integer) e.get(0)).equals(Integer.valueOf(this.F.Q))) {
                Objects.requireNonNull(this.F);
            }
        }
    }

    public void onDisplayRemoved(int i) {
        C2066cm0 cm0 = this.F;
        if (i == cm0.Q) {
            if (cm0.f9630J.k == 3) {
                cm0.O = true;
            }
        } else if (cm0.h() == null) {
            Objects.requireNonNull(this.F);
        }
    }
}
