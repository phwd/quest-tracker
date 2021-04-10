package X;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;
import java.util.List;

/* renamed from: X.1h0  reason: invalid class name */
public abstract class AnonymousClass1h0<T extends View, Z> implements AbstractC08781fH<Z> {
    public final T A00;
    public final AnonymousClass1h1 A01;

    @Override // X.AbstractC08541eo
    public final void onDestroy() {
    }

    @Override // X.AbstractC08541eo
    public final void onStart() {
    }

    @Override // X.AbstractC08541eo
    public final void onStop() {
    }

    @Override // X.AbstractC08781fH
    @Nullable
    public final AnonymousClass1h5 getRequest() {
        Object tag = this.A00.getTag(R.id.glide_custom_view_target_tag);
        if (tag == null) {
            return null;
        }
        if (tag instanceof AnonymousClass1h5) {
            return (AnonymousClass1h5) tag;
        }
        throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
    }

    @Override // X.AbstractC08781fH
    public final void getSize(@NonNull AbstractC09041fz r7) {
        int i;
        int i2;
        AnonymousClass1h1 r3 = this.A01;
        View view = r3.A01;
        int paddingLeft = view.getPaddingLeft() + view.getPaddingRight();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            i = layoutParams.width;
        } else {
            i = 0;
        }
        int A002 = AnonymousClass1h1.A00(r3, view.getWidth(), i, paddingLeft);
        int paddingTop = view.getPaddingTop() + view.getPaddingBottom();
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 != null) {
            i2 = layoutParams2.height;
        } else {
            i2 = 0;
        }
        int A003 = AnonymousClass1h1.A00(r3, view.getHeight(), i2, paddingTop);
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
            AnonymousClass1h3 r0 = new AnonymousClass1h3(r3);
            r3.A00 = r0;
            viewTreeObserver.addOnPreDrawListener(r0);
        }
    }

    @Override // X.AbstractC08781fH
    public final void onLoadCleared(@Nullable Drawable drawable) {
        AnonymousClass1h1 r2 = this.A01;
        ViewTreeObserver viewTreeObserver = r2.A01.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(r2.A00);
        }
        r2.A00 = null;
        r2.A02.clear();
    }

    @Override // X.AbstractC08781fH
    public final void removeCallback(@NonNull AbstractC09041fz r2) {
        this.A01.A02.remove(r2);
    }

    @Override // X.AbstractC08781fH
    public final void setRequest(@Nullable AnonymousClass1h5 r3) {
        this.A00.setTag(R.id.glide_custom_view_target_tag, r3);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Target for: ");
        sb.append(this.A00);
        return sb.toString();
    }

    public AnonymousClass1h0(@NonNull T t) {
        AnonymousClass1S2.A00(t);
        this.A00 = t;
        this.A01 = new AnonymousClass1h1(t);
    }

    @Override // X.AbstractC08781fH
    public final void onLoadStarted(@Nullable Drawable drawable) {
    }
}
