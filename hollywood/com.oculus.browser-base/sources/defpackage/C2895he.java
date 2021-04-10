package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: he  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2895he extends View.AccessibilityDelegate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3066ie f10087a;

    public C2895he(C3066ie ieVar) {
        this.f10087a = ieVar;
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        this.f10087a.b().removeCallbacks(this.f10087a.f10151J);
        if (accessibilityEvent.getEventType() == 65536) {
            this.f10087a.b().postDelayed(this.f10087a.f10151J, 100);
        }
        return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }
}
