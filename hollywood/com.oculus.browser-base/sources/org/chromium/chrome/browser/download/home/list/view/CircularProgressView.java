package org.chromium.chrome.browser.download.home.list.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import java.util.Objects;
import org.chromium.ui.widget.ChromeImageButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CircularProgressView extends ChromeImageButton {
    public final VR H;

    public CircularProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArray;
        VR vr = new VR(this);
        this.H = vr;
        ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
        if (vr.L != scaleType) {
            vr.L = scaleType;
            vr.f9083J = true;
            if (vr.K != null) {
                vr.I.invalidate();
            }
        }
        if (attributeSet == null) {
            typedArray = null;
        } else {
            typedArray = context.obtainStyledAttributes(attributeSet, FJ0.D, 0, 0);
        }
        C2547fc.b(AbstractC2417ep1.e(context, typedArray, 1));
        C2547fc.b(AbstractC2417ep1.e(context, typedArray, 0));
        AbstractC2417ep1.e(context, typedArray, 3);
        AbstractC2417ep1.e(context, typedArray, 2);
        AbstractC2417ep1.e(context, typedArray, 4);
        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.H.a(canvas);
    }

    @Override // org.chromium.ui.widget.ChromeImageButton, defpackage.C4353q8
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.H.b();
    }

    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.H.c(view, i);
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (!super.verifyDrawable(drawable)) {
            VR vr = this.H;
            Objects.requireNonNull(vr);
            if (!(drawable != null && vr.K == drawable)) {
                return false;
            }
        }
        return true;
    }
}
