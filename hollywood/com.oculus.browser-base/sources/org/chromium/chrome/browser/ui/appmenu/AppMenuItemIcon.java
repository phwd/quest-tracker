package org.chromium.chrome.browser.ui.appmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;
import org.chromium.ui.widget.ChromeImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AppMenuItemIcon extends ChromeImageView implements Checkable {
    public static final int[] H = {16842912};
    public boolean I;

    public AppMenuItemIcon(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean isChecked() {
        return this.I;
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.I) {
            ImageView.mergeDrawableStates(onCreateDrawableState, H);
        }
        return onCreateDrawableState;
    }

    public void setChecked(boolean z) {
        if (z != this.I) {
            this.I = z;
            refreshDrawableState();
        }
    }

    public void toggle() {
        setChecked(!this.I);
    }
}
