package X;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

@Deprecated
/* renamed from: X.1gz  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09221gz<T extends View, Z> extends AbstractC09191gs<Z> {
    public final T A00;
    public final AnonymousClass1h2 A01;

    @Override // X.AbstractC08781fH
    @CallSuper
    public final void getSize(@NonNull AbstractC09041fz r7) {
        int i;
        int i2;
        AnonymousClass1h2 r3 = this.A01;
        View view = r3.A01;
        int paddingLeft = view.getPaddingLeft() + view.getPaddingRight();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            i = layoutParams.width;
        } else {
            i = 0;
        }
        int A002 = AnonymousClass1h2.A00(r3, view.getWidth(), i, paddingLeft);
        int paddingTop = view.getPaddingTop() + view.getPaddingBottom();
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 != null) {
            i2 = layoutParams2.height;
        } else {
            i2 = 0;
        }
        int A003 = AnonymousClass1h2.A00(r3, view.getHeight(), i2, paddingTop);
        if ((A002 > 0 || A002 == Integer.MIN_VALUE) && (A003 > 0 || A003 == Integer.MIN_VALUE)) {
            r7.A84(A002, A003);
            return;
        }
        List<AbstractC09041fz> list = r3.A02;
        if (!list.contains(r7)) {
            list.add(r7);
        }
        if (r3.A00 == null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            AnonymousClass1h4 r0 = new AnonymousClass1h4(r3);
            r3.A00 = r0;
            viewTreeObserver.addOnPreDrawListener(r0);
        }
    }

    @Override // X.AbstractC08781fH
    @CallSuper
    public final void removeCallback(@NonNull AbstractC09041fz r2) {
        this.A01.A02.remove(r2);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Target for: ");
        sb.append(this.A00);
        return sb.toString();
    }

    public AbstractC09221gz(@NonNull T t) {
        AnonymousClass1S2.A00(t);
        this.A00 = t;
        this.A01 = new AnonymousClass1h2(t);
    }

    @Override // X.AbstractC08781fH, X.AbstractC09191gs
    @CallSuper
    public void onLoadCleared(@Nullable Drawable drawable) {
        super.onLoadCleared(drawable);
        AnonymousClass1h2 r2 = this.A01;
        ViewTreeObserver viewTreeObserver = r2.A01.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(r2.A00);
        }
        r2.A00 = null;
        r2.A02.clear();
    }

    @Override // X.AbstractC08781fH, X.AbstractC09191gs
    @CallSuper
    public void onLoadStarted(@Nullable Drawable drawable) {
        super.onLoadStarted(drawable);
    }
}
