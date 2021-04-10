package X;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.RequiresApi;
import java.util.List;

@RequiresApi(16)
/* renamed from: X.085  reason: invalid class name */
public class AnonymousClass085 extends AccessibilityNodeProvider {
    public final AnonymousClass086 A00;

    public AnonymousClass085(AnonymousClass086 r1) {
        this.A00 = r1;
    }

    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        return null;
    }

    public final boolean performAction(int i, int i2, Bundle bundle) {
        return false;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
        return null;
    }
}
