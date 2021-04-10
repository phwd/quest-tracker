package org.chromium.chrome.browser.share.screenshot;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ScreenshotShareSheetDialog extends OE {
    public Context M0;
    public Bitmap N0;
    public Tab O0;
    public View$OnLayoutChangeListenerC5940zU0 P0;
    public Callback Q0;

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void e0(Context context) {
        super.e0(context);
        this.M0 = context;
    }

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        C2290e4 e4Var = new C2290e4(u(), R.style.f72740_resource_name_obfuscated_RES_2132017847);
        ScreenshotShareSheetView screenshotShareSheetView = (ScreenshotShareSheetView) u().getLayoutInflater().inflate(R.layout.f41250_resource_name_obfuscated_RES_2131624434, (ViewGroup) null);
        e4Var.h(screenshotShareSheetView);
        Context context = this.M0;
        Bitmap bitmap = this.N0;
        RunnableC3887nP0 np0 = new RunnableC3887nP0(this);
        Tab tab = this.O0;
        View$OnLayoutChangeListenerC5940zU0 zu0 = this.P0;
        Callback callback = this.Q0;
        UH0 uh0 = new UH0(new ArrayList(Arrays.asList(AbstractC5590xP0.c)));
        uh0.m(AbstractC5590xP0.b, bitmap);
        new C4400qP0(context, uh0, np0, new RunnableC3545lP0(new C5250vP0(context, uh0, np0, new I2(new WeakReference((Activity) context)))), tab, zu0, callback);
        ZH0.a(uh0, screenshotShareSheetView, new C3716mP0());
        return e4Var.a();
    }
}
