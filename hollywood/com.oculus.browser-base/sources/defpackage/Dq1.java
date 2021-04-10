package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import org.chromium.chrome.browser.omnibox.UrlBarApi26;

/* renamed from: Dq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Dq1 extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ UrlBarApi26 f7915a;

    public Dq1(UrlBarApi26 urlBarApi26) {
        this.f7915a = urlBarApi26;
    }

    public void onLongPress(MotionEvent motionEvent) {
        Hq1 hq1 = this.f7915a.Q;
        if (hq1 != null) {
            hq1.f(true);
            this.f7915a.performLongClick();
        }
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        UrlBarApi26 urlBarApi26 = this.f7915a;
        if (urlBarApi26.Q == null) {
            return true;
        }
        urlBarApi26.requestFocus();
        this.f7915a.Q.f(false);
        return true;
    }
}
