package defpackage;

import android.graphics.Bitmap;
import android.view.View;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadController;

/* renamed from: nJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC3874nJ0 implements View.OnClickListener {
    public final C4898tJ0 F;

    public View$OnClickListenerC3874nJ0(C4898tJ0 tj0) {
        this.F = tj0;
    }

    public void onClick(View view) {
        C4898tJ0 tj0 = this.F;
        Objects.requireNonNull(tj0);
        AbstractC3535lK0.a("SharingQRCode.DownloadQRCode");
        if (tj0.d > 0) {
            AbstractC3535lK0.a("SharingQRCode.DownloadQRCodeMultipleAttempts");
        }
        tj0.d++;
        if (((Bitmap) tj0.b.g(AbstractC5578xJ0.f11604a)) != null && !tj0.e) {
            DownloadController.c(new VG(new C4387qJ0(tj0)));
        }
    }
}
