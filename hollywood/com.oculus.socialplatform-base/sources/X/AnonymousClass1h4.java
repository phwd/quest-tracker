package X;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.1h4  reason: invalid class name */
public final class AnonymousClass1h4 implements ViewTreeObserver.OnPreDrawListener {
    public final WeakReference<AnonymousClass1h2> A00;

    public final boolean onPreDraw() {
        int i;
        int i2;
        AnonymousClass1h2 r4 = this.A00.get();
        if (r4 == null) {
            return true;
        }
        List<AbstractC09041fz> list = r4.A02;
        if (list.isEmpty()) {
            return true;
        }
        View view = r4.A01;
        int paddingLeft = view.getPaddingLeft() + view.getPaddingRight();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            i = layoutParams.width;
        } else {
            i = 0;
        }
        int A002 = AnonymousClass1h2.A00(r4, view.getWidth(), i, paddingLeft);
        int paddingTop = view.getPaddingTop() + view.getPaddingBottom();
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 != null) {
            i2 = layoutParams2.height;
        } else {
            i2 = 0;
        }
        int A003 = AnonymousClass1h2.A00(r4, view.getHeight(), i2, paddingTop);
        if (A002 <= 0 && A002 != Integer.MIN_VALUE) {
            return true;
        }
        if (A003 <= 0 && A003 != Integer.MIN_VALUE) {
            return true;
        }
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            ((AbstractC09041fz) it.next()).A84(A002, A003);
        }
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(r4.A00);
        }
        r4.A00 = null;
        list.clear();
        return true;
    }

    public AnonymousClass1h4(@NonNull AnonymousClass1h2 r2) {
        this.A00 = new WeakReference<>(r2);
    }
}
