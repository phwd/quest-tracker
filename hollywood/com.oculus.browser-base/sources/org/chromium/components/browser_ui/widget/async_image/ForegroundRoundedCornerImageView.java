package org.chromium.components.browser_ui.widget.async_image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import java.util.Objects;
import org.chromium.components.browser_ui.widget.RoundedCornerImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ForegroundRoundedCornerImageView extends RoundedCornerImageView {
    public final VR P;

    public ForegroundRoundedCornerImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.P.a(canvas);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.P.b();
    }

    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.P.c(view, i);
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (!super.verifyDrawable(drawable)) {
            VR vr = this.P;
            Objects.requireNonNull(vr);
            if (!(drawable != null && vr.K == drawable)) {
                return false;
            }
        }
        return true;
    }

    public ForegroundRoundedCornerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArray;
        VR vr = new VR(this);
        this.P = vr;
        if (attributeSet == null) {
            typedArray = null;
        } else {
            typedArray = context.obtainStyledAttributes(attributeSet, FJ0.P, 0, 0);
        }
        vr.d(C2547fc.b(AbstractC2417ep1.e(context, typedArray, 0)));
        if (typedArray != null) {
            typedArray.recycle();
        }
    }
}
