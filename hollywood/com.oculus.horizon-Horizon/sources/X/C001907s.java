package X;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* renamed from: X.07s  reason: invalid class name and case insensitive filesystem */
public final class C001907s extends ClickableSpan {
    public final int A00;
    public final int A01;
    public final AccessibilityNodeInfoCompat A02;

    public final void onClick(@NonNull View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.A01);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.A02;
        accessibilityNodeInfoCompat.A00.performAction(this.A00, bundle);
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public C001907s(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i2) {
        this.A01 = i;
        this.A02 = accessibilityNodeInfoCompat;
        this.A00 = i2;
    }
}
