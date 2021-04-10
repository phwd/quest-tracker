package X;

import android.graphics.Typeface;

/* renamed from: X.088  reason: invalid class name */
public class AnonymousClass088 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.content.res.ResourcesCompat$FontCallback$1";
    public final /* synthetic */ Typeface A00;
    public final /* synthetic */ AnonymousClass08A A01;

    public AnonymousClass088(AnonymousClass08A r1, Typeface typeface) {
        this.A01 = r1;
        this.A00 = typeface;
    }

    public final void run() {
        this.A01.A02(this.A00);
    }
}
