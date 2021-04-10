package defpackage;

import android.view.View;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.share.screenshot.ScreenshotShareSheetView;

/* renamed from: wP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC5420wP0 implements View.OnClickListener {
    public final Callback F;
    public final Integer G;

    public View$OnClickListenerC5420wP0(Callback callback, Integer num) {
        this.F = callback;
        this.G = num;
    }

    public void onClick(View view) {
        Callback callback = this.F;
        Integer num = this.G;
        int i = ScreenshotShareSheetView.F;
        callback.onResult(num);
    }
}
