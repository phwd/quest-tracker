package org.chromium.chrome.browser.webapps;

import J.N;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PwaBottomSheetController extends Ep1 implements A3, View.OnClickListener {
    public final Activity F;
    public long G;
    public AbstractC4448qj H;
    public PI0 I;

    /* renamed from: J  reason: collision with root package name */
    public UH0 f10800J;
    public OI0 K;
    public WebContents L;
    public AbstractC6022zx1 M;

    public PwaBottomSheetController(Activity activity) {
        this.F = activity;
    }

    public static void addWebAppScreenshot(Bitmap bitmap, WebContents webContents) {
        PwaBottomSheetController pwaBottomSheetController;
        WindowAndroid I2 = webContents.I();
        if (I2 != null && (pwaBottomSheetController = (PwaBottomSheetController) PwaBottomSheetControllerProvider.f10801a.e(I2.U)) != null) {
            OI0 oi0 = pwaBottomSheetController.K;
            oi0.f8617J.add(bitmap);
            oi0.F.b();
        }
    }

    @Override // defpackage.A3
    public boolean f() {
        return false;
    }

    @Override // defpackage.A3
    public void g(String str) {
        N.MP8mMucP(this.G, this.L);
    }

    @Override // defpackage.A3
    public void i() {
        this.M = null;
        this.I = null;
        N.MSaM2QtS(this.G);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_install) {
            N.MP8mMucP(this.G, this.L);
            ((C5638xj) this.H).p(this.I, false, 0);
        } else if (id != R.id.drag_handlebar) {
        } else {
            if (((C5638xj) this.H).q()) {
                ((C5638xj) this.H).l(true);
            } else {
                ((C5638xj) this.H).m();
            }
        }
    }
}
