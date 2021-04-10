package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* renamed from: YI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class YI extends Drawable implements Drawable.Callback {
    public Drawable F;

    public YI(Drawable drawable) {
        Drawable drawable2 = this.F;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.F = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    public void draw(Canvas canvas) {
        this.F.draw(canvas);
    }

    public int getChangingConfigurations() {
        return this.F.getChangingConfigurations();
    }

    public Drawable getCurrent() {
        return this.F.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.F.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.F.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.F.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.F.getMinimumWidth();
    }

    public int getOpacity() {
        return this.F.getOpacity();
    }

    public boolean getPadding(Rect rect) {
        return this.F.getPadding(rect);
    }

    public int[] getState() {
        return this.F.getState();
    }

    public Region getTransparentRegion() {
        return this.F.getTransparentRegion();
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public boolean isAutoMirrored() {
        return this.F.isAutoMirrored();
    }

    public boolean isStateful() {
        return this.F.isStateful();
    }

    public void jumpToCurrentState() {
        this.F.jumpToCurrentState();
    }

    public void onBoundsChange(Rect rect) {
        this.F.setBounds(rect);
    }

    public boolean onLevelChange(int i) {
        return this.F.setLevel(i);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.F.setAlpha(i);
    }

    public void setAutoMirrored(boolean z) {
        this.F.setAutoMirrored(z);
    }

    public void setChangingConfigurations(int i) {
        this.F.setChangingConfigurations(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.F.setColorFilter(colorFilter);
    }

    public void setDither(boolean z) {
        this.F.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.F.setFilterBitmap(z);
    }

    public void setHotspot(float f, float f2) {
        this.F.setHotspot(f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.F.setHotspotBounds(i, i2, i3, i4);
    }

    public boolean setState(int[] iArr) {
        return this.F.setState(iArr);
    }

    public void setTint(int i) {
        this.F.setTint(i);
    }

    public void setTintList(ColorStateList colorStateList) {
        this.F.setTintList(colorStateList);
    }

    public void setTintMode(PorterDuff.Mode mode) {
        this.F.setTintMode(mode);
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.F.setVisible(z, z2);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
