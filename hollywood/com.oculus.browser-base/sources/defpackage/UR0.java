package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: UR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UR0 extends View.AccessibilityDelegate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ VR0 f9027a;

    public UR0(VR0 vr0) {
        this.f9027a = vr0;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        C3209jS0 js0 = this.f9027a.I;
        accessibilityNodeInfo.setCheckable((js0 == null || !js0.d() || this.f9027a.f9084J == null) ? false : true);
        accessibilityNodeInfo.setChecked(this.f9027a.isChecked());
    }
}
