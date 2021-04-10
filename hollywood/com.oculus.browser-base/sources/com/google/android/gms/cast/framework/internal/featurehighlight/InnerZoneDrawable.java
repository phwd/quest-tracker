package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InnerZoneDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public float f9649a;
    public float b;
    public float c;

    public void draw(Canvas canvas) {
        if (this.c <= 0.0f) {
            canvas.drawCircle(0.0f, 0.0f, this.f9649a * 0.0f, null);
            return;
        }
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

    public void setPulseAlpha(float f) {
        this.c = f;
        invalidateSelf();
    }

    public void setPulseScale(float f) {
        this.b = f;
        invalidateSelf();
    }

    public void setScale(float f) {
        this.f9649a = f;
        invalidateSelf();
    }
}
