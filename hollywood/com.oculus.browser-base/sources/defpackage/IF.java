package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import com.oculus.browser.R;

/* renamed from: IF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IF {

    /* renamed from: a  reason: collision with root package name */
    public final Dialog f8211a;
    public final ViewGroup b;
    public final int c;
    public HF d;
    public boolean e;

    public IF(Activity activity, HF hf) {
        this.d = hf;
        FrameLayout frameLayout = new FrameLayout(activity);
        this.b = frameLayout;
        frameLayout.setBackgroundColor(activity.getResources().getColor(R.color.f13320_resource_name_obfuscated_RES_2131100022));
        K4 k4 = new K4(activity, R.style.f68530_resource_name_obfuscated_RES_2132017426);
        this.f8211a = k4;
        k4.setOnDismissListener(new DF(this));
        k4.addContentView(frameLayout, new ViewGroup.LayoutParams(-1, -1));
        Window window = k4.getWindow();
        window.setGravity(17);
        window.setLayout(-1, -1);
        window.setBackgroundDrawable(new ColorDrawable(0));
        b(window);
        this.c = activity.getResources().getDimensionPixelSize(R.dimen.f24020_resource_name_obfuscated_RES_2131166021);
    }

    public static void b(Window window) {
        if (Build.VERSION.SDK_INT >= 26) {
            AbstractC3153j7.h(window.getDecorView().getRootView(), !AbstractC1270Uv.g(window.getStatusBarColor()));
        }
    }

    public final void a() {
        HF hf = this.d;
        if (hf != null) {
            TA0 ta0 = (TA0) hf;
            ta0.o0 = true;
            if (ta0.N.isShowing()) {
                ta0.N.dismiss();
            }
            if (ta0.O.isShowing()) {
                ta0.O.dismiss();
            }
            if (!ta0.k0) {
                ((C0289Es) ((AB0) ta0.I).m).g(0, "User closed the Payment Request UI.");
            }
            this.d = null;
        }
    }
}
