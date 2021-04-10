package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OuterHighlightDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public float f9650a;
    public float b;
    public float c;

    public void draw(Canvas canvas) {
        canvas.drawCircle(this.b + 0.0f, this.c + 0.0f, 0.0f * this.f9650a, null);
    }

    public int getAlpha() {
        throw null;
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
        throw null;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        throw null;
    }

    public void setScale(float f) {
        this.f9650a = f;
        invalidateSelf();
    }

    public void setTranslationX(float f) {
        this.b = f;
        invalidateSelf();
    }

    public void setTranslationY(float f) {
        this.c = f;
        invalidateSelf();
    }
}
