package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1jL  reason: invalid class name */
public abstract class AnonymousClass1jL extends AbstractC03820oc<AbstractC00820Ju<AnonymousClass0VM>> {
    public abstract void onNewResultImpl(@Nullable Bitmap bitmap);

    @Override // X.AbstractC03820oc
    public void onNewResultImpl(AnonymousClass0M8<AbstractC00820Ju<AnonymousClass0VM>> r4) {
        if (r4.A5y()) {
            AbstractC00820Ju<AnonymousClass0VM> A4p = r4.A4p();
            Bitmap bitmap = null;
            if (A4p != null && (A4p.A06() instanceof AnonymousClass0I0)) {
                bitmap = ((AnonymousClass0I0) A4p.A06()).A04();
            }
            try {
                onNewResultImpl(bitmap);
            } finally {
                AbstractC00820Ju.A03(A4p);
            }
        }
    }
}
