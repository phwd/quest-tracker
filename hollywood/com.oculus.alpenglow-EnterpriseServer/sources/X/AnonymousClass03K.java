package X;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Iterator;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.03K  reason: invalid class name */
public final class AnonymousClass03K {
    public long A00 = -1;
    public Interpolator A01;
    public AnonymousClass0B1 A02;
    public boolean A03;
    public final ArrayList<AnonymousClass0B0> A04 = new ArrayList<>();
    public final C03750dB A05 = new C01910Mz(this);

    public final void A00() {
        if (this.A03) {
            Iterator<AnonymousClass0B0> it = this.A04.iterator();
            while (it.hasNext()) {
                it.next().A00();
            }
            this.A03 = false;
        }
    }

    public final void A01() {
        View view;
        if (!this.A03) {
            Iterator<AnonymousClass0B0> it = this.A04.iterator();
            while (it.hasNext()) {
                AnonymousClass0B0 next = it.next();
                long j = this.A00;
                if (j >= 0) {
                    next.A03(j);
                }
                Interpolator interpolator = this.A01;
                if (!(interpolator == null || (view = next.A00.get()) == null)) {
                    view.animate().setInterpolator(interpolator);
                }
                if (this.A02 != null) {
                    next.A04(this.A05);
                }
                View view2 = next.A00.get();
                if (view2 != null) {
                    view2.animate().start();
                }
            }
            this.A03 = true;
        }
    }
}
