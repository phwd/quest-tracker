package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;

/* renamed from: XB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class XB extends GestureDetector.SimpleOnGestureListener {
    public XB(CustomTabToolbar.InterceptTouchLayout interceptTouchLayout) {
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (C2474f80.f9900a.f()) {
            AbstractC3535lK0.a("CustomTabs.TapUrlBar");
        }
        return super.onSingleTapConfirmed(motionEvent);
    }
}
