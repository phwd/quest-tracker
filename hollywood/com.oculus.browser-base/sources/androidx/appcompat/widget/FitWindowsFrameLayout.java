package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FitWindowsFrameLayout extends FrameLayout {
    public T7 F;

    public FitWindowsFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean fitSystemWindows(Rect rect) {
        if (this.F == null) {
            return super.fitSystemWindows(rect);
        }
        throw null;
    }
}
