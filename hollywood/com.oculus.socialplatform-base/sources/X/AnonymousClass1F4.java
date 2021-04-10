package X;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.ActionBarContainer;

/* renamed from: X.1F4  reason: invalid class name */
public final class AnonymousClass1F4 extends Drawable {
    public final ActionBarContainer A00;

    public final int getOpacity() {
        return 0;
    }

    public final void setAlpha(int i) {
    }

    public final void setColorFilter(ColorFilter colorFilter) {
    }

    public final void draw(Canvas canvas) {
        ActionBarContainer actionBarContainer = this.A00;
        if (actionBarContainer.A03) {
            Drawable drawable = actionBarContainer.A01;
            if (drawable != null) {
                drawable.draw(canvas);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.A00;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Drawable drawable3 = actionBarContainer.A02;
        if (drawable3 != null && actionBarContainer.A04) {
            drawable3.draw(canvas);
        }
    }

    @RequiresApi(21)
    public final void getOutline(@NonNull Outline outline) {
        Drawable drawable;
        ActionBarContainer actionBarContainer = this.A00;
        if (actionBarContainer.A03) {
            drawable = actionBarContainer.A01;
        } else {
            drawable = actionBarContainer.A00;
        }
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    public AnonymousClass1F4(ActionBarContainer actionBarContainer) {
        this.A00 = actionBarContainer;
    }
}
