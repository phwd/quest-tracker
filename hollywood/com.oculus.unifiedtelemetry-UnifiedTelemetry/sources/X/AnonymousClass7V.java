package X;

import android.os.Build;
import android.view.View;
import com.oculus.unifiedtelemetry.R;

/* renamed from: X.7V  reason: invalid class name */
public abstract class AnonymousClass7V<T> {
    public final int A00 = 28;
    public final int A01 = R.id.tag_accessibility_pane_title;
    public final Class<T> A02 = CharSequence.class;

    public abstract T A01(View view);

    public final T A00(View view) {
        if (Build.VERSION.SDK_INT >= this.A00) {
            return A01(view);
        }
        T t = (T) view.getTag(this.A01);
        if (!this.A02.isInstance(t)) {
            return null;
        }
        return t;
    }
}
