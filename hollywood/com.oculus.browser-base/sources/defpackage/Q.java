package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelListItem;

/* renamed from: Q  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AccessibilityTabModelListItem f8729a;

    public Q(AccessibilityTabModelListItem accessibilityTabModelListItem, M m) {
        this.f8729a = accessibilityTabModelListItem;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (Math.abs(this.f8729a.getTranslationX()) < this.f8729a.L) {
            return false;
        }
        double sqrt = Math.sqrt((double) ((f2 * f2) + (f * f)));
        AccessibilityTabModelListItem accessibilityTabModelListItem = this.f8729a;
        accessibilityTabModelListItem.e(Math.min(((long) Math.abs(((double) this.f8729a.getWidth()) / sqrt)) * 150, (long) accessibilityTabModelListItem.H));
        this.f8729a.j0.G = true;
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        AccessibilityTabModelListItem accessibilityTabModelListItem = this.f8729a;
        if (accessibilityTabModelListItem.g0.a(accessibilityTabModelListItem.d0.getId())) {
            return false;
        }
        this.f8729a.j0.G = false;
        float x = motionEvent2.getX() - motionEvent.getX();
        AccessibilityTabModelListItem accessibilityTabModelListItem2 = this.f8729a;
        accessibilityTabModelListItem2.setTranslationX(accessibilityTabModelListItem2.getTranslationX() + x);
        AccessibilityTabModelListItem accessibilityTabModelListItem3 = this.f8729a;
        accessibilityTabModelListItem3.setAlpha(1.0f - Math.abs(accessibilityTabModelListItem3.getTranslationX() / ((float) this.f8729a.getWidth())));
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.f8729a.performClick();
        return true;
    }
}
