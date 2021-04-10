package defpackage;

import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.oculus.browser.R;

/* renamed from: Bf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class View$OnClickListenerC0077Bf0 implements View.OnClickListener {
    public final /* synthetic */ DialogC0504If0 F;

    public View$OnClickListenerC0077Bf0(DialogC0504If0 if0) {
        this.F = if0;
    }

    public void onClick(View view) {
        PlaybackStateCompat playbackStateCompat;
        int id = view.getId();
        int i = 1;
        if (id == 16908313 || id == 16908314) {
            if (this.F.L.h()) {
                C3246jh0 jh0 = this.F.f8239J;
                if (id == 16908313) {
                    i = 2;
                }
                jh0.m(i);
            }
            this.F.dismiss();
        } else if (id == R.id.mr_control_playback_ctrl) {
            DialogC0504If0 if0 = this.F;
            if (if0.w0 != null && (playbackStateCompat = if0.y0) != null) {
                int i2 = 0;
                if (playbackStateCompat.F != 3) {
                    i = 0;
                }
                if (i != 0 && if0.n()) {
                    this.F.w0.b().a();
                    i2 = R.string.f55300_resource_name_obfuscated_RES_2131952847;
                } else if (i != 0 && this.F.p()) {
                    this.F.w0.b().c();
                    i2 = R.string.f55320_resource_name_obfuscated_RES_2131952849;
                } else if (i == 0 && this.F.o()) {
                    this.F.w0.b().b();
                    i2 = R.string.f55310_resource_name_obfuscated_RES_2131952848;
                }
                AccessibilityManager accessibilityManager = this.F.S0;
                if (accessibilityManager != null && accessibilityManager.isEnabled() && i2 != 0) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
                    obtain.setPackageName(this.F.M.getPackageName());
                    obtain.setClassName(View$OnClickListenerC0077Bf0.class.getName());
                    obtain.getText().add(this.F.M.getString(i2));
                    this.F.S0.sendAccessibilityEvent(obtain);
                }
            }
        } else if (id == R.id.mr_close) {
            this.F.dismiss();
        }
    }
}
