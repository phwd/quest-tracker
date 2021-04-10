package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import org.chromium.chrome.browser.ntp.NewTabPageScrollView;

/* renamed from: Xn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1438Xn0 extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ NewTabPageScrollView f9234a;

    public C1438Xn0(NewTabPageScrollView newTabPageScrollView) {
        this.f9234a = newTabPageScrollView;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        boolean onSingleTapUp = super.onSingleTapUp(motionEvent);
        this.f9234a.requestFocus();
        return onSingleTapUp;
    }
}
