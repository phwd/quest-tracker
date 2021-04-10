package defpackage;

import J.N;
import android.view.View;
import org.chromium.chrome.browser.media.PictureInPictureActivity;

/* renamed from: XC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class XC0 implements View.OnLayoutChangeListener {
    public XC0(PictureInPictureActivity pictureInPictureActivity) {
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        long j = PictureInPictureActivity.o0;
        if (j != 0) {
            N.MLM3OS4j(j, i3 - i, i4 - i2);
        }
    }
}
