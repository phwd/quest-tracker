package X;

import android.graphics.Typeface;

/* renamed from: X.04q  reason: invalid class name */
public class AnonymousClass04q implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.content.res.ResourcesCompat$FontCallback$1";
    public final /* synthetic */ Typeface A00;
    public final /* synthetic */ AbstractC001704s A01;

    public AnonymousClass04q(AbstractC001704s r1, Typeface typeface) {
        this.A01 = r1;
        this.A00 = typeface;
    }

    public final void run() {
        this.A01.A02(this.A00);
    }
}
