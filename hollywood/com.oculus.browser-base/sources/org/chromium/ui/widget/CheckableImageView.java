package org.chromium.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CheckableImageView extends ChromeImageView implements Checkable {
    public static final int[] H = {16842912};
    public boolean I;

    public CheckableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean isChecked() {
        return this.I;
    }

    public int[] onCreateDrawableState(int i) {
        if (!isChecked()) {
            return super.onCreateDrawableState(i);
        }
        int[] iArr = H;
        return ImageView.mergeDrawableStates(super.onCreateDrawableState(i + iArr.length), iArr);
    }

    public void setChecked(boolean z) {
        if (this.I != z) {
            this.I = z;
            refreshDrawableState();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView
    public void setImageDrawable(Drawable drawable) {
        if (drawable != getDrawable()) {
            super.setImageDrawable(drawable);
            refreshDrawableState();
        }
    }

    public void toggle() {
        setChecked(!this.I);
    }
}
