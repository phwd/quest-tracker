package org.chromium.chrome.browser.share.screenshot;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ScreenshotShareSheetView extends FrameLayout {
    public static final /* synthetic */ int F = 0;

    public ScreenshotShareSheetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a(Integer num, int i, Callback callback) {
        findViewById(i).setOnClickListener(new View$OnClickListenerC5420wP0(callback, num));
    }
}
