package org.chromium.chrome.browser.toolbar.bottom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ScrollingBottomViewResourceFrameLayout extends ViewResourceFrameLayout {
    public final Rect I = new Rect();

    /* renamed from: J  reason: collision with root package name */
    public final int f10786J = getResources().getDimensionPixelOffset(R.dimen.f26400_resource_name_obfuscated_RES_2131166259);

    public ScrollingBottomViewResourceFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.components.browser_ui.widget.ViewResourceFrameLayout
    public View$OnLayoutChangeListenerC2948hv1 d() {
        return new UP0(this, this);
    }
}
