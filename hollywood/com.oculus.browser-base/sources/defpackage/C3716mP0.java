package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.share.screenshot.ScreenshotShareSheetView;
import org.chromium.ui.widget.ChromeImageView;

/* renamed from: mP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3716mP0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        ScreenshotShareSheetView screenshotShareSheetView = (ScreenshotShareSheetView) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5590xP0.f11607a;
        if (th0 == kh0) {
            Callback callback = (Callback) uh0.g(th0);
            screenshotShareSheetView.a(1, R.id.share, callback);
            screenshotShareSheetView.a(2, R.id.save, callback);
            screenshotShareSheetView.a(3, R.id.delete, callback);
            screenshotShareSheetView.a(3, R.id.close_button, callback);
            screenshotShareSheetView.a(4, R.id.edit, callback);
            return;
        }
        TH0 th02 = AbstractC5590xP0.b;
        if (th02 == kh0) {
            ((ChromeImageView) screenshotShareSheetView.findViewById(R.id.screenshot)).setImageDrawable(new BitmapDrawable((Bitmap) uh0.g(th02)));
        }
    }
}
