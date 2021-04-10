package defpackage;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: sJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4727sJ extends C0142Cg1 {
    public final /* synthetic */ AJ e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4727sJ(AJ aj, TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.e = aj;
    }

    @Override // defpackage.C5349w, defpackage.C0142Cg1
    public void d(View view, D d) {
        boolean z;
        super.d(view, d);
        d.b.setClassName(Spinner.class.getName());
        if (Build.VERSION.SDK_INT >= 26) {
            z = d.b.isShowingHintText();
        } else {
            Bundle f = d.f();
            z = f != null && (f.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & 4) == 4;
        }
        if (z) {
            d.k(null);
        }
    }

    @Override // defpackage.C5349w
    public void e(View view, AccessibilityEvent accessibilityEvent) {
        this.b.onPopulateAccessibilityEvent(view, accessibilityEvent);
        AJ aj = this.e;
        AutoCompleteTextView d = AJ.d(aj, aj.f9772a.f9696J);
        if (accessibilityEvent.getEventType() == 1 && this.e.l.isTouchExplorationEnabled()) {
            AJ.f(this.e, d);
        }
    }
}
