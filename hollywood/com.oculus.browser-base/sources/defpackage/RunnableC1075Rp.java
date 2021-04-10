package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import java.util.Objects;
import org.chromium.chrome.browser.accessibility.FontSizePrefs;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: Rp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1075Rp implements Runnable {
    public final ChromeActivity F;
    public final String G;

    public RunnableC1075Rp(ChromeActivity chromeActivity, String str) {
        this.F = chromeActivity;
        this.G = str;
    }

    public void run() {
        int i;
        ChromeActivity chromeActivity = this.F;
        String str = this.G;
        if (!chromeActivity.v()) {
            if (chromeActivity.b1.V != null) {
                AbstractC3364kK0.k(AbstractC2531fV.f("MobileStartup.ToolbarInflationTime.", str), chromeActivity.R0 - chromeActivity.Q0);
                chromeActivity.b1.V.d0.L();
            }
            if (chromeActivity.isInMultiWindowMode()) {
                AbstractC3535lK0.a("Android.MultiWindowMode.Enter");
            }
            Intent intent = chromeActivity.getIntent();
            ComponentName componentName = S20.f8870a;
            long longExtra = intent.getLongExtra("org.chromium.chrome.browser.timestamp", -1);
            if (longExtra != -1) {
                chromeActivity.n1(chromeActivity.a0 - longExtra);
            }
            YF b = YF.b(chromeActivity);
            Point point = b.d;
            float f = b.e;
            int i2 = (int) ((((float) point.x) / f) + 0.5f);
            int i3 = (int) ((((float) point.y) / f) + 0.5f);
            int i4 = i2 > i3 ? i2 : i3;
            if (i2 >= i3) {
                i2 = i3;
            }
            boolean z = false;
            AbstractC3100ip1.f10165a.d("Android.DeviceSize.SmallestDisplaySize", AbstractC4089od0.c(i2, 0, 1000));
            AbstractC3100ip1.f10165a.d("Android.DeviceSize.LargestDisplaySize", AbstractC4089od0.c(i4, 200, 1200));
            try {
                i = chromeActivity.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
                i = 0;
            }
            if (i > 0) {
                z = true;
            }
            AbstractC3100ip1.f10165a.a("Android.PlayServices.Installed", z);
            AbstractC3100ip1.f10165a.d("Android.PlayServices.Version", i);
            Objects.requireNonNull(FontSizePrefs.b());
            AbstractC3100ip1.f10165a.d("Accessibility.Android.UserFontSizePref.OnStartup", (int) (FontSizePrefs.b().d() * 100.0f));
        }
    }
}
