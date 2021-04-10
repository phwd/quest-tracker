package com.bumptech.glide.request.target;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.util.Preconditions;

public class FixedSizeDrawable extends Drawable {
    private final RectF bounds;
    private final Matrix matrix;
    private boolean mutated;
    private State state;
    private Drawable wrapped;
    private final RectF wrappedRect;

    public FixedSizeDrawable(Drawable drawable, int i, int i2) {
        this(new State(drawable.getConstantState(), i, i2), drawable);
    }

    FixedSizeDrawable(State state2, Drawable drawable) {
        this.state = (State) Preconditions.checkNotNull(state2);
        this.wrapped = (Drawable) Preconditions.checkNotNull(drawable);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        this.matrix = new Matrix();
        this.wrappedRect = new RectF(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        this.bounds = new RectF();
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.bounds.set((float) i, (float) i2, (float) i3, (float) i4);
        updateMatrix();
    }

    public void setBounds(@NonNull Rect rect) {
        super.setBounds(rect);
        this.bounds.set(rect);
        updateMatrix();
    }

    private void updateMatrix() {
        this.matrix.setRectToRect(this.wrappedRect, this.bounds, Matrix.ScaleToFit.CENTER);
    }

    public void setChangingConfigurations(int i) {
        this.wrapped.setChangingConfigurations(i);
    }

    public int getChangingConfigurations() {
        return this.wrapped.getChangingConfigurations();
    }

    @Deprecated
    public void setDither(boolean z) {
        this.wrapped.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.wrapped.setFilterBitmap(z);
    }

    public Drawable.Callback getCallback() {
        return this.wrapped.getCallback();
    }

    @RequiresApi(19)
    public int getAlpha() {
        return this.wrapped.getAlpha();
    }

    public void setColorFilter(int i, @NonNull PorterDuff.Mode mode) {
        this.wrapped.setColorFilter(i, mode);
    }

    public void clearColorFilter() {
        this.wrapped.clearColorFilter();
    }

    @NonNull
    public Drawable getCurrent() {
        return this.wrapped.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return this.wrapped.setVisible(z, z2);
    }

    public int getIntrinsicWidth() {
        return this.state.width;
    }

    public int getIntrinsicHeight() {
        return this.state.height;
    }

    public int getMinimumWidth() {
        return this.wrapped.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.wrapped.getMinimumHeight();
    }

    public boolean getPadding(@NonNull Rect rect) {
        return this.wrapped.getPadding(rect);
    }

    public void invalidateSelf() {
        super.invalidateSelf();
        this.wrapped.invalidateSelf();
    }

    public void unscheduleSelf(@NonNull Runnable runnable) {
        super.unscheduleSelf(runnable);
        this.wrapped.unscheduleSelf(runnable);
    }

    public void scheduleSelf(@NonNull Runnable runnable, long j) {
        super.scheduleSelf(runnable, j);
        this.wrapped.scheduleSelf(runnable, j);
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.concat(this.matrix);
        this.wrapped.draw(canvas);
        canvas.restore();
    }

    public void setAlpha(int i) {
        this.wrapped.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.wrapped.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return this.wrapped.getOpacity();
    }

    @NonNull
    public Drawable mutate() {
        if (!this.mutated && super.mutate() == this) {
            this.wrapped = this.wrapped.mutate();
            this.state = new State(this.state);
            this.mutated = true;
        }
        return this;
    }

    public Drawable.ConstantState getConstantState() {
        return this.state;
    }

    static final class State extends Drawable.ConstantState {
        final int height;
        final int width;
        private final Drawable.ConstantState wrapped;

        public int getChangingConfigurations() {
            return 0;
        }

        State(State state) {
            this(state.wrapped, state.width, state.height);
        }

        State(Drawable.ConstantState constantState, int i, int i2) {
            this.wrapped = constantState;
            this.width = i;
            this.height = i2;
        }

        @NonNull
        public Drawable newDrawable() {
            return new FixedSizeDrawable(this, this.wrapped.newDrawable());
        }

        @NonNull
        public Drawable newDrawable(Resources resources) {
            return new FixedSizeDrawable(this, this.wrapped.newDrawable(resources));
        }
    }
}
