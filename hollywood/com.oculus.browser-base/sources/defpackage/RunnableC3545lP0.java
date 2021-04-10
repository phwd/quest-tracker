package defpackage;

import android.graphics.Bitmap;
import com.oculus.browser.R;
import org.chromium.chrome.browser.download.DownloadController;

/* renamed from: lP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3545lP0 implements Runnable {
    public final C5250vP0 F;

    public RunnableC3545lP0(C5250vP0 vp0) {
        this.F = vp0;
    }

    public void run() {
        C5250vP0 vp0 = this.F;
        Bitmap bitmap = (Bitmap) vp0.f11477a.g(AbstractC5590xP0.b);
        vp0.e = bitmap;
        if (bitmap != null) {
            if (vp0.c.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE") || vp0.c.canRequestPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
                DownloadController.c(new VG(new C4740sP0(vp0)));
                return;
            }
            C2290e4 e4Var = new C2290e4(vp0.b, R.style.f72700_resource_name_obfuscated_RES_2132017843);
            e4Var.c(R.string.f61690_resource_name_obfuscated_RES_2131953486);
            e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, new DialogInterface$OnClickListenerC5080uP0(vp0));
            e4Var.e(R.string.f61680_resource_name_obfuscated_RES_2131953485, new DialogInterface$OnClickListenerC4910tP0(vp0));
            DialogC2461f4 a2 = e4Var.a();
            vp0.f = a2;
            a2.setCanceledOnTouchOutside(false);
            vp0.f.show();
        }
    }
}
