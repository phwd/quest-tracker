package defpackage;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.components.browser_ui.widget.text.TemplatePreservingTextView;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Pl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Pl1 extends C5948zY0 {
    public final Activity q;

    public Pl1(Activity activity, View.OnClickListener onClickListener, C4076oY0 oy0, WindowAndroid windowAndroid) {
        super(activity, onClickListener, oy0, (ViewGroup) activity.findViewById(16908290), windowAndroid);
        this.q = activity;
    }

    @Override // defpackage.C5948zY0
    public void a() {
        TemplatePreservingTextView templatePreservingTextView = this.d;
        templatePreservingTextView.announceForAccessibility(((Object) this.d.getContentDescription()) + " " + this.b.getResources().getString(R.string.f63470_resource_name_obfuscated_RES_2131953664));
    }

    @Override // defpackage.C5948zY0
    public int c() {
        int height = this.j.getHeight() - this.c.getHeight();
        Activity activity = this.q;
        int i = 0;
        if (activity instanceof ChromeActivity) {
            ChromeActivity chromeActivity = (ChromeActivity) activity;
            if (chromeActivity.M0().T != 0) {
                i = chromeActivity.M0().M;
            }
        }
        return height - i;
    }

    @Override // defpackage.C5948zY0
    public int d() {
        return -this.b.getHeight();
    }
}
