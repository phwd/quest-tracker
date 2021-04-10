package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: fw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2609fw1 extends AbstractC2097cw1 {
    @Override // defpackage.AbstractC2097cw1
    public void b(ChromeActivity chromeActivity, Intent intent) {
        if (VrModuleProvider.d().a(chromeActivity, intent)) {
            if (a()) {
                boolean z = true;
                if (!(YF.b(chromeActivity).c == 0)) {
                    AbstractC1220Ua0.d("VrDelegate", "Relaunching Chrome onto the main display.", new Object[0]);
                    chromeActivity.finish();
                    chromeActivity.startActivity(intent, Build.VERSION.SDK_INT >= 26 ? C2641g7.a(0) : null);
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            if (chromeActivity.getWindow().findViewById(R.id.vr_overlay_view) == null) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                View view = new View(chromeActivity);
                view.setId(R.id.vr_overlay_view);
                view.setBackgroundColor(-16777216);
                ((FrameLayout) chromeActivity.getWindow().getDecorView()).addView(view, layoutParams);
            }
            chromeActivity.getWindow().addFlags(128);
            chromeActivity.getWindow().getDecorView().setSystemUiVisibility(chromeActivity.getWindow().getDecorView().getSystemUiVisibility() | 5894);
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            VrModuleProvider.e(new C2267dw1(this, atomicBoolean));
            PostTask.b(Zo1.f9374a, new RunnableC2438ew1(this, atomicBoolean, chromeActivity), 1500);
        }
    }

    public final void c(Activity activity) {
        if (!a()) {
            PostTask.b(Zo1.f9374a, new RunnableC2951hw1(activity), 2000);
            activity.finish();
        } else if (!d(activity, false)) {
            activity.finish();
        } else {
            View findViewById = activity.getWindow().findViewById(R.id.vr_overlay_view);
            if (findViewById != null) {
                ((FrameLayout) activity.getWindow().getDecorView()).removeView(findViewById);
            }
            C1184Ti1.a(ContextUtils.getApplicationContext(), R.string.f64600_resource_name_obfuscated_RES_2131953777, 0).b.show();
        }
    }

    public final boolean d(Activity activity, boolean z) {
        try {
            activity.setVrModeEnabled(z, new ComponentName("com.google.vr.vrcore", "com.google.vr.vrcore.common.VrCoreListenerService"));
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            AbstractC1220Ua0.a("VrDelegateFallback", "Cannot unset VR mode", e);
            return false;
        }
    }
}
