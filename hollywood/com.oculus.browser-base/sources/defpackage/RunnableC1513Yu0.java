package defpackage;

import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Yu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1513Yu0 implements Runnable {
    public final C0599Ju0 F;

    public RunnableC1513Yu0(C0599Ju0 ju0) {
        this.F = ju0;
    }

    public void run() {
        C0599Ju0 ju0 = this.F;
        int i = 0;
        boolean z = ju0.F.getVisibility() != 0;
        ju0.F.setVisibility(z ? 0 : 8);
        TextView textView = ju0.G;
        if (z) {
            i = 8;
        }
        textView.setVisibility(i);
        ju0.announceForAccessibility(ju0.getResources().getString(z ? R.string.f57290_resource_name_obfuscated_RES_2131953046 : R.string.f57300_resource_name_obfuscated_RES_2131953047));
    }
}
