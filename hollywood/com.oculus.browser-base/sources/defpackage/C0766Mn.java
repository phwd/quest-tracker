package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.material.internal.CheckableImageButton;

/* renamed from: Mn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0766Mn extends C5349w {
    public final /* synthetic */ CheckableImageButton d;

    public C0766Mn(CheckableImageButton checkableImageButton) {
        this.d = checkableImageButton;
    }

    @Override // defpackage.C5349w
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setChecked(this.d.isChecked());
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
        d2.b.setCheckable(this.d.f9693J);
        d2.b.setChecked(this.d.isChecked());
    }
}
