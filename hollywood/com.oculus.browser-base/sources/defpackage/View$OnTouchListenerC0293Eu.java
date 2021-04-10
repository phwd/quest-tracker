package defpackage;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataCheckBoxPreference;

/* renamed from: Eu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnTouchListenerC0293Eu implements View.OnTouchListener {
    public final ClearBrowsingDataCheckBoxPreference F;
    public final TextView G;

    public View$OnTouchListenerC0293Eu(ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference, TextView textView) {
        this.F = clearBrowsingDataCheckBoxPreference;
        this.G = textView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.F.j0(this.G, motionEvent);
    }
}
