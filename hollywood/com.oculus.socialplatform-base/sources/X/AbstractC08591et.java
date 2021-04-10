package X;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;

/* renamed from: X.1et  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC08591et<T extends Drawable> implements AnonymousClass1fR<T>, AbstractC08111dz {
    public final T A00;

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Object get() {
        T t = this.A00;
        Drawable.ConstantState constantState = t.getConstantState();
        if (constantState == null) {
            return t;
        }
        return constantState.newDrawable();
    }

    @Override // X.AbstractC08111dz
    public final void initialize() {
        C09131gk r0;
        Bitmap bitmap;
        if (!(this instanceof AnonymousClass1g9)) {
            T t = this.A00;
            if (t instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) t).getBitmap();
            } else if (t instanceof AnonymousClass1gA) {
                r0 = ((AnonymousClass1gA) t).A09;
                bitmap = r0.A00.A03;
            } else {
                return;
            }
        } else {
            r0 = ((AnonymousClass1gA) this.A00).A09;
            bitmap = r0.A00.A03;
        }
        bitmap.prepareToDraw();
    }

    public AbstractC08591et(T t) {
        AnonymousClass1S2.A00(t);
        this.A00 = t;
    }
}
