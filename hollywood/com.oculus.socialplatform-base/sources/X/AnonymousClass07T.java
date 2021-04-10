package X;

import android.os.Build;
import android.view.View;

/* renamed from: X.07T  reason: invalid class name */
public abstract class AnonymousClass07T<T> {
    public final int A00 = 28;
    public final int A01;
    public final Class<T> A02;

    public abstract T A01(View view);

    /* JADX WARN: Incorrect args count in method signature: (ILjava/lang/Class<TT;>;II)V */
    public AnonymousClass07T(int i, Class cls) {
        this.A01 = i;
        this.A02 = cls;
    }

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
