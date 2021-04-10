package X;

import android.view.View;
import android.view.WindowInsets;

/* renamed from: X.07Q  reason: invalid class name */
public class AnonymousClass07Q implements View.OnApplyWindowInsetsListener {
    public final /* synthetic */ AnonymousClass07J A00;

    public AnonymousClass07Q(AnonymousClass07J r1) {
        this.A00 = r1;
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (windowInsets != null) {
            return this.A00.A5k(view, new AnonymousClass07r(windowInsets)).A00();
        }
        throw null;
    }
}
