package X;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1ll  reason: invalid class name and case insensitive filesystem */
public class C10291ll extends Drawable implements Drawable.Callback {
    public Drawable A00;

    public void draw(Canvas canvas) {
        this.A00.draw(canvas);
    }

    public final int getChangingConfigurations() {
        return this.A00.getChangingConfigurations();
    }

    public final Drawable getCurrent() {
        return this.A00.getCurrent();
    }

    public final int getIntrinsicHeight() {
        return this.A00.getIntrinsicHeight();
    }

    public final int getIntrinsicWidth() {
        return this.A00.getIntrinsicWidth();
    }

    public final int getMinimumHeight() {
        return this.A00.getMinimumHeight();
    }

    public final int getMinimumWidth() {
        return this.A00.getMinimumWidth();
    }

    public final int getOpacity() {
        return this.A00.getOpacity();
    }

    public final boolean getPadding(Rect rect) {
        return this.A00.getPadding(rect);
    }

    public final int[] getState() {
        return this.A00.getState();
    }

    public final Region getTransparentRegion() {
        return this.A00.getTransparentRegion();
    }

    public final boolean isAutoMirrored() {
        return this.A00.isAutoMirrored();
    }

    public final boolean isStateful() {
        return this.A00.isStateful();
    }

    public final void jumpToCurrentState() {
        this.A00.jumpToCurrentState();
    }

    public final void onBoundsChange(Rect rect) {
        this.A00.setBounds(rect);
    }

    public final boolean onLevelChange(int i) {
        return this.A00.setLevel(i);
    }

    public final void setAlpha(int i) {
        this.A00.setAlpha(i);
    }

    public final void setAutoMirrored(boolean z) {
        this.A00.setAutoMirrored(z);
    }

    public final void setChangingConfigurations(int i) {
        this.A00.setChangingConfigurations(i);
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.A00.setColorFilter(colorFilter);
    }

    public final void setDither(boolean z) {
        this.A00.setDither(z);
    }

    public final void setFilterBitmap(boolean z) {
        this.A00.setFilterBitmap(z);
    }

    public void setHotspot(float f, float f2) {
        this.A00.setHotspot(f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.A00.setHotspotBounds(i, i2, i3, i4);
    }

    public boolean setState(int[] iArr) {
        return this.A00.setState(iArr);
    }

    public final void setTint(int i) {
        this.A00.setTint(i);
    }

    public final void setTintList(ColorStateList colorStateList) {
        this.A00.setTintList(colorStateList);
    }

    public final void setTintMode(PorterDuff.Mode mode) {
        this.A00.setTintMode(mode);
    }

    public C10291ll(Drawable drawable) {
        Drawable drawable2 = this.A00;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.A00 = drawable;
        drawable.setCallback(this);
    }

    public boolean setVisible(boolean z, boolean z2) {
        if (super.setVisible(z, z2) || this.A00.setVisible(z, z2)) {
            return true;
        }
        return false;
    }

    public final void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }
}
